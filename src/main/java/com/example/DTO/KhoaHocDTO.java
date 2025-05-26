package com.example.DTO;

import com.example.Entity.Bai_Hoc;
import com.example.Entity.Nguoi_Dung;
import lombok.Data;

import java.util.List;

@Data
public class KhoaHocDTO {
    private Integer id;
    private String tenKhoa;
    private Integer soBuoi;
    private Integer soGio;
    private String moTa;
    //private List<Bai_Hoc> baiHoc;
    private List<Nguoi_Dung> nguoiDung;


    public KhoaHocDTO() {
    }
    public KhoaHocDTO(Integer id, String tenKhoa) {
        this.id = id;
        this.tenKhoa = tenKhoa;
    }
    public KhoaHocDTO(Integer id, String tenKhoa, int soBuoi, int soGio, String moTa, List<Nguoi_Dung> nguoiDung) {
        this.id = id;
        this.tenKhoa = tenKhoa;
        this.soBuoi = soBuoi;
        this.soGio = soGio;
        this.moTa = moTa;
        this.nguoiDung=nguoiDung;
    }
}
