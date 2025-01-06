package com.backend.utils.services;

import com.backend.ApiHandler;
import com.backend.exception.ValidationSchemaException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * AuthServiceImpl is the utility class, provides methods for validating JSON requests and responses against predefined
 * JSON schema located in resources.
 */
public class JsonValidationService {

    /**
     * Represents map of the available JSON schemas for validation.
     */
    public final Map<String, String> jsonSchemas;

    /**
     * Default constructor, inits JSON schemas map for validation.
     */
    public JsonValidationService() {
        this.jsonSchemas = loadJsonSchemas();
    }

    /**
     * Validates JSON model against predefined JSON schema.
     *
     * @param schemaName {@code schemaName} file name of the JSON schema
     * @param jsonModel {@code jsonModel} content of the validated JSON model
     */
    public void validateModelByJsonSchema(String schemaName, String jsonModel) {
        LoggerService.warn("[JsonValidationService] JSON validation has started for schema {} and model {}",
                schemaName, jsonModel);
        String schemaContent = jsonSchemas.get(schemaName);

        if (schemaContent == null) {
            LoggerService.error("[JsonValidationService] Json schema {} not found", schemaName);
            throw new ValidationSchemaException("Json schema not found");
        }

        LoggerService.info("[JsonValidationService] JSON schema has been found");

        try {
            JSONObject schemaJson = new JSONObject(schemaContent);
            Schema schema = SchemaLoader.load(schemaJson);
            LoggerService.info("[JsonValidationService] JSON schema and content has been loaded");

            JSONObject jsonObject = new JSONObject(jsonModel);
            LoggerService.info("[JsonValidationService] JSON model has been converted to JSON object, " +
                    "validating JSON model against JSON schema");
            schema.validate(jsonObject);
            LoggerService.info("[JsonValidationService] JSON model has been validated successfully");
        } catch (ValidationException validationException) {
            LoggerService.error("[JsonValidationService] Validation has failed due to {}",
                    validationException.getMessage());
            throw new ValidationSchemaException("Validation has failed due to mismatching between model and schema");
        }
    }

    /**
     * Loads predefined JSON schemas for JSON models from resources and adds them to the map.
     *
     * @return {@code Map<String, String>} JSON validation schemas
     */
    private Map<String, String> loadJsonSchemas() {
        LoggerService.warn("[JsonValidationService] JSON schemas loading has been started...");
        Map<String, String> jsonSchemas = new HashMap<>();
        String resourceDir = "validation-schemas";
        Path resourcePath;

        try {
            LoggerService.warn("[JsonValidationService] Resolving resource directory {}", resourceDir);
            resourcePath = Paths.get(
                    Objects.requireNonNull(ApiHandler.class.getClassLoader().getResource(resourceDir)).toURI()
            );
            LoggerService.info("[JsonValidationService] Resource directory has been resolved");
        } catch (URISyntaxException uriSyntaxException) {
            LoggerService.error("[JsonValidationService] Error while loading JSON Schemas {}",
                    uriSyntaxException.getMessage());
            throw new ValidationSchemaException("Error while loading JSON Schemas, resource path wasn't found");
        }

        try (Stream<Path> paths = Files.walk(resourcePath)) {
            LoggerService.info("[JsonValidationService] Generating JSON validation schemas map");
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        try {
                            LoggerService.info("[JsonValidationService] Resolving JSON schema path and content...");
                            String content = Files.readString(path);
                            jsonSchemas.put(String.valueOf(path.getFileName()), content);
                            LoggerService.info("[JsonValidationService] JSON schema path and content has been " +
                                    "resolved path {}, content: {}", path, content);
                        } catch (IOException ioException) {
                            LoggerService.error("[JsonValidationService] Error while loading JSON Schemas {}",
                                    ioException.getMessage());
                            throw new ValidationSchemaException("Error while loading JSON Schemas, path or content " +
                                    "are inappropriate");
                        }
                    });
        } catch (IOException ioException) {
            LoggerService.error("[JsonValidationService] Error while loading JSON Schemas {}",
                    ioException.getMessage());
            throw new ValidationSchemaException("Error while loading JSON Schemas, resource directory wasn't found");
        }

        return jsonSchemas;
    }
}