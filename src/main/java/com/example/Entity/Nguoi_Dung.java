package com.example.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
public class Nguoi_Dung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int id;
    @Column(name = "Full_Name")
    private String fullName;

    @Column(name = "Address")
    private String address;

    @Column(name = "Date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "Email")
    private String email;

    @Column(name = "Ngay_Tao")
    private LocalDate ngayTao;

    @Column(name = "Ngay_Cap_Nhat")
    private LocalDate ngayCapNhat;

    @Column(name = "Role")
    private String role;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "Password")
    private String password;

    @ManyToMany(mappedBy = "nguoiDungs")
    private List<Khoa_Hoc> khoaHocs;

    public Nguoi_Dung() {
    }

    public Nguoi_Dung(String fullName, String address, LocalDate dateOfBirth, String email, LocalDate ngayTao, LocalDate ngayCapNhat, String role, String userName, String password, List<Khoa_Hoc> khoaHocs) {
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.khoaHocs = khoaHocs;
    }
}
