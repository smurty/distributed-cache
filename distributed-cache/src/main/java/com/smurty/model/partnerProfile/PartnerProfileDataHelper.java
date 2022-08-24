package com.smurty.model.partnerProfile;

//import static com.smurty.model.partnerProfile.PartnerProfileConstants.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


/**
 * @author smurty
 *
 */
@Configuration
public class PartnerProfileDataHelper {
	
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private DynamoDBMapper dbMapper;

	public PartnerProfileDataHelper() {

	}
	
	/**
	 * @param userID
	 * @return
	 */
	public PartnerProfileData load(String userID) {
		return dbMapper.load(PartnerProfileData.class, userID);
	}
	
	/**
	 * @param item
	 */
	public void save(PartnerProfileData item) {
		dbMapper.save(item);
	}
	
}
