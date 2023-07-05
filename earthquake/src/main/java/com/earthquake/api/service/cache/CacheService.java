package com.earthquake.api.service.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 18:57:10
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
public class CacheService<T, Z> {

	@Value("${cache.expire.time}")
	private long cacheTime;

	public Cache<T, Z> cache;

	public CacheService(@Value("${cache.expire.time}") long cacheTime) {
		this.cacheTime = cacheTime;
		this.cache = Caffeine.newBuilder()
				.expireAfterWrite(cacheTime, TimeUnit.MINUTES)
				.maximumSize(100).build();

	}

	public Z get(T key) {
		return cache.getIfPresent(key);
	}

	public void put(T key, Z data) {
		this.cache.put(key, data);
	}

}
