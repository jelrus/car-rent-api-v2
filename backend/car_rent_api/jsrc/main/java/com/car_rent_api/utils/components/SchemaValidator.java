package com.car_rent_api.utils.components;

import com.car_rent_api.ApiHandler;
import com.car_rent_api.exception.JsonSchemaException;
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

public class SchemaValidator {

    public final Map<String, String> jsonSchemas;
    private final String resourcePath;
    private final String extension;

    public SchemaValidator() {
        this.resourcePath = "schemas";
        this.extension = ".json";
        this.jsonSchemas = loadJsonSchemas();
    }

    public void validateModelByJsonSchema(String schemaName, String jsonModel) {
        LogPrinter.info("[SchemaValidator] Preparing Json schemas for validation ...");
        String schemaContent = jsonSchemas.get(schemaName);

        if (schemaContent == null) {
            LogPrinter.error("[SchemaValidator] Json schema was not found");
            throw new JsonSchemaException("Json schema was not found");
        }

        try {
            JSONObject schemaJson = new JSONObject(schemaContent);
            Schema schema = SchemaLoader.load(schemaJson);
            JSONObject jsonObject = new JSONObject(jsonModel);
            schema.validate(jsonObject);
            LogPrinter.info("[SchemaValidator] Model's fields are all valid. Exiting...");
        } catch (ValidationException validationEx) {
            LogPrinter.error("[SchemaValidator] Some of the model's fields not matching the schema. Exiting... ",
                    validationEx.getMessage());
            throw new JsonSchemaException("Some of the model's fields not matching the schema " +
                    validationEx.getMessage());
        }
    }

    private Map<String, String> loadJsonSchemas() {
        return loadSchemas(loadSchemasPath());
    }

    private Path loadSchemasPath() {
        LogPrinter.info("[SchemaValidator] Loading Json schema path...");

        try {
            return Paths.get(
                    Objects.requireNonNull(ApiHandler.class.getClassLoader().getResource(resourcePath)).toURI()
            );
        } catch (URISyntaxException uriSyntaxException) {
            LogPrinter.error("[SchemaValidator] Error while loading JSON Schemas, resource path was not found",
                    uriSyntaxException.getMessage());
            throw new JsonSchemaException("Error while loading JSON Schemas, resource path was not found");
        }
    }

    private Map<String, String> loadSchemas(Path resourcePath) {
        LogPrinter.info("[SchemaValidator] Loading Json schemas...");
        Map<String, String> jsonSchemas = new HashMap<>();

        try (Stream<Path> paths = Files.walk(resourcePath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(extension))
                    .forEach(path -> loadSchemaFile(jsonSchemas, path));
        } catch (IOException ioException) {
            LogPrinter.error("[SchemaValidator] Error while loading JSON Schemas, resource directory was not found",
                    ioException.getMessage());
            throw new JsonSchemaException("Error while loading JSON Schemas, resource directory was not found");
        }

        return jsonSchemas;
    }

    private void loadSchemaFile(Map<String, String> jsonSchemas, Path path) {
        LogPrinter.info("[SchemaValidator] Resolving Json schema file...");

        try {
            String content = Files.readString(path);
            jsonSchemas.put(String.valueOf(path.getFileName()), content);
        } catch (IOException ioException) {
            LogPrinter.error("[SchemaValidator] Error while loading JSON Schemas, path or content are inappropriate",
                    ioException.getMessage());
            throw new JsonSchemaException("Error while loading JSON Schemas, path or content are inappropriate");
        }
    }
}