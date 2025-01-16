package com.car_rent_api.utils.components;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class ReportUploader {

    private final S3Client s3Client;
    private String region;
    private String bucketName;
    private String key;
    private byte[] file;

    public ReportUploader(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Builder builder() {
        return this.new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder region(String region) {
            ReportUploader.this.region = region;
            return this;
        }

        public Builder bucketName(String bucketName) {
            ReportUploader.this.bucketName = bucketName;
            return this;
        }

        public Builder key(String key) {
            ReportUploader.this.key = key;
            return this;
        }

        public Builder file(byte[] file) {
            ReportUploader.this.file = file;
            return this;
        }

        public String build() {
            PutObjectRequest reportRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build();

            s3Client.putObject(reportRequest, RequestBody.fromBytes(file));

            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
        }
    }
}