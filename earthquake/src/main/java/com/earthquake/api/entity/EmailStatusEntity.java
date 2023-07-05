package com.earthquake.api.entity;

import com.earthquake.api.type.MailType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Semih, 12.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailStatusEntity {

    @Id
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private String email;

    @Enumerated(EnumType.STRING)
    private MailType mailType;
}
