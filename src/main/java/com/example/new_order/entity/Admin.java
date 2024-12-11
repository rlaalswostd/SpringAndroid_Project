package com.example.new_order.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId; // 관리자 고유번호

    @Column(name = "admin_name", nullable = false, length = 100)
    private String adminName; // 관리자명

    @Column(nullable = false, length = 100, unique = true)
    private String email; // 이메일

    @Column(nullable = false, length = 100)
    private String password; // 비밀번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 권한 (SUPER_ADMIN, STORE_ADMIN)


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt; // 생성일

    // Enum 타입 정의
    public enum Role {
        SUPER_ADMIN,
        STORE_ADMIN
    }
}
