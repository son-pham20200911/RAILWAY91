package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PaswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder: Không mã hóa(Người dùng nhập Pass như nào thì vào CSDL y như vậy)
        // return NoOpPasswordEncoder.getInstance();

        // BCryptPasswordEncoder(): Đã mã hóa(Người dùng nhập Pass và dòng Pass đó sẽ được mã hóa thành 1 chuỗi ngẫu nhiên nào đó rồi mới vào CSDL)
        return new BCryptPasswordEncoder();
    }
}
