package com.example.Controller;

import com.example.DTO.NguoiDungDTO;
import com.example.Entity.Nguoi_Dung;
import com.example.repository.NguoiDungRepository;
import com.example.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping("/nguoidung")
public class NguoiDungController {
    private NguoiDungService nguoiDungService;

    @Autowired
    public NguoiDungController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @PostMapping("/dangKy")
    public ResponseEntity<?> dangKy(@RequestBody NguoiDungDTO nguoiDungDTO) {
        return ResponseEntity.ok(nguoiDungService.dangKy(nguoiDungDTO));
    }
}