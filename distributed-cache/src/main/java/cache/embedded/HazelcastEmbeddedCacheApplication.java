package cache.embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {
		"com.smurty.rest",
		"com.smurty.service",
		"com.smurty.model.partnerProfile",
		"com.smurty.config"
})
public class HazelcastEmbeddedCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelcastEmbeddedCacheApplication.class, args);
    }

}

//		"cache.embedded",