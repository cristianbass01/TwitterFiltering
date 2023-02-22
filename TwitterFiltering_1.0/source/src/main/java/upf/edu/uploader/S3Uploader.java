package upf.edu.uploader;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;

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
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new ProfileCredentialsProvider(this.profileName))
                .build();
    }
    @Override
    public void upload(List<String> files) {

        for (String outputFile : files) {
            try {
                this.s3Client.putObject(new PutObjectRequest(this.BucketName, prefix + outputFile, new File(outputFile)));
            }
            catch (AmazonS3Exception ex) {
                System.out.println("Exception occured: The specified bucket does not exist: " + this.BucketName);
                break;
            }
            catch(SdkClientException ex){
                System.out.println("Exception occured: " + outputFile + " not found (No such file or directory)");
            }
        }
    }
}
