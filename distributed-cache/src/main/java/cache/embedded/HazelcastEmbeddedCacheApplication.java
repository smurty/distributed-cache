package cache.embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {
		"com.mocha.rest",
		"com.mocha.service",
		"com.mocha.model.partnerProfile",
		"com.mocha.config"
})
public class HazelcastEmbeddedCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelcastEmbeddedCacheApplication.class, args);
    }

}
