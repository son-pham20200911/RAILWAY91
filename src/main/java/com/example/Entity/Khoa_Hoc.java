package com.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Khoa_Hoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tenKhoa;
    private Integer soBuoi;
    private Integer soGio;
    private String moTa;

    @OneToMany(mappedBy = "khoaHocS", cascade = CascadeType.ALL)
    @JsonIgnore // Ngăn vòng lặp bằng cách bỏ qua thuộc tính baiHocS khi serialize
    private List<Bai_Hoc> baiHocS;

    @ManyToMany
    @JoinTable(name="NguoiDung_KhoaHoc",
                joinColumns={@JoinColumn(name="Khoa_Hoc_ID")},
                inverseJoinColumns = {@JoinColumn(name="Nguoi_Dung_ID")})
    @JsonIgnore
    private List<Nguoi_Dung> nguoiDungs;


    public Khoa_Hoc() {
    }
    public Khoa_Hoc(String tenKhoa, Integer soBuoi, Integer soGio, String moTa, List<Bai_Hoc> baiHocS, List<Nguoi_Dung> nguoiDungs) {
        this.tenKhoa = tenKhoa;
        this.soBuoi = soBuoi;
        this.soGio = soGio;
        this.moTa = moTa;
        this.baiHocS = baiHocS;
        this.nguoiDungs = nguoiDungs;
    }
}
