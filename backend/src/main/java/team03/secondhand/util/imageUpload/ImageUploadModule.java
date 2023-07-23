package team03.secondhand.util.imageUpload;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadModule {

    public String productImageUpload(MultipartFile multipartFile, String imageName);

    public String profileImageUpload(MultipartFile multipartFile, String imageName);
}
