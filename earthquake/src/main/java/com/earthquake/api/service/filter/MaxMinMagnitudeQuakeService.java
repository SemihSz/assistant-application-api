package com.earthquake.api.service.filter;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeRequest;
import com.earthquake.api.service.cache.CacheService;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 13:55:03
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MaxMinMagnitudeQuakeService {

	private final MessageSource messageSource;

	private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

	private final UsageCountService usageCountService;


	@SneakyThrows
	public EarthQuakeInfo highestMagnitude(EarthQuakeRequest request) {

		try {

			EarthQuakeInfo highestMagnitude = null;
			final List<EarthQuakeInfo> cacheList = cacheService.get(request.getToken()).getData();
			final Comparator<EarthQuakeInfo> comparator = Comparator.comparing( EarthQuakeInfo::getMagnitude );
			if (cacheList != null) {
				highestMagnitude = cacheList.stream().max(comparator).get();
			}
			else {
				usageCountService.accept(Constant.Request.POWER_MAGNITUDE, ServiceUsageStatusType.FAIL);
				throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
			}

			usageCountService.accept(Constant.Request.POWER_MAGNITUDE, ServiceUsageStatusType.SUCCESS);

			return highestMagnitude;

		} catch (Exception e) {
			log.error(e.getMessage());
			usageCountService.accept(Constant.Request.POWER_MAGNITUDE, ServiceUsageStatusType.FAIL);
		}

		return null;
	}

	@SneakyThrows
	public EarthQuakeInfo lowestMagnitude(EarthQuakeRequest request) {

		try {

			EarthQuakeInfo lowestMagnitude = null;
			final Comparator<EarthQuakeInfo> comparator = Comparator.comparing( EarthQuakeInfo::getMagnitude );
			final List<EarthQuakeInfo> cacheList= cacheService.get(request.getToken()).getData();
			if (cacheList != null){
				lowestMagnitude = cacheList.stream().min(comparator).get();

			}
			else {
				usageCountService.accept(Constant.Request.POWER_MAGNITUDE, ServiceUsageStatusType.FAIL);
				throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
			}

			usageCountService.accept(Constant.Request.POWER_MAGNITUDE, ServiceUsageStatusType.SUCCESS);

			return lowestMagnitude;

		} catch (Exception e) {
			log.error(e.getMessage());
			usageCountService.accept(Constant.Request.POWER_MAGNITUDE, ServiceUsageStatusType.FAIL);
		}

		return null;
	}
}





