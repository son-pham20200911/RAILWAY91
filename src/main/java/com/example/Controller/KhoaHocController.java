package com.example.Controller;

import com.example.DTO.KhoaHocDTO;
import com.example.Entity.Bai_Hoc;
import com.example.Entity.Khoa_Hoc;
import com.example.repository.BaiHocRepository;
import com.example.repository.KhoaHocRepository;
import com.example.service.KhoaHocService;
import com.example.service.KhoaHocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/khoahoc")
public class KhoaHocController {
    private KhoaHocService khoaHocService;

    @Autowired
    public KhoaHocController(KhoaHocService khoaHocService) {
        this.khoaHocService = khoaHocService;
    }

    //Lấy toàn bộ khóa học
    @GetMapping("/getAllKhoaHoc")
    public ResponseEntity<?> getListKhoaHoc() {
        return ResponseEntity.ok(khoaHocService.getListKhoaHoc());
    }

    //Lấy khóa học theo ID
    @GetMapping("/getKhoaHocByID/{id}")
    public ResponseEntity<?> getChiTietKhoaHoc(@PathVariable Integer id) {
        ResponseEntity<?> khoaHoc = khoaHocService.getChiTietKhoaHoc(id);
        return ResponseEntity.ok(khoaHoc);
    }

    //Tạo khóa học mới
    @PostMapping("/insertKhoaHoc")
    public ResponseEntity<?> insertKhoaHoc(@RequestBody KhoaHocDTO khoaHocDTO,@RequestParam Integer idNguoiDung) {
        this.khoaHocService.insertKhoaHoc(khoaHocDTO,idNguoiDung);
        return ResponseEntity.ok("Đã thêm mới thành công");
    }

    //Cập nhật khóa học theo id
    @PutMapping("/updateKhoaHoc/{id}")
    public ResponseEntity<?> updateKhoaHoc(@PathVariable Integer id, @RequestBody KhoaHocDTO khoaHocDTO) {
        this.khoaHocService.updateKhoaHoc(id, khoaHocDTO);
        return ResponseEntity.ok("Đã cập nhật thành công");
    }

    // Xóa khóa học
    @DeleteMapping("/deleteKhoaHoc/{id}")
    public ResponseEntity<?> deleteKhoaHoc(@PathVariable Integer id) {
        this.khoaHocService.deleteKhoaHoc(id);
        return ResponseEntity.ok("Đã xóa khóa học");
    }

    //Tìm khóa hoc theo từ khóa
    @GetMapping("/getByKeyWord")
    public ResponseEntity<?> getKhoaHocByKeyWord(@RequestParam String tenKhoa, Pageable pageable) {
        return ResponseEntity.ok(khoaHocService.getKhoaHocByKeyWord(tenKhoa, pageable));
    }
}



