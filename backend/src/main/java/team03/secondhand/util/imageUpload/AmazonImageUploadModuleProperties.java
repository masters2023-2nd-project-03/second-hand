package team03.secondhand.util.imageUpload;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("aws.image")
public class AmazonImageUploadModuleProperties {

    private String S3Bucket;
    private String productFolderPath;
    private String profileFolderPath;

    public AmazonImageUploadModuleProperties(String S3Bucket, String productFolderPath, String profileFolderPath) {
        this.S3Bucket = S3Bucket;
        this.productFolderPath = productFolderPath;
        this.profileFolderPath = profileFolderPath;
    }

}
