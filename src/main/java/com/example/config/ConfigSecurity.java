package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class ConfigSecurity {
        private final UserDetailsService userDetailService;
        private PasswordEncoder passwordEncoder;

        @Autowired
        public ConfigSecurity(UserDetailsService userDetailService,PasswordEncoder passwordEncoder) {
            this.userDetailService = userDetailService;
            this.passwordEncoder=passwordEncoder;
        }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF cho REST API
                .authorizeHttpRequests(auth -> auth
                        // Endpoint công khai - không cần xác thực
                        .requestMatchers("/nguoidung/dangKy").permitAll() // Đăng ký
                        .requestMatchers("/khoahoc/getAllKhoaHoc").permitAll() // Xem danh sách khóa học
                        .requestMatchers("/khoahoc/getKhoaHocByID/*").permitAll() // Xem chi tiết khóa học
                        .requestMatchers("/khoahoc/getByKeyWord").permitAll() // Tìm kiếm khóa học
                        .requestMatchers("/baihoc/getAllBaiHoc").permitAll() // Xem danh sách bài học
                        .requestMatchers("/h2-console/**").permitAll() // H2 database console

                        // Endpoint cần xác thực - chỉ user đã đăng nhập
                        .requestMatchers("/khoahoc/insertKhoaHoc").authenticated() // Tạo khóa học
                        .requestMatchers("/khoahoc/updateKhoaHoc/*").authenticated() // Cập nhật khóa học
                        .requestMatchers("/khoahoc/deleteKhoaHoc/*").authenticated() // Xóa khóa học
                        .requestMatchers("/baihoc/insertBaihoc/*").authenticated() // Tạo bài học
                        .requestMatchers("/baihoc/updateBaiHoc/*").authenticated() // Cập nhật bài học
                        .requestMatchers("/baihoc/deleteBaiHoc/*").authenticated() // Xóa bài học

                        .anyRequest().authenticated() // Tất cả request khác cần xác thực
                )
                .httpBasic(httpBasic -> {}) // Sử dụng Basic Authentication
                .headers(headers -> headers.frameOptions().disable()); // Cho phép H2 console (nếu có)

        return http.build();
    }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailService)
               .passwordEncoder(passwordEncoder);
   }
}
