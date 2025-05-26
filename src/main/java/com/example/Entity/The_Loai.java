package com.example.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class The_Loai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tenTheLoai;
}
