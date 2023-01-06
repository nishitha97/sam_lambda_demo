package com.lambda_demo.aws.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LambdaDemo implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger logger = LoggerFactory.getLogger(LambdaDemo.class);

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

        Map<String, String> applicationHeaders = new HashMap<>();
        applicationHeaders.put("Content-Type", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(applicationHeaders);

        try {
            logger.info("========== Task Executed Successfully =================");

            return response
                    .withStatusCode(200)
                    .withBody("{\"message\":\"Task Executed Successfully\"}");


        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error " + e.getMessage());
            return response
                    .withBody("{\"message\":\"Task Failed due to Exception\"}")
                    .withStatusCode(500);
        }
    }

}

