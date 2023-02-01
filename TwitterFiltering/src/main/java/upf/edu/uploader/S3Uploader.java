package upf.edu.uploader;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class S3Uploader implements Uploader {
    final String BucketName;
    final String prefix;
    final String profileName;

    final AmazonS3 s3Client;

    public S3Uploader(String bucketName, String prefix, String profileName){
        this.BucketName = bucketName;
        this.prefix = prefix;
        this.profileName = profileName;

        this.s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_2)
                .withCredentials(new ProfileCredentialsProvider(this.profileName))
                .build();
    }
    @Override
    public void upload(List<String> files) {
        try {
            for (String outputFile : files) {
                try {
                    this.s3Client.putObject(this.BucketName, outputFile, new File(prefix + outputFile));
                } catch(Exception e){
                    System.out.println(e.toString());
                    System.out.println(outputFile + " not found");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(this.BucketName + " not found");
        }

    }
}
