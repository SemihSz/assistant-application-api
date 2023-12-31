package com.earthquake.api.entity;

import com.earthquake.api.type.AdminSaveStatusType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by Semih, 23.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity {

    @Id
    private String userEmail; // Kullanıcı email kesinlikle olmalı primary key

    private String passwordHash; // kullanıcı şifre

    private String confirmPasswordHash; // şifre tekrarı

    private String firstTokenHash; // Yeni kullanıcı oluşturmak için bu gerekli

    private String secondTokenHash; // Yeni kullanıcı oluşturmak için bu gerekli

    private String autoGeneratedTokenHash; // 123456 gönderilecek fakat db'de hash tutulacak

    private LocalDateTime crtDate;

    @Enumerated(EnumType.STRING)
    private AdminSaveStatusType status;
}
