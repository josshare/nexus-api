package com.example.demo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    @Value("http://dynamodb.your-region.amazonaws.com")
    private String amazonDynamoDBEndpoint;

    @Value("${AMAZON_AWS_ACCESSKEY}")
    private String amazonAWSAccessKey;

    @Value("${AMAZON_AWS_SECRETKEY}")
    private String amazonAWSSecretKey;

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                amazonDynamoDBEndpoint,
                                "us-east-1"
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        amazonAWSAccessKey,
                                        amazonAWSSecretKey
                                )
                        )
                )
                .build();
    }
}