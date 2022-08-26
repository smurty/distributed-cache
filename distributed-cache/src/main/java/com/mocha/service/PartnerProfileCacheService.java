package com.mocha.service;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.mocha.model.partnerProfile.PartnerProfileData;
import com.mocha.model.partnerProfile.PartnerProfileDataHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerProfileCacheService {

    public static final String PARTNERPROFILE_CACHE = "partnerProfileCache";
    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());
    
    @Autowired
    private PartnerProfileDataHelper ppHelper;

    public PartnerProfileData put(String userID, PartnerProfileData ppItem){
        IMap<String, PartnerProfileData> map = hazelcastInstance.getMap(PARTNERPROFILE_CACHE);
        return map.put(userID, ppItem);
    }

    public PartnerProfileData get(String userID){
        IMap<String, PartnerProfileData> map = hazelcastInstance.getMap(PARTNERPROFILE_CACHE);
        return map.computeIfAbsent(userID, (userid) -> ppHelper.load(userid));
    }
    

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
//        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

//    private SerializerConfig serializerConfig() {
//        return  new SerializerConfig()
//                .setImplementation(new PartnerProfilesSerializer())
//                .setTypeClass(Car.class);
//    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(PARTNERPROFILE_CACHE);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(360);
        mapConfig.setPerEntryStatsEnabled(true);
        return mapConfig;
    }
}
