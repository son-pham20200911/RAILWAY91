package com.example.DTO;

import com.example.Entity.Khoa_Hoc;
import lombok.Data;

@Data
public class BaiHocDTO {
    private Integer id;
    private String tenBai;
    private Integer soGio;
    private String moTa;
    private Khoa_Hoc khoahocs;
    private Integer idKhoahoc;

    public BaiHocDTO(){}
    public BaiHocDTO(Integer id, String tenBai){
        this.id = id;
        this.tenBai = tenBai;
    }
    public BaiHocDTO(Integer id, String tenBai, int soGio, String moTa,Khoa_Hoc khoahocs) {
        this.id = id;
        this.tenBai = tenBai;
        this.soGio = soGio;
        this.moTa = moTa;
        this.khoahocs=khoahocs;
    }
}
