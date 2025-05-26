package com.example.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Bai_Hoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tenBai;
    private Integer soGio;
    private String moTa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="IDKhoaHoc")
    private Khoa_Hoc khoaHocS;

    public Bai_Hoc(){}
    public Bai_Hoc( String tenBai, int soGio, String moTa, Khoa_Hoc khoaHocS) {
        this.tenBai = tenBai;
        this.soGio = soGio;
        this.moTa = moTa;
        this.khoaHocS = khoaHocS;
    }
}
