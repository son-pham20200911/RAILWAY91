package com.example.repository;

import com.example.Entity.Nguoi_Dung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends JpaRepository<Nguoi_Dung,Integer> {
   public Nguoi_Dung findByPassword(String password);
   public Nguoi_Dung findByUserName(String userName);
}
