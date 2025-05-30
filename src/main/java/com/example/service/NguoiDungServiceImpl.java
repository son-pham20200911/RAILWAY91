package com.example.service;

import com.example.DTO.NguoiDungDTO;
import com.example.Entity.Nguoi_Dung;
//import com.example.config.ConfigSecurity;
import com.example.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {
    private NguoiDungRepository nguoiDungRepo;
             private PasswordEncoder passwordEncoder;

    @Autowired
    public NguoiDungServiceImpl(NguoiDungRepository nguoiDungRepo) {
        this.nguoiDungRepo = nguoiDungRepo;
        this.passwordEncoder=passwordEncoder;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Nguoi_Dung nguoiDung=nguoiDungRepo.findByUserName(username);
        if(nguoiDung==null){
            throw new UsernameNotFoundException("Sai UserName");
        }
        //Collections.emptyList(): Đăng nhập thành công
        // Chuyển đổi role thành GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(nguoiDung.getRole())); // Giả sử getRole() trả về "ROLE_ADMIN" hoặc "ROLE_CUSTOMER"

        return new User(nguoiDung.getUserName(), nguoiDung.getPassword(), authorities);
    }

    @Override
    public ResponseEntity<?> dangKy(NguoiDungDTO nguoiDungDTO) {
        String pass = nguoiDungDTO.getPassword();
        String userName = nguoiDungDTO.getUserName();
        Optional<Nguoi_Dung> opNguoiDung = Optional.ofNullable(nguoiDungRepo.findByPassword(pass));
        Optional<Nguoi_Dung> opNguoiDung2 = Optional.ofNullable(nguoiDungRepo.findByUserName(userName));
        if (opNguoiDung.isPresent()) {
            return ResponseEntity.badRequest().body("PassWord này đã tồn tại");
        } else if (opNguoiDung2.isPresent()) {
            return ResponseEntity.badRequest().body("UserName này đã tồn tại");
        }
        if (nguoiDungDTO.getFullName() == null) {
            return ResponseEntity.badRequest().body("Không được để trống FullName");
        } else if (nguoiDungDTO.getDateOfBirth() == null) {
            return ResponseEntity.badRequest().body("Không được để trống Ngày Sinh");
        } else if (nguoiDungDTO.getAddress() == null) {
            return ResponseEntity.badRequest().body("Không được để trống Địa Chỉ");
        } else if(nguoiDungDTO.getEmail()==null){
            return ResponseEntity.badRequest().body("Không được để trống Email");
        }


        Nguoi_Dung nguoiDung = new Nguoi_Dung();
        nguoiDung.setFullName(nguoiDungDTO.getFullName());
        nguoiDung.setAddress(nguoiDungDTO.getAddress());
        nguoiDung.setDateOfBirth(nguoiDungDTO.getDateOfBirth());
        nguoiDung.setNgayTao(nguoiDungDTO.getNgayTao());
        nguoiDung.setNgayCapNhat(nguoiDungDTO.getNgayCapNhat());
        nguoiDung.setEmail(nguoiDungDTO.getEmail());
        nguoiDung.setRole(nguoiDungDTO.getRole());
        nguoiDung.setUserName(nguoiDungDTO.getUserName());
        // MÃ HÓA PASSWORD trước khi lưu vào database
        nguoiDung.setPassword(this.passwordEncoder.encode(nguoiDungDTO.getPassword()));
        nguoiDung.setPassword(nguoiDungDTO.getPassword());

        try {
            nguoiDungRepo.save(nguoiDung);
            return ResponseEntity.ok("Đăng ký thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }

}
