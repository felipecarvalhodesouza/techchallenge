package br.com.postech.techchallenge.main.security;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;

public class CognitoUserHelper {
	
    private final String AWS_REGION = "us-east-1";

    public String getUserEmail(String token) throws Exception {
    	
    	String userCode = new JwtHelper(token).extractUsername();
    	
        AWSCognitoIdentityProvider cognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(AWS_REGION)
                .build();

        AdminGetUserRequest getUserRequest = new AdminGetUserRequest()
                .withUserPoolId("us-east-1_pGFsCFVuS")
                .withUsername(userCode);
        

        AdminGetUserResult getUserResult = cognitoIdentityProvider.adminGetUser(getUserRequest);

        for (AttributeType attribute : getUserResult.getUserAttributes()) {
            if (attribute.getName().equals("email")) {
                return attribute.getValue();
            }
        }

        return null;
    }
}
