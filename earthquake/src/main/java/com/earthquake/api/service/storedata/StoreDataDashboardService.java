package com.earthquake.api.service.storedata;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.entity.AFADEarthquakeEntity;
import com.earthquake.api.entity.KandilliEarthQuakeEntity;
import com.earthquake.api.model.StoreDbInfoInput;
import com.earthquake.api.response.DashboardResponse;
import com.earthquake.api.response.StoreDataDashboardResponse;
import com.earthquake.api.service.usage.UsageCountService;
import com.assistant.commonapi.task.Mappers;
import com.earthquake.api.type.Dashboard;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Semih, 6.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StoreDataDashboardService implements Mappers<StoreDbInfoInput, Dashboard, DashboardResponse> {

    private final UsageCountService usageCountService;

    @SneakyThrows
    @Override
    public DashboardResponse apply(StoreDbInfoInput input, Dashboard dashboard) {

        DashboardResponse response = new DashboardResponse();

        StoreDataDashboardResponse afad = new StoreDataDashboardResponse();
        StoreDataDashboardResponse kandilli = new StoreDataDashboardResponse();

        switch (dashboard) {

            case LOCATION:

                kandilli = locationCount(input);
                response.setKandilli(kandilli);

                usageCountService.accept(Constant.Request.DASHBOARD, ServiceUsageStatusType.SUCCESS);

                return response;

            case MAGNITUDE:


                kandilli.setLowerFromFiveMagnitudeCount(magnitudeLowerFromKandilli(input));
                kandilli.setHigherFromFiveMagnitudeCount(magnitudeHigherFromKandilli(input));

                afad.setHigherFromFiveMagnitudeCount(magnitudeHigherFromAfad(input));
                afad.setLowerFromFiveMagnitudeCount(magnitudeLowerFromAfad(input));

                response.setKandilli(kandilli);
                response.setAfad(afad);

                usageCountService.accept(Constant.Request.DASHBOARD, ServiceUsageStatusType.SUCCESS);

                return response;

            case DEPTH:

                kandilli.setLowerFromFiveMagnitudeCount(magnitudeLowerFromKandilli(input));
                kandilli.setHigherFromFiveMagnitudeCount(magnitudeHigherFromKandilli(input));

                afad.setDepthHigherTenKm(depthHigherTenKmAfad(input));
                afad.setDepthLowerTenKm(depthLowerTenKmKandilli(input));

                response.setKandilli(kandilli);
                response.setAfad(afad);

                usageCountService.accept(Constant.Request.DASHBOARD, ServiceUsageStatusType.SUCCESS);

                return response;

            case ALL:

                kandilli = locationCount(input);

                kandilli.setLowerFromFiveMagnitudeCount(magnitudeLowerFromKandilli(input));
                kandilli.setHigherFromFiveMagnitudeCount(magnitudeHigherFromKandilli(input));
                kandilli.setDepthLowerTenKm(depthLowerTenKmKandilli(input));
                kandilli.setDepthHigherTenKm(depthHigherTenKmKandilli(input));

                afad.setDepthHigherTenKm(depthHigherTenKmAfad(input));
                afad.setDepthLowerTenKm(depthLowerTenKmAfad(input));
                afad.setHigherFromFiveMagnitudeCount(magnitudeHigherFromAfad(input));
                afad.setLowerFromFiveMagnitudeCount(magnitudeLowerFromAfad(input));

                response.setAfad(afad);
                response.setKandilli(kandilli);

                usageCountService.accept(Constant.Request.DASHBOARD, ServiceUsageStatusType.SUCCESS);

                return response;

            default:
                log.error("Unaccepted state!!");
                usageCountService.accept(Constant.Request.DASHBOARD, ServiceUsageStatusType.SUCCESS);
                throw new Exception("Unaccepted state!!");

        }

    }

    private StoreDataDashboardResponse locationCount(StoreDbInfoInput storeDbInfoInput) {

        final List<KandilliEarthQuakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getKandilliList();
        Map<String, List<KandilliEarthQuakeEntity>> location = new HashMap<>();
        Map<String, Integer> countLocation = new HashMap<>();
        location = listData.stream().collect(Collectors.groupingBy(KandilliEarthQuakeEntity::getLocation));

        for (Map.Entry<String, List<KandilliEarthQuakeEntity>> entry : location.entrySet()) {
            String key = entry.getKey();
            List<KandilliEarthQuakeEntity> value = entry.getValue();
            countLocation.put(key, value.size());
        }

        StoreDataDashboardResponse response = new StoreDataDashboardResponse();
        response.setCountLocation(countLocation);
        response.setEarthQuakeLocationCount(location);

        return response;

    }

    // x > 4.5
    private Integer magnitudeHigherFromKandilli(StoreDbInfoInput storeDbInfoInput) {

        final List<KandilliEarthQuakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getKandilliList();

        return (int) listData.stream().filter(t -> t.getMagnitude() > 4.5).count();

    }

    // x < 4.5
    private Integer magnitudeLowerFromKandilli(StoreDbInfoInput storeDbInfoInput) {

        final List<KandilliEarthQuakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getKandilliList();

        return (int) listData.stream().filter(t -> t.getMagnitude() < 4.5).count();
    }

    // x < 10
    private Integer depthLowerTenKmKandilli(StoreDbInfoInput storeDbInfoInput) {

        final List<KandilliEarthQuakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getKandilliList();

        return (int) listData.stream().filter(t -> t.getDepth() < 10).count();
    }

    // x > 10
    private Integer depthHigherTenKmKandilli(StoreDbInfoInput storeDbInfoInput) {

        final List<KandilliEarthQuakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getKandilliList();

        return (int) listData.stream().filter(t -> t.getDepth() > 10).count();
    }


    // x > 4.5
    private Integer magnitudeHigherFromAfad(StoreDbInfoInput storeDbInfoInput) {

        final List<AFADEarthquakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getAfadList();

        return (int) listData.stream().filter(t -> t.getMagnitude() > 4.5).count();

    }

    // x < 4.5
    private Integer magnitudeLowerFromAfad(StoreDbInfoInput storeDbInfoInput) {

        final List<AFADEarthquakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getAfadList();

        return (int) listData.stream().filter(t -> t.getMagnitude() < 4.5).count();
    }

    // x < 10
    private Integer depthLowerTenKmAfad(StoreDbInfoInput storeDbInfoInput) {

        final List<AFADEarthquakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getAfadList();

        return (int) listData.stream().filter(t -> t.getDepth() < 10).count();
    }

    // x > 10
    private Integer depthHigherTenKmAfad(StoreDbInfoInput storeDbInfoInput) {

        final List<AFADEarthquakeEntity> listData = storeDbInfoInput.getAllEarthQuakeDbModel().getAfadList();

        return (int) listData.stream().filter(t -> t.getDepth() > 10).count();
    }

}
