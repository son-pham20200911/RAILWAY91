package com.example.service;

import com.example.DTO.KhoaHocDTO;
import com.example.Entity.Bai_Hoc;
import com.example.Entity.Khoa_Hoc;
import com.example.Entity.Nguoi_Dung;
import com.example.repository.BaiHocRepository;
import com.example.repository.KhoaHocRepository;
import com.example.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
public class KhoaHocServiceImpl implements KhoaHocService{

    private KhoaHocRepository khoaHocRepo;
    private BaiHocRepository baiHocRepo;
    private NguoiDungRepository nguoiDungRepo;

    @Autowired
    public KhoaHocServiceImpl(KhoaHocRepository khoaHocRepo,BaiHocRepository baiHocRepo,NguoiDungRepository nguoiDungRepo) {
        this.khoaHocRepo = khoaHocRepo;
        this.baiHocRepo=baiHocRepo;
        this.nguoiDungRepo=nguoiDungRepo;
    }

    @Override
    public ResponseEntity<?> getListKhoaHoc() {
        List<Khoa_Hoc> khoaHocs = khoaHocRepo.findAll();
        if (khoaHocs.isEmpty()) {
            return ResponseEntity.badRequest().body("Không tìm thấy khóa học nào");
        }
        return ResponseEntity.ok(khoaHocs);
    }

    @Override
    public ResponseEntity<?> getChiTietKhoaHoc(Integer id) {
        Optional<Khoa_Hoc> khoaHoc1 = khoaHocRepo.findById(id);
        if (khoaHoc1.isEmpty()) {
            return ResponseEntity.badRequest().body("Không tìm thấy khóa học có ID: " + id);
        }
        Khoa_Hoc khoaHoc2 = khoaHoc1.get();
        return ResponseEntity.ok(khoaHoc2);
    }

    @Override
    public ResponseEntity<?> insertKhoaHoc(KhoaHocDTO khoaHocDTO, Integer idNguoiDUng) {
        String khoaHocName = khoaHocDTO.getTenKhoa();
        Optional<Khoa_Hoc> opKhohoc = khoaHocRepo.findByTenKhoa(khoaHocName);
        if (opKhohoc.isPresent()) {
            return ResponseEntity.badRequest().body("Tên khóa học đã tồn tại");
        }
        if (khoaHocDTO.getTenKhoa() == null) {
            return ResponseEntity.badRequest().body("Không được để trống tên");
        } else if (khoaHocDTO.getSoBuoi() == null) {
            return ResponseEntity.badRequest().body("Số buổi khóa học không được để trống");
        } else if (khoaHocDTO.getSoGio() == null) {
            return ResponseEntity.badRequest().body("Số giờ khóa học không được để trống");
        } else if(khoaHocDTO.getMoTa()==null){
            return ResponseEntity.badRequest().body("Không được để trống mô tả");
        }

        //Kiểm tra người dùng có tồn tại hay không trước khi đưa vào khóa học
        Nguoi_Dung nguoiDung=nguoiDungRepo.findById(idNguoiDUng).orElse(null);
        if(nguoiDung==null){
            return ResponseEntity.badRequest().body("Không tìm thấy người dùng có id: "+idNguoiDUng);
        }
        List<Nguoi_Dung> nguoiDungS=new ArrayList<>();
        nguoiDungS.add(nguoiDung);

        Khoa_Hoc kh = new Khoa_Hoc();
        kh.setTenKhoa(khoaHocDTO.getTenKhoa());
        kh.setSoBuoi(khoaHocDTO.getSoBuoi());
        kh.setSoGio(khoaHocDTO.getSoGio());
        kh.setMoTa(khoaHocDTO.getMoTa());
        kh.setNguoiDungs(nguoiDungS);
        khoaHocRepo.save(kh);
        return ResponseEntity.ok("Đã thêm mới thành công");

    }

    @Override
    public ResponseEntity<?> updateKhoaHoc(Integer id, KhoaHocDTO khoaHocDTO) {
        Khoa_Hoc kh = khoaHocRepo.findById(id).orElse(null);
        if (kh == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy khóa ọc có id: " + id);
        }
        if (khoaHocDTO.getTenKhoa() == null) {
            return ResponseEntity.badRequest().body("Không được để trống tên");
        } else if (khoaHocDTO.getSoBuoi() == null) {
            return ResponseEntity.badRequest().body("Số buổi khóa học không được để trống");
        } else if (khoaHocDTO.getSoGio() == null) {
            return ResponseEntity.badRequest().body("Số giờ khóa học không được để trống");
        }


        kh.setTenKhoa(khoaHocDTO.getTenKhoa());
        kh.setSoBuoi(khoaHocDTO.getSoBuoi());
        kh.setSoGio(khoaHocDTO.getSoGio());
        kh.setMoTa(khoaHocDTO.getMoTa());
        khoaHocRepo.save(kh);
        return ResponseEntity.ok("Đã cập nhật thành công");
    }

    @Override
    public ResponseEntity<?> deleteKhoaHoc(Integer id) {
            Khoa_Hoc khoaHoc = khoaHocRepo.findById(id).orElse(null);
            if (khoaHoc == null) {
                return ResponseEntity.badRequest().body("Không tìm thấy khóa học có id: " + id);
            }
            // Sử dụng Iterator để ngắt mối quan hệ an toàn (Mối quan hệ với bài học)
            Iterator<Bai_Hoc> iterator = khoaHoc.getBaiHocS().iterator();
            while (iterator.hasNext()) {
                Bai_Hoc baiHoc = iterator.next();
                baiHoc.setKhoaHocS(null);
                baiHocRepo.save(baiHoc); // Cập nhật từng bài học(Cập nhật thành null)
                iterator.remove(); // Xóa khỏi collection (không bắt buộc nếu chỉ ngắt liên kết)
            }

            khoaHocRepo.delete(khoaHoc);
            return ResponseEntity.ok("Đã xóa khóa học");
    }

    @Override
    public ResponseEntity<?> getKhoaHocByKeyWord(String tenKhoa, Pageable pageable) {
        List<Khoa_Hoc> khoaHocs = khoaHocRepo.findByTenKhoaContainingIgnoreCase(tenKhoa,pageable);
        if (khoaHocs.isEmpty()) {
            return ResponseEntity.badRequest().body("Không tìm thấy khóa học nào");
        }
        return ResponseEntity.ok(khoaHocs);
    }
}
