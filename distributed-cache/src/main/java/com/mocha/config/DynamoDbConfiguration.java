package com.mocha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

//import org.springframework.context.annotation.Profile;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.ConversionSchemas;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
//@ComponentScan({  })
public class DynamoDbConfiguration {
  @Value("${aws.dynamo.endpoint}")
  private String endPoint;

  @Value("${aws.cloud.region}")
  private String region;

  @Value("${aws.cloud.accessKey}")
  private String accessKey;

  @Value("${aws.cloud.secretKey}")
  private String secretKey;
  
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Bean
  @Primary
  public DynamoDBMapper dynamoDBMapper() {
    return new DynamoDBMapper(buildAmazonDynamoDB(), buildAmazonDynamoDBMapperConfig());
  }

  @Bean
  public DynamoDB dynamoDB() {
    return new DynamoDB(buildAmazonDynamoDB());
  }
  
  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return buildAmazonDynamoDB();
  }

  @Bean
  public ObjectMapper objectMapper() {
	  return objectMapper;
  }
  
  private AmazonDynamoDB buildAmazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, region))
        .withCredentials(
            new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        .build();
  }
  
  private DynamoDBMapperConfig buildAmazonDynamoDBMapperConfig() {
	  return new DynamoDBMapperConfig.Builder()
				.withConversionSchema(ConversionSchemas.V2)
				.withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.LAZY_LOADING)
				.withBatchLoadRetryStrategy(DynamoDBMapperConfig.DefaultBatchLoadRetryStrategy.INSTANCE)
				.withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)				// write
				.withConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL)	// read
				.build();
  }
  
//  @Bean
//  @Profile("rest")
//  public DynamoDBMappingContext dynamoDBMappingContext() {
//      return new DynamoDBMappingContext();
//  }

}
