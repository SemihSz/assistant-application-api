package com.crypto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author semih on Eylül, 2021, 25.09.2021, 21:06:30
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(CryptoTokenAuthEntity.PK.class)
public class CryptoTokenAuthEntity {

	@Id // TODO hash ile save işlemi yapılmalı
	private String hashToken;

	@Id
	private String userId;

	private LocalDateTime createdDate;

	private Long numberOfUsage;

	@EqualsAndHashCode
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PK implements Serializable {

		private String hashToken;

		private String userId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CryptoTokenAuthEntity that = (CryptoTokenAuthEntity) o;
		return Objects.equals(hashToken, that.hashToken) &&
				Objects.equals(userId, that.userId) &&
				Objects.equals(createdDate, that.createdDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hashToken, userId, createdDate);
	}
}
