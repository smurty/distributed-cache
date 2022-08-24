package com.smurty.model.partnerProfile;

import static com.smurty.model.partnerProfile.PartnerProfileConstants.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@DynamoDBTable (tableName = DYNAMODB_PARTNERPROFILE)
public class PartnerProfileItem {
	@Setter private String 		userID;		// partition key
	private boolean		active;
	private boolean		autopilotEnabled;
	private ApiAccessSetting apiAccessSetting;
	private String 		masterTradingAccountID;
	private String		masterTradingAccountNo;
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
	@DynamoDBDocument
	public static class ApiAccessSetting {
		private boolean accountsIgnoreBuyingPower;
		private boolean usersCredentialsOptional;
		private boolean usersEmploymentTypeOptional;
		private boolean usersEnableStrictValidations;
		private boolean usersPrivacyPolicyOptional;
	}

}

