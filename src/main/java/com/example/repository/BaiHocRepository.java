package com.example.repository;

import com.example.Entity.Bai_Hoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiHocRepository extends JpaRepository<Bai_Hoc,Integer> {

}
