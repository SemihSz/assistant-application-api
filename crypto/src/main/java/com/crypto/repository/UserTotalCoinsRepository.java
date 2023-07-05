package com.crypto.repository;

import com.crypto.entity.UserTotalCoins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTotalCoinsRepository extends JpaRepository<UserTotalCoins, UserTotalCoins.PK> {

	@Query(value = "SELECT * FROM user_total_coins t WHERE t.user_id=:userId" , nativeQuery = true)
	List<UserTotalCoins> getUserTotalCoins(@Param("userId")String userId);
}
