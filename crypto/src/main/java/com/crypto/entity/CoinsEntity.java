package com.crypto.entity;

import com.crypto.type.GenerateRepoLocationType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Semih, 20.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoinsEntity {

    // TODO Coins Entity yapısına gerek kalmadan tek bir yapı içerisinde yapabilinir. Bu Db tarafından sorgularla yönetilir.

    @Id
    private String coin;

    private LocalDateTime createdDate;

    private Boolean isRepository;

    private LocalDateTime repositoryCreatedDate;

    @Enumerated(EnumType.STRING)
    private GenerateRepoLocationType repoLocationType;

    private String repositoryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoinsEntity)) return false;
        CoinsEntity that = (CoinsEntity) o;
        return Objects.equals(coin, that.coin) && Objects.equals(createdDate, that.createdDate) && Objects.equals(isRepository, that.isRepository) && Objects.equals(repositoryCreatedDate, that.repositoryCreatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coin, createdDate, isRepository, repositoryCreatedDate);
    }
}
