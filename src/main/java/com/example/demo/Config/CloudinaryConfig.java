package com.example.demo.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@Qualifier
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "diwak35f5",
                "api_key", "561233366252739",
                "api_secret", "EjKyrs4mh9_FcU6MFtsiZCUxX8Y"
        ));
    }
}
