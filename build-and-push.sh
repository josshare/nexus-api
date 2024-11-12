#!/bin/bash

# Set variables
AWS_ACCOUNT_ID="your-aws-account-id"
AWS_REGION="your-aws-region"
ECR_REPOSITORY="spring-boot-dynamodb-demo"
IMAGE_TAG="latest"

# Build Docker image
docker build -t $ECR_REPOSITORY:$IMAGE_TAG .

# Authenticate Docker to ECR
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com

# Tag image for ECR
docker tag $ECR_REPOSITORY:$IMAGE_TAG $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPOSITORY:$IMAGE_TAG

# Push image to ECR
docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPOSITORY:$IMAGE_TAG

echo "Docker image built and pushed to ECR successfully!"