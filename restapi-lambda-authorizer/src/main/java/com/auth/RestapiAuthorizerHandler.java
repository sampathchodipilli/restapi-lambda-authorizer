package com.auth;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.auth.input.model.Input;
import com.auth.output.model.AuthPolicy;
import com.auth.output.model.AuthPolicy.HttpMethod;

public class RestapiAuthorizerHandler implements RequestHandler<Input, AuthPolicy> {

	private Logger logger = LogManager.getLogger(RestapiAuthorizerHandler.class);

	@Override
	public AuthPolicy handleRequest(Input input, Context context) {

		logger.info("Input Context :: " + input.toString());

		// get the headers that are coming as input
		Map<String, String> headers = input.getHeaderData();
		
		try {

			// get the bearer token by ignoring the bearer part
			String token = input.getAuthorizationToken().substring(7);

			/**
			 * Verify the bearer token here
			 * 
			 * 
			 * Code Goes Here
			 * 
			 * 
			 **/
			
			logger.info("User access allowed !");
			
			// once the token is successfully verified generate a allow iam policy and send it
			return new AuthPolicy(headers.get("accountId"),
					AuthPolicy.PolicyDocument.getAllowOnePolicy(headers.get("region"), headers.get("accountId"),
							headers.get("apiId"), headers.get("stage"), HttpMethod.valueOf(headers.get("httpMethod")), "*"));

		} catch (Exception e) {
			// if the token is not verified or any error occurs then generate deny iam policy
			logger.error("Error :: ", e);
			
			logger.info("User access denied !");
			
			return new AuthPolicy(headers.get("accountId"),
					AuthPolicy.PolicyDocument.getDenyOnePolicy(headers.get("region"), headers.get("accountId"),
							headers.get("apiId"), headers.get("stage"), HttpMethod.valueOf(headers.get("httpMethod")), "*"));
		}
	}

}
