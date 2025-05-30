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
@EnableWebSecurity
public class ConfigSecurity {
        private final UserDetailsService userDetailService;

        @Autowired
        public ConfigSecurity(UserDetailsService userDetailService) {
            this.userDetailService = userDetailService;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            // NoOpPasswordEncoder: Không mã hóa(Người dùng nhập Pass như nào thì vào CSDL y như vậy)
            // return NoOpPasswordEncoder.getInstance();

            // BCryptPasswordEncoder(): Đã mã hóa(Người dùng nhập Pass và dòng Pass đó sẽ được mã hóa thành 1 chuỗi ngẫu nhiên nào đó rồi mới vào CSDL)
            return new BCryptPasswordEncoder();
        }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Tắt CSRF cho REST API
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/nguoidung/dangKy").permitAll() // Cho phép đăng ký không cần xác thực
//                        .requestMatchers("/h2-console/**").permitAll() // Nếu sử dụng H2 database console
//                        .anyRequest().authenticated() // Tất cả request khác cần xác thực
//                )
//                .httpBasic(httpBasic -> {}) // Sử dụng Basic Authentication
//                .headers(headers -> headers.frameOptions().disable()); // Cho phép H2 console (nếu có)
//
//        return http.build();
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailService)
//                .passwordEncoder(passwordEncoder());
//    }
}
