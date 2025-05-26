package com.example.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Chung_Chi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ChungChi chungChi;
}
