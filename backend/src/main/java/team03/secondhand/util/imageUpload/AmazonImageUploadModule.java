package team03.secondhand.util.imageUpload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

public class AmazonImageUploadModule implements ImageUploadModule {

    private AmazonS3Client amazonS3Client;
    private String S3Bucket;
    private String productFolderPath;
    private String profileFolderPath;

    public AmazonImageUploadModule(AmazonS3Client amazonS3Client, AmazonImageUploadModuleProperties properties) {
        this.amazonS3Client = amazonS3Client;
        this.S3Bucket = properties.getS3Bucket();
        this.productFolderPath = properties.getProductFolderPath();
        this.profileFolderPath = properties.getProfileFolderPath();
    }

    /*
    /**********************************************************************
    /* Public Method
    /**********************************************************************
     */

    @Override
    public String productImageUpload(MultipartFile multipartFile, String imageName) {
        return upload(multipartFile, productFolderPath + imageName);
    }

    @Override
    public String profileImageUpload(MultipartFile multipartFile, String imageName) {
        return upload(multipartFile, profileFolderPath + imageName);
    }

    /*
    /**********************************************************************
    /* Private Method
    /**********************************************************************
     */

    private String upload(MultipartFile multipartFile, String imageName) {
        imageUpload(multipartFile, imageName);
        return amazonS3Client.getUrl(S3Bucket, imageName).toString();
    }

    private void imageUpload(MultipartFile multipartFile, String imageName) {
        ObjectMetadata objectMetadata = getObjectMetadate(multipartFile);

        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, imageName, multipartFile.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMetadata getObjectMetadate(MultipartFile multipartFile) {
        long fileSize = multipartFile.getSize();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(fileSize);
        return objectMetadata;
    }

}
