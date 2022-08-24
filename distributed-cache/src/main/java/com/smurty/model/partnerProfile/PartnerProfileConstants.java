package com.smurty.model.partnerProfile;

/**
 * Use this class as a static import This file could be used to define all field
 * names used in DynamoDb. Ex: ACCT has a line defined like so ... (name =
 * value)
 * 
 * public static final String cash = "cash";
 * 
 * the name can be anything descriptive, but the value should be the exact field
 * name in DynamoDb
 * 
 * @author smurty
 *
 */
public class PartnerProfileConstants {
	
	public static final String DYNAMODB_PARTNERPROFILE 		= "bo.partnerProfiles";
		
	public static final class PARTNERPROFILE {
		public static final String userID					= "userID";
	}

	/**
	 * Prevent instantiation of this class
	 */
	private PartnerProfileConstants() {
		
	}

}
