package com.mocha.model.partnerProfile;

import static com.mocha.model.partnerProfile.PartnerProfileConstants.*;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable (tableName = DYNAMODB_PARTNERPROFILE)
public class PartnerProfileData implements Serializable {
	@Setter private String 		userID;		// partition key
	private boolean		active;
	private boolean		autopilotEnabled;
	private ApiAccessSettings apiAccessSettings;
	private String		country;
	private String		created;
	private String		name;
	private String		type;
	private String		wlpID;
	
	@DynamoDBHashKey
	public String getUserID() {
		return userID;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@DynamoDBDocument
	public static class ApiAccessSettings implements Serializable {
		private boolean accountsIgnoreBuyingPower;
		private boolean usersCredentialsOptional;
		private boolean usersEmploymentTypeOptional;
		private boolean usersEnableStrictValidations;
		private boolean usersPrivacyPolicyOptional;
	}

}

