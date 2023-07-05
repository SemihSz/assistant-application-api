package com.earthquake.api.service.filter;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.model.StoreDbInfoInput;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.service.usage.UsageCountService;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.InstitutionType;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


/**
 * Created by Semih, 4.01.2021
 * <p>This service provide filter db information.</p>
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StoreEarthQuakeInfoFilterService implements SimpleTask<StoreDbInfoInput, EarthQuakeResponse> {

    private final UsageCountService usageCountService;

    @Override
    public EarthQuakeResponse apply(StoreDbInfoInput input) {

        switch (input.getInstitutionType()) {

            case KANDILLI:

                List<EarthQuakeInfo> kList = new ArrayList<>();
                input.getAllEarthQuakeDbModel().getKandilliList().stream()
                        .filter(t-> {
                            if (Objects.nonNull(input.getMaxMagnitude())) {
                                return t.getMagnitude() > input.getMaxMagnitude();
                            }
                            return true;
                            })
                        .filter(t -> {
                            if (Objects.nonNull(input.getDepth())) {
                                return t.getDepth() > input.getDepth();
                            }
                            return true;
                        })
                        .filter(t -> {
                            if (Objects.nonNull(input.getLocation())) {
                                return input.getLocation().toUpperCase(Locale.ROOT).equals(t.getLocation());
                            }
                            return true;
                        })
                        .limit(Objects.nonNull(input.getLimit()) ? input.getLimit() : Long.MAX_VALUE)
                        .forEach(t -> {

                            final EarthQuakeInfo infoKandilli = EarthQuakeInfo.builder()
                                    .date(t.getDate())
                                    .latitude(t.getLatitude())
                                    .longitude(t.getLongitude())
                                    .locationName(t.getLocation())
                                    .depth(t.getDepth())
                                    .magnitude(t.getMagnitude()).build();

                            kList.add(infoKandilli);
                        });

                log.info("Kandilli Institution type ready!");
                usageCountService.accept(Constant.Request.STORE_DB_FILTER, ServiceUsageStatusType.SUCCESS);
                return EarthQuakeResponse.builder()
                        .quakeInfoList(kList)
                        .build();

            case AFAD:

                List<EarthQuakeInfo> aList = new ArrayList<>();
                input.getAllEarthQuakeDbModel().getAfadList().stream()
                        .filter(t -> {
                            if (Objects.nonNull(input.getMaxMagnitude())) {
                                return t.getMagnitude() > input.getMaxMagnitude();
                            }
                            return true;
                        })
                        .filter(t -> {
                            if (Objects.nonNull(input.getDepth())) {
                                return t.getDepth() > input.getDepth();
                            }
                            return true;
                        })
                        .forEach(t -> {

                            final EarthQuakeInfo infoKandilli = EarthQuakeInfo.builder()
                                    .date(t.getDate())
                                    .latitude(t.getLatitude())
                                    .longitude(t.getLongitude())
                                    .locationName(t.getLocation())
                                    .depth(t.getDepth())
                                    .magnitude(t.getMagnitude()).build();

                            aList.add(infoKandilli);
                        });

                log.info("Afad Institution type ready!");
                usageCountService.accept(Constant.Request.STORE_DB_FILTER, ServiceUsageStatusType.SUCCESS);
                return EarthQuakeResponse.builder()
                        .afadQuakeList(aList)
                        .build();

            case ALL:

                EarthQuakeResponse response = new EarthQuakeResponse();

                input.setInstitutionType(InstitutionType.KANDILLI);
                response.setQuakeInfoList(this.apply(input).getQuakeInfoList());
                input.setInstitutionType(InstitutionType.AFAD);
                response.setAfadQuakeList(this.apply(input).getAfadQuakeList());
                log.info("All Institution type ready!");
                usageCountService.accept(Constant.Request.STORE_DB_FILTER, ServiceUsageStatusType.SUCCESS);
                return response;

            default:
                usageCountService.accept(Constant.Request.STORE_DB_FILTER, ServiceUsageStatusType.FAIL);
                log.error("Unknown Institution Type");
                break;

        }

        usageCountService.accept(Constant.Request.STORE_DB_FILTER, ServiceUsageStatusType.FAIL);

        return null;
    }

}
