package com.earthquake.api.service.world;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.model.world.Root;
import com.earthquake.api.model.world.WorldEarthQuakeModel;
import com.earthquake.api.model.world.WorldEarthQuakeQueryModel;
import com.earthquake.api.request.world.WorldEarthQuakeRequest;
import com.earthquake.api.request.world.WorldEarthquakeQueryRequest;
import com.earthquake.api.response.world.WorldEarthQuakeGenericResponse;
import com.earthquake.api.service.cache.CacheService;
import com.earthquake.api.service.cache.WorldEarthQuakeCacheService;
import com.earthquake.api.service.core.CoreWorldEarthQuakeService;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.type.InstitutionType;
import com.earthquake.api.type.SearchType;
import com.earthquake.api.type.ServiceUsageStatusType;
import com.earthquake.api.type.WorldEarthQuakeRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Semih, 18.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WorldEarthQuakeService implements WorldEarthQuakeGeneralService {

    private final CoreWorldEarthQuakeService coreWorldEarthQuakeService;

    private final WorldEarthQuakeConverterService converterService;

    private final CacheService<String, WorldEarthQuakeGenericResponse<WorldEarthQuakeModel>> worldEarthQuakeCache;

    private final WorldEarthQuakeCacheService worldEarthQuakeCacheService;

    private final UsageCountService usageCountService;

    private final WorldEarthquakeQueryService worldEarthquakeQueryService;

    private static final String LIMIT = "limit";

    private static final String ORDER = "orderby";

    private static final String MIN_MAGNITUDE = "minmagnitude";

    private static final String SCHEDULE_LIMIT = "500";

    private static final String SCHEDULE_ORDER_BY = "time";

    private static final Double SCHEDULE_MIN_MAGNITUDE = 0.5;


    @Override
    public WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> worldGenericService(WorldEarthQuakeRequest request) {

        WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> response = new WorldEarthQuakeGenericResponse<>();

        final WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> cacheResponse = worldEarthQuakeCacheService.apply(request);

        if (Objects.nonNull(cacheResponse)) {

            usageCountService.accept(Constant.Request.WORLD_EARTH_QUAKE_RESULT, ServiceUsageStatusType.SUCCESS);
            return cacheResponse;
        }

        switch (request.getRequestType()) {

            case LATEST:

                response.setResponse(generalEarthQuakeService(request));
                worldEarthQuakeCache.put(request.getToken(), response);

                usageCountService.accept(Constant.Request.WORLD_EARTH_QUAKE_RESULT, ServiceUsageStatusType.SUCCESS);

                return response;


            case SIGNIFICANT:

                response.setResponse(significantEarthQuakeService(request));
                worldEarthQuakeCache.put(request.getToken(), response);

                usageCountService.accept(Constant.Request.WORLD_EARTH_QUAKE_RESULT, ServiceUsageStatusType.SUCCESS);

                return response;

            default:

                usageCountService.accept(Constant.Request.WORLD_EARTH_QUAKE_RESULT, ServiceUsageStatusType.FAIL);
                log.error("Unknown Request Type");
                throw new RuntimeException("Unknown Request Type Error!");
        }

    }

    @Override
    public WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> worldScheduleService() {

        WorldEarthQuakeRequest request = new WorldEarthQuakeRequest();

        request.setInstitutionType(InstitutionType.USGS);
        request.setRequestType(WorldEarthQuakeRequestType.LATEST);
        request.setLimit(SCHEDULE_LIMIT);
        request.setMinMagnitude(SCHEDULE_MIN_MAGNITUDE);
        request.setOrderBy(SCHEDULE_ORDER_BY);
        request.setSearchType(SearchType.SIMPLE);

        WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> response = new WorldEarthQuakeGenericResponse<>();
        response.setResponse(generalEarthQuakeService(request));

        return response;
    }

    private WorldEarthQuakeModel generalEarthQuakeService(WorldEarthQuakeRequest request) {

        final Map<String, String> queryParams = new HashMap<>();

        queryParams.put(MIN_MAGNITUDE, request.getMinMagnitude().toString());
        queryParams.put(ORDER, request.getOrderBy());
        queryParams.put(LIMIT, request.getLimit());

        final Root worldEarthQuakeModel = coreWorldEarthQuakeService.generalService(queryParams);

        return getWorldEarthQuakeModel(request, worldEarthQuakeModel);

    }

    private WorldEarthQuakeModel significantEarthQuakeService(WorldEarthQuakeRequest request) {

        final Root worldEarthQuakeModel = coreWorldEarthQuakeService.significantService();

        return getWorldEarthQuakeModel(request, worldEarthQuakeModel);

    }

    private WorldEarthQuakeModel getWorldEarthQuakeModel(WorldEarthQuakeRequest request, Root worldEarthQuakeModel) {

        switch (request.getSearchType()) {

            case DETAIL:

                return WorldEarthQuakeModel.builder()
                        .detailModel(worldEarthQuakeModel)
                        .build();

            case SIMPLE:

                return WorldEarthQuakeModel.builder()
                        .simpleModel(converterService.apply(worldEarthQuakeModel))
                        .build();

            default:
                log.error("Unknown search type.");
                throw new RuntimeException("Unknown search type.");
        }
    }

    @Override
    public WorldEarthQuakeGenericResponse<WorldEarthQuakeQueryModel> worldEarthquakeQuery(WorldEarthquakeQueryRequest request) {

        WorldEarthQuakeGenericResponse<WorldEarthQuakeQueryModel> response = new WorldEarthQuakeGenericResponse<>();
        response.setResponse(worldEarthquakeQueryService.apply(request));

        return response;
    }
}
