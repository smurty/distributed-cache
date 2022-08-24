package com.smurty.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smurty.model.partnerProfile.PartnerProfileData;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {
		cache.embedded.HazelcastEmbeddedCacheApplication.class		
})
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired(required=true)
    private ObjectMapper objectMapper = new ObjectMapper();
    

    @Test
    void get() throws Exception {
        // given
        String userID = "0ec0c2f3-f1e4-4ac0-91fc-55c8a7442f95";
        
        PartnerProfileData ppDataExpected = 
        		PartnerProfileData.builder()
        			.userID(userID)
        			.active(true)
        			.apiAccessSettings(
        					PartnerProfileData.ApiAccessSettings.builder()
        						.accountsIgnoreBuyingPower(true)
        						.usersCredentialsOptional(true)
        						.usersEmploymentTypeOptional(false)
        						.usersEnableStrictValidations(true)
        						.usersPrivacyPolicyOptional(false)
        						.build())
        			.autopilotEnabled(true)
        			.country("USA")
        			.created("2021-04-14T21:29:56.155Z")
        			.name("Marygold")
        			.type("RIA")
        			.wlpID("MARY")
        			.build();

//        // put
//        String content = objectMapper.writeValueAsString(car);
//        mockMvc.perform(
//                post("/cars")
//                        .content(content)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//        ).andExpect(status().isCreated());

        // get
        String json = mockMvc
        				.perform(MockMvcRequestBuilders.get("/partnerProfiles/" + userID))
        				.andExpect(status().isOk())
        				.andReturn()
        				.getResponse()
        				.getContentAsString();
        
        PartnerProfileData response = objectMapper.readValue(json, PartnerProfileData.class);

        assertThat(response).isEqualToComparingFieldByField(ppDataExpected);

    } // .perform(get("/partnerProfiles/" + userID))
}