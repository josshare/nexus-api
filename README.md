# Spring Boot DynamoDB Demo

This project is a Spring Boot application that uses Amazon DynamoDB.

## Running the Application Locally with Docker

Before running the application, make sure to set the following environment variables:

- AMAZON_DYNAMODB_ENDPOINT
- AMAZON_AWS_ACCESSKEY
- AMAZON_AWS_SECRETKEY

To run the Docker image locally with the required environment variables, use the following command:

```
docker run -e AMAZON_DYNAMODB_ENDPOINT=http://dynamodb.your-region.amazonaws.com \
           -e AMAZON_AWS_ACCESSKEY=your_access_key \
           -e AMAZON_AWS_SECRETKEY=your_secret_key \
           -p 8080:8080 spring-boot-dynamodb-demo
```

Replace the values for the environment variables with your actual AWS DynamoDB endpoint, access key, and secret key.

If you encounter the error "Could not resolve placeholder 'AMAZON_AWS_ACCESSKEY' in value "${AMAZON_AWS_ACCESSKEY}"", it means that the environment variables are not set correctly. Double-check that you've provided all the required environment variables when running the Docker container.

## Building and Pushing Docker Image to Amazon ECR

Follow these steps to build the Docker image and push it to Amazon ECR:

1. Build the Docker image:
   ```
   docker build -t spring-boot-dynamodb-demo .
   ```

2. Authenticate Docker to your Amazon ECR registry:
   ```
   aws ecr get-login-password --region your-region | docker login --username AWS --password-stdin your-account-id.dkr.ecr.your-region.amazonaws.com
   ```

3. Tag your image to match your repository name:
   ```
   docker tag spring-boot-dynamodb-demo:latest your-account-id.dkr.ecr.your-region.amazonaws.com/spring-boot-dynamodb-demo:latest
   ```

4. Push the image to Amazon ECR:
   ```
   docker push your-account-id.dkr.ecr.your-region.amazonaws.com/spring-boot-dynamodb-demo:latest
   ```

Replace `your-region` with your AWS region (e.g., us-west-2) and `your-account-id` with your AWS account ID.

Make sure you have the AWS CLI installed and configured with the necessary permissions to push to ECR.

## Deploying to Amazon EC2

1. Launch an EC2 instance:
   - Use an Amazon ECS-optimized Amazon Linux 2 AMI
   - Ensure the instance has the necessary IAM role to access ECR and DynamoDB
   - Configure security groups to allow inbound traffic on port 8080

2. Install and configure the ECS agent on your EC2 instance:
   ```
   sudo amazon-linux-extras disable docker
   sudo amazon-linux-extras install -y ecs
   sudo systemctl enable --now ecs
   ```

3. Create an ECS cluster (if not already created):
   ```
   aws ecs create-cluster --cluster-name spring-boot-dynamodb-demo-cluster
   ```

4. Register the task definition:
   ```
   aws ecs register-task-definition --cli-input-json file://ecs-task-definition.json
   ```

5. Create an ECS service:
   ```
   aws ecs create-service \
     --cluster spring-boot-dynamodb-demo-cluster \
     --service-name spring-boot-dynamodb-demo-service \
     --task-definition spring-boot-dynamodb-demo \
     --desired-count 1 \
     --launch-type EC2
   ```

6. Your application should now be deployed and running on EC2!

## Additional Configuration

Ensure that your EC2 instance's security group allows inbound traffic on port 8080.

Make sure that your EC2 instance's IAM role has the necessary permissions to access ECR and DynamoDB.

You may need to adjust the CPU and memory values in the task definition based on your EC2 instance type.