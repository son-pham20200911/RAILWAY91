package com.example.service;

import com.example.DTO.KhoaHocDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface KhoaHocService {
    public ResponseEntity<?> getListKhoaHoc();
    public ResponseEntity<?> getChiTietKhoaHoc(Integer id);
    public ResponseEntity<?> insertKhoaHoc(KhoaHocDTO khoaHocDTO,@PathVariable Integer idNguoiDung);
    public ResponseEntity<?> updateKhoaHoc(Integer id,KhoaHocDTO khoaHocDTO);
    public ResponseEntity<?> deleteKhoaHoc(Integer id);
    public ResponseEntity<?> getKhoaHocByKeyWord(String tenKhoa, Pageable pageable);
}
