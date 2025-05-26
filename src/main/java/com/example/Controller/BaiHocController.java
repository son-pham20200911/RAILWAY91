package com.example.Controller;

import com.example.DTO.BaiHocDTO;
import com.example.repository.BaiHocRepository;
import com.example.repository.KhoaHocRepository;
import com.example.service.BaiHocService;
import com.example.service.BaiHocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baihoc")
public class BaiHocController {
    private BaiHocService baiHocService;

    @Autowired
    public BaiHocController( BaiHocService baiHocService) {
        this.baiHocService=baiHocService;
    }

    //Thêm bài học
    @PostMapping("/insertBaihoc/{id}")
    public ResponseEntity<?> insertBaiHoc(@PathVariable Integer id, @RequestBody BaiHocDTO baiHocDTO) {
        return ResponseEntity.ok(baiHocService.insertBaiHoc(id,baiHocDTO)+"Đã thêm thành công");
    }


    //Xem nội dung bài học
    @GetMapping("/getAllBaiHoc")
    public ResponseEntity<?> getAllBaiHoc() {
        return ResponseEntity.ok(baiHocService.getAllBaiHoc());
    }

    // Cập Nhật Bài Học
    @PutMapping("/updateBaiHoc/{id}")
    public ResponseEntity<?>updateBaiHoc(@PathVariable Integer id,@RequestBody BaiHocDTO baiHocDTO){
        return ResponseEntity.ok(baiHocService.updateBaiHoc(id,baiHocDTO)+" Cập nhật bài học thành công");
    }

    //Xóa bài học theo ID
    @DeleteMapping("/deleteBaiHoc/{id}")
    public ResponseEntity<?>deleteBaihoc(@PathVariable Integer id){
        return ResponseEntity.ok( baiHocService.deleteBaihoc(id)+ "Đã xóa thành công");
    }
}
