package com.earthquake.api.service.distance;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeDistanceRequest;
import com.earthquake.api.response.EarthQuakeDistanceResponse;
import com.earthquake.api.service.cache.CacheService;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.shared.util.CalculateDistance;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 22:09:46
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DistanceCalculatorService implements SimpleTask<EarthQuakeDistanceRequest, EarthQuakeDistanceResponse> {

	private final MessageSource messageSource;

	private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

	private final UsageCountService usageCountService;

	@Override
	public EarthQuakeDistanceResponse apply(EarthQuakeDistanceRequest request) {

		final List<EarthQuakeInfo> cacheList = cacheService.get(request.getToken()).getData();

		if (cacheList != null) {
			final EarthQuakeInfo info = cacheList.stream().filter(t -> request.getId().equals(t.getId())).collect(Collectors.toList()).get(0);
			usageCountService.accept(Constant.Request.CALCULATE_DISTANCE, ServiceUsageStatusType.SUCCESS);
			return EarthQuakeDistanceResponse.builder()
					.distance(CalculateDistance.calculateDistance(request.getLatitude(), request.getLongitude(),
							info.getLatitude(), info.getLongitude()))
					.id(info.getId())
					.quakeLatitude(info.getLatitude())
					.quakeLongitude(info.getLongitude())
					.build();
		}

		usageCountService.accept(Constant.Request.CALCULATE_DISTANCE, ServiceUsageStatusType.SUCCESS);
		throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
	}

}
