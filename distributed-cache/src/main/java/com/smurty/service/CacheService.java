package com.smurty.service;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.smurty.model.partnerProfile.PartnerProfileItem;
import com.smurty.model.partnerProfile.PartnerProfileItemHelper;

import cache.embedded.rest.Car;
import cache.embedded.serializer.CarSerializer;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    public static final String PARTNERPROFILE_CACHE = "partnerProfileCache";
    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());
    
    @Autowired
    private PartnerProfileItemHelper ppHelper;

    public PartnerProfileItem put(String userID, PartnerProfileItem ppItem){
        IMap<String, PartnerProfileItem> map = hazelcastInstance.getMap(PARTNERPROFILE_CACHE);
        return map.put(userID, ppItem);
    }

    public PartnerProfileItem get(String userID){
        IMap<String, PartnerProfileItem> map = hazelcastInstance.getMap(PARTNERPROFILE_CACHE);
        return map.computeIfAbsent(userID, (aUserID) -> ppHelper.load(aUserID));
    }
    

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

    private SerializerConfig serializerConfig() {
        return  new SerializerConfig()
                .setImplementation(new CarSerializer())
                .setTypeClass(Car.class);
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(PARTNERPROFILE_CACHE);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(360);
        mapConfig.setPerEntryStatsEnabled(true);
        return mapConfig;
    }
}
