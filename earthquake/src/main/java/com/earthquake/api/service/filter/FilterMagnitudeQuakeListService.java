package com.earthquake.api.service.filter;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.EarthQuakeRequest;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.service.cache.CacheService;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import com.earthquake.api.service.usage.UsageCountService;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 12:24:16
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FilterMagnitudeQuakeListService implements SimpleTask<EarthQuakeRequest, EarthQuakeResponse> {

	private final MessageSource messageSource;

	private final CacheService<String, GenericCacheModel<List<EarthQuakeInfo>>> cacheService;

	private final UsageCountService usageCountService;


	@SneakyThrows
	@Override
	public EarthQuakeResponse apply(EarthQuakeRequest request) {

		try {

			final List<EarthQuakeInfo> cacheList = cacheService.get(request.getToken()).getData();
			if (cacheList != null) {
				usageCountService.accept(Constant.Request.FIRST_N_LIST, ServiceUsageStatusType.SUCCESS);
				return EarthQuakeResponse.builder()
						.quakeInfoList(cacheList.stream()
								.filter(t -> request.getMinMagnitude() < t.getMagnitude())
								.limit((Objects.nonNull(request.getLimit()) && request.getLimit() != 0) ? request.getLimit() : 0)
								.sorted(Comparator.comparing(EarthQuakeInfo::getMagnitude))
								.collect(Collectors.toList())).build();
			}
			else {
				usageCountService.accept(Constant.Request.FILTER_MAGNITUDE_LIST, ServiceUsageStatusType.FAIL);
				throw new TokenException(messageSource.getMessage(Constant.Message.TOKEN_EXPIRE, null, Locale.ENGLISH));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			usageCountService.accept(Constant.Request.FILTER_MAGNITUDE_LIST, ServiceUsageStatusType.FAIL);
		}

		return null;
	}
}
