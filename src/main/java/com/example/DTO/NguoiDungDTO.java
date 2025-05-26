package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NguoiDungDTO {
    private int id;
    private String fullName;
    private String address;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
    private String role;
    private String userName;
    private String password;


    public NguoiDungDTO() {
        this.ngayTao = LocalDate.now();
    }
}
