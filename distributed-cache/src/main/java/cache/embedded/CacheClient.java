package cache.embedded;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import cache.embedded.rest.Car;
import cache.embedded.serializer.CarSerializer;

import org.springframework.stereotype.Component;

@Component
public class CacheClient {

    public static final String CARS = "cars";
    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());

    public Car put(String number, Car car){
        IMap<String, Car> map = hazelcastInstance.getMap(CARS);
        return map.put(number, car);
    }

    public Car get(String key){
        IMap<String, Car> map = hazelcastInstance.getMap(CARS);
        return map.get(key);
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
        MapConfig mapConfig = new MapConfig(CARS);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(360);
        mapConfig.setPerEntryStatsEnabled(true);
        return mapConfig;
    }
}
