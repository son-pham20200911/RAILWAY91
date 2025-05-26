package com.example.service;

import com.example.DTO.BaiHocDTO;
import com.example.Entity.Bai_Hoc;
import com.example.Entity.Khoa_Hoc;
import com.example.repository.BaiHocRepository;
import com.example.repository.KhoaHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BaiHocServiceImpl implements BaiHocService{
    private BaiHocRepository baiHocrepo;
    private KhoaHocRepository khoaHocRepo;

    @Autowired
    public BaiHocServiceImpl(BaiHocRepository baiHocrepo, KhoaHocRepository khoaHocRepo) {
        this.baiHocrepo = baiHocrepo;
        this.khoaHocRepo = khoaHocRepo;
    }

    //Thêm Bài Học
    public ResponseEntity<?> insertBaiHoc( Integer id, BaiHocDTO baiHocDTO) {
        // Kiểm tra tenBai,soGio có null hoặc rỗng hay không
        if (baiHocDTO.getTenBai() == null || baiHocDTO.getTenBai().trim().isEmpty()) {
            ResponseEntity.badRequest().body("Tên bài học không được để trống");
        }
        if (baiHocDTO.getSoGio() == null) {
            ResponseEntity.badRequest().body("Số giờ bài học không được để trống");
        }
        // Tìm khóa học theo ID
        Khoa_Hoc khoaHoc = khoaHocRepo.findById(id).orElse(null);
        if (khoaHoc == null) {
            ResponseEntity.badRequest().body("Không tìm thấy khóa học có id: " + id);
        }
        // Tạo bài học mới
        Bai_Hoc baiHoc = new Bai_Hoc();
        baiHoc.setTenBai(baiHocDTO.getTenBai());
        baiHoc.setSoGio(baiHocDTO.getSoGio());
        baiHoc.setMoTa(baiHocDTO.getMoTa());
        baiHoc.setKhoaHocS(khoaHoc);

        baiHocrepo.save(baiHoc);
        return ResponseEntity.ok("");
    }

    // Lấy ra tất cả các Bài Học
    public ResponseEntity<?> getAllBaiHoc() {
        List<Bai_Hoc> baiHocS = baiHocrepo.findAll();
        if (baiHocS.isEmpty()) {
            return ResponseEntity.badRequest().body("Không tìm thấy bài học nào");
        }
        return ResponseEntity.ok(baiHocS);
    }

    // Cập nhật bài học
    public ResponseEntity<?>updateBaiHoc(@PathVariable Integer id,@RequestBody BaiHocDTO baiHocDTO){
        Bai_Hoc baiHoc=baiHocrepo.findById(id).orElse(null);
        if(baiHoc==null){
            return ResponseEntity.badRequest().body("Không tìm thấy bài học có id: "+id);
        }
        if(baiHocDTO.getTenBai()==null){
            return ResponseEntity.badRequest().body("Không được để trống tên bài học");
        }else if(baiHocDTO.getSoGio()==null){
            return ResponseEntity.badRequest().body("Không được để trống số giờ bài học");
        }if(baiHocDTO.getMoTa()==null){
            return ResponseEntity.badRequest().body("Không được để trống mô tả bài học");
        }
        //Thay đổi khóa học
        if(baiHocDTO.getIdKhoahoc()!=null){
            Khoa_Hoc khoaHoc=khoaHocRepo.findById(baiHocDTO.getIdKhoahoc()).orElse(null);
            if(khoaHoc==null){
                return ResponseEntity.badRequest().body("Không tìm thấy khóa học có id: "+baiHocDTO.getIdKhoahoc());
            }
            baiHoc.setKhoaHocS(khoaHoc);
        }
        baiHoc.setTenBai(baiHocDTO.getTenBai());
        baiHoc.setSoGio(baiHocDTO.getSoGio());
        baiHoc.setMoTa(baiHocDTO.getMoTa());

        baiHocrepo.save(baiHoc);

        return ResponseEntity.ok("");
    }

    //Xóa bài học
    public ResponseEntity<?>deleteBaihoc(@PathVariable Integer id){
        Optional<Bai_Hoc> baiHoc=baiHocrepo.findById(id);
        if(baiHoc.isEmpty()){
            return ResponseEntity.badRequest().body("Không tìm thấy bài học có id: "+id);
        }
        baiHocrepo.deleteById(id);
        return ResponseEntity.ok("");
    }
}
