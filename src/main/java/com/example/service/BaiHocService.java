package com.example.service;

import com.example.DTO.BaiHocDTO;
import com.example.Entity.Bai_Hoc;
import org.springframework.http.ResponseEntity;

public interface BaiHocService {
    public ResponseEntity<?> insertBaiHoc(Integer id, BaiHocDTO baihocDTO);

    public ResponseEntity<?> getAllBaiHoc();

    public ResponseEntity<?>updateBaiHoc(Integer id,BaiHocDTO baiHocDTO);

    public ResponseEntity<?>deleteBaihoc(Integer id);
}
