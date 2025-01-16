package com.car_rent_api.utils.components;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import org.apache.commons.codec.binary.Base64;

public class ImageUploader {

    private final S3Client s3Client;
    private String region;
    private String bucketName;
    private String key;
    private String base64Image;

    public ImageUploader(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public Builder builder() {
        return this.new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder region(String region) {
            ImageUploader.this.region = region;
            return this;
        }

        public Builder bucketName(String bucketName) {
            ImageUploader.this.bucketName = bucketName;
            return this;
        }

        public Builder key(String key) {
            ImageUploader.this.key = key;
            return this;
        }

        public Builder base64Image(String base64Image) {
            ImageUploader.this.base64Image = base64Image;
            return this;
        }

        public String build() {
            byte[] imageBytes = Base64.decodeBase64(base64Image);
            PutObjectRequest imageRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build();

            s3Client.putObject(imageRequest, RequestBody.fromBytes(imageBytes));

            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
        }
    }
}