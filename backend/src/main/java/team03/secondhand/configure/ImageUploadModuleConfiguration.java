package team03.secondhand.configure;


import com.amazonaws.services.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import team03.secondhand.util.imageUpload.AmazonImageUploadModule;
import team03.secondhand.util.imageUpload.AmazonImageUploadModuleProperties;
import team03.secondhand.util.imageUpload.ImageUploadModule;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(AmazonImageUploadModuleProperties.class)
public class ImageUploadModuleConfiguration {

    private final AmazonImageUploadModuleProperties properties;
    private final AmazonS3Client amazonS3Client;

    @Bean
    public ImageUploadModule imageUploadModule() {
        return new AmazonImageUploadModule(amazonS3Client, properties);
    }
}


