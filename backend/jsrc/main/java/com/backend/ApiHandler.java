package com.backend;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.syndicate.deployment.annotations.environment.EnvironmentVariable;
import com.syndicate.deployment.annotations.environment.EnvironmentVariables;
import com.syndicate.deployment.annotations.events.DynamoDbEvents;
import com.syndicate.deployment.annotations.events.DynamoDbTriggerEventSource;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.Architecture;
import com.syndicate.deployment.model.DeploymentRuntime;
import com.syndicate.deployment.model.ResourceType;
import com.syndicate.deployment.model.RetentionSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.syndicate.deployment.model.environment.ValueTransformer.USER_POOL_NAME_TO_CLIENT_ID;
import static com.syndicate.deployment.model.environment.ValueTransformer.USER_POOL_NAME_TO_USER_POOL_ID;

@DependsOn(name = "${user_table}", resourceType = ResourceType.DYNAMODB_TABLE)
@DependsOn(name = "${cognito_user_pool}", resourceType = ResourceType.COGNITO_USER_POOL)
@LambdaHandler(
		lambdaName = "api_handler",
		runtime = DeploymentRuntime.JAVA17,
		architecture = Architecture.ARM64,
		roleName = "api_handler-role",
		isPublishVersion = true,
		aliasName = "${lambdas_alias_name}",
		logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@DynamoDbEvents({
		@DynamoDbTriggerEventSource(targetTable = "${user_table}", batchSize = 10),
})
@EnvironmentVariables({
		@EnvironmentVariable(key = "REGION", value = "${region}"),
		@EnvironmentVariable(key = "USERS_TABLE", value = "${user_table}"),
		@EnvironmentVariable(key = "COGNITO_ID", value = "${cognito_user_pool}",
				valueTransformer = USER_POOL_NAME_TO_USER_POOL_ID),
		@EnvironmentVariable(key = "CLIENT_ID", value = "${cognito_user_pool}",
				valueTransformer = USER_POOL_NAME_TO_CLIENT_ID)
})
public class ApiHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private final CarRentApplication carRentApplication = DaggerCarRentApplication.create();
	private final EndpointHandler generalHandler = carRentApplication.getGeneralApiHandler();
	private final Map<String, String> corsHeaders = carRentApplication.getCorsHeaders();
	private static final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
		logger.info("handleRequest started");
		return generalHandler.handle(event, context).withHeaders(corsHeaders);
	}
}