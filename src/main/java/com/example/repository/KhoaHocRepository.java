package com.example.repository;

import com.example.Entity.Khoa_Hoc;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhoaHocRepository extends JpaRepository<Khoa_Hoc,Integer> {
     Optional<Khoa_Hoc>findByTenKhoa(String tenKhoa);
     List<Khoa_Hoc>findByTenKhoaContainingIgnoreCase(String tenKhoa, Pageable pageable); //Tìm theo từ khóa không phân biệt chữ hoa chữ thường
}
