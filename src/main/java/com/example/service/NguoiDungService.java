package com.example.service;

import com.example.DTO.NguoiDungDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface NguoiDungService  {
    public ResponseEntity<?> dangKy(NguoiDungDTO nguoiDungDTO);
}
