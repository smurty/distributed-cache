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
public class PartnerProfileItemHelper {
	
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private DynamoDBMapper dbMapper;

	public PartnerProfileItemHelper() {

	}
	
	/**
	 * @param userID
	 * @return
	 */
	@Bean
	public PartnerProfileItem load(String userID) {
		return dbMapper.load(PartnerProfileItem.class, userID);
	}
	
	/**
	 * @param item
	 */
	@Bean
	public void save(PartnerProfileItem item) {
		dbMapper.save(item);
	}
	
}
