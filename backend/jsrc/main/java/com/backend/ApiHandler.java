package com.backend;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.utils.services.LoggerService;
import com.syndicate.deployment.annotations.environment.EnvironmentVariable;
import com.syndicate.deployment.annotations.environment.EnvironmentVariables;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.Architecture;
import com.syndicate.deployment.model.DeploymentRuntime;
import com.syndicate.deployment.model.ResourceType;
import com.syndicate.deployment.model.RetentionSetting;

import java.util.Map;

import static com.syndicate.deployment.model.environment.ValueTransformer.USER_POOL_NAME_TO_CLIENT_ID;
import static com.syndicate.deployment.model.environment.ValueTransformer.USER_POOL_NAME_TO_USER_POOL_ID;

/**
 * Main class for Car Rent Application, contains main entry point for Lambda function execution.
 * Lambda is configured with annotation-based configuration:
 * - @DependsOn - Lambda function dependencies
 * - @LambdaHandler - main Lambda function configuration
 * - @DynamoDbEvents - triggers on specific DynamoDB events
 * - @EnvironmentVariables - environment variables set for Lambda function
 */
@DependsOn(name = "${user_table}", resourceType = ResourceType.DYNAMODB_TABLE)
@DependsOn(name = "${support_agents_table}", resourceType = ResourceType.DYNAMODB_TABLE)
@DependsOn(name = "${home_table}", resourceType = ResourceType.DYNAMODB_TABLE)
@DependsOn(name = "${faq_table}", resourceType = ResourceType.DYNAMODB_TABLE)
@DependsOn(name = "${cars_table}", resourceType = ResourceType.DYNAMODB_TABLE)
@DependsOn(name = "${booking_table}", resourceType = ResourceType.DYNAMODB_TABLE)
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
@EnvironmentVariables({
		@EnvironmentVariable(key = "REGION", value = "${region}"),
		@EnvironmentVariable(key = "USERS_TABLE", value = "${user_table}"),
		@EnvironmentVariable(key = "SUPPORT_AGENTS_TABLE", value = "${support_agents_table}"),
		@EnvironmentVariable(key = "HOME_TABLE", value = "${home_table}"),
		@EnvironmentVariable(key = "FAQ_TABLE", value = "${faq_table}"),
		@EnvironmentVariable(key = "CARS_TABLE", value = "${cars_table}"),
		@EnvironmentVariable(key = "BOOKING_TABLE", value = "${booking_table}"),
		@EnvironmentVariable(key = "COGNITO_ID", value = "${cognito_user_pool}",
				valueTransformer = USER_POOL_NAME_TO_USER_POOL_ID),
		@EnvironmentVariable(key = "CLIENT_ID", value = "${cognito_user_pool}",
				valueTransformer = USER_POOL_NAME_TO_CLIENT_ID)
})
public class ApiHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	/**
	 * Initialised Car Rent Application.
	 */
	private final CarRentApplication carRentApplication = DaggerCarRentApplication.create();

	/**
	 * Initialised General endpoint handler.
	 */
	private final EndpointHandler generalHandler = carRentApplication.getGeneralApiHandler();

	/**
	 * Initialised CORS headers.
	 */
	private final Map<String, String> corsHeaders = carRentApplication.getCorsHeaders();

	/**
	 * Main execution method, entry point for Lambda function execution, catches request from API Gateway and
	 * forwards it to General handler for resolution.
	 *
	 * @param event {@code APIGatewayProxyRequestEvent} caught APIGatewayProxyRequestEvent
	 * @param context {@code Context} Lambda executable context
	 * @return {@code APIGatewayProxyResponseEvent} response as the result of handling request
	 */
	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
		LoggerService.info("handleRequest started");
		return generalHandler.handle(event, context).withHeaders(corsHeaders);
	}
}