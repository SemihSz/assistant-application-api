package com.earthquake.api.service.dashboard;

import com.earthquake.api.entity.AFADEarthquakeEntity;
import com.earthquake.api.entity.KandilliEarthQuakeEntity;
import com.earthquake.api.entity.WorldEarthQuakeEntity;
import com.earthquake.api.model.dashboard.DashboardDateModel;
import com.earthquake.api.model.dashboard.DashboardFilterInput;
import com.earthquake.api.model.dashboard.DashboardModel;
import com.earthquake.api.repository.AFADEarthquakeRepository;
import com.earthquake.api.repository.KandilliEarthQuakeRepository;
import com.earthquake.api.repository.WorldEarthQuakeRepository;
import com.earthquake.api.request.dashboard.DashboardRequest;
import com.earthquake.api.response.dashboard.DashboardResponse;
import com.earthquake.api.type.InstitutionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Semih, 29.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService implements GeneralDashboardService {

    private final AFADEarthquakeRepository afadEarthquakeRepository;

    private final KandilliEarthQuakeRepository kandilliEarthQuakeRepository;

    private final WorldEarthQuakeRepository worldEarthQuakeRepository;

    private final DashboardFilterService dashboardFilterService;

    @Override
    public DashboardResponse getEarthquakeDashboard(DashboardRequest request) {

        final Map<String, Long> earthquakeCounts = new HashMap<>();

        earthquakeCounts.put(InstitutionType.AFAD.toString(), afadEarthquakeRepository.count());
        earthquakeCounts.put(InstitutionType.KANDILLI.toString(), kandilliEarthQuakeRepository.count());
        earthquakeCounts.put(InstitutionType.USGS.toString(), worldEarthQuakeRepository.count());

        final Iterable<AFADEarthquakeEntity> dataAfad = afadEarthquakeRepository.findAll();
        final List<DashboardModel> dashboardAfadModel = dashboardAfadModel(dataAfad);

        final Iterable<KandilliEarthQuakeEntity> dataKandilli = kandilliEarthQuakeRepository.findAll();
        final List<DashboardModel> dashboardKandilliModel = dashboardKandilliModel(dataKandilli);

        final Iterable<WorldEarthQuakeEntity> dataWorld= worldEarthQuakeRepository.findAll();
        final List<DashboardModel> dashboardWorldModel = dashboardWorldModel(dataWorld);

        final DashboardFilterInput dashboardFilterInput = DashboardFilterInput.builder()
                .afad(dashboardAfadModel)
                .kandilli(dashboardKandilliModel)
                .world(dashboardWorldModel)
                .depth(request.getDepth())
                .magnitude(request.getMagnitude())
                .build();

        final DashboardResponse dashboardFilterResponse = dashboardFilterService.apply(dashboardFilterInput);

        return DashboardResponse.builder()
                .earthquakeCount(earthquakeCounts)
                .earthquakeDateModel(dashboardFilterResponse.getEarthquakeDateModel())
                .filterEarthquakeModel(dashboardFilterResponse.getFilterEarthquakeModel())
                .build();
    }

    private List<DashboardModel> dashboardAfadModel(Iterable<AFADEarthquakeEntity> data) {

        List<DashboardModel> returnList = new ArrayList<>();
        data.forEach(t -> {
            final LocalDate date = t.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            final DashboardDateModel dashboardDateModel = DashboardDateModel.builder()
                    .date(date)
                    .day(date.getDayOfMonth())
                    .year(date.getYear())
                    .month(date.getMonthValue())
                    .build();

            final DashboardModel dashboardModel = DashboardModel.builder()
                    .dateModel(dashboardDateModel)
                    .depth(t.getDepth())
                    .magnitude(t.getMagnitude())
                    .location(t.getLocation())
                    .build();

            returnList.add(dashboardModel);
        });

        return returnList;
    }

    private List<DashboardModel> dashboardKandilliModel(Iterable<KandilliEarthQuakeEntity> data) {

        final List<DashboardModel> returnList = new ArrayList<>();
        data.forEach(t -> {
            final LocalDate date = t.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            final DashboardDateModel dashboardDateModel = DashboardDateModel.builder()
                    .date(date)
                    .day(date.getDayOfMonth())
                    .year(date.getYear())
                    .month(date.getMonthValue())
                    .build();

            final DashboardModel dashboardModel = DashboardModel.builder()
                    .dateModel(dashboardDateModel)
                    .depth(t.getDepth())
                    .magnitude(t.getMagnitude())
                    .location(t.getLocation())
                    .build();

            returnList.add(dashboardModel);
        });

        return returnList;
    }

    private List<DashboardModel> dashboardWorldModel(Iterable<WorldEarthQuakeEntity> data) {

        List<DashboardModel> returnList = new ArrayList<>();
        data.forEach(t -> {
            final LocalDate date = t.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            final DashboardDateModel dashboardDateModel = DashboardDateModel.builder()
                    .date(date)
                    .day(date.getDayOfMonth())
                    .year(date.getYear())
                    .month(date.getMonthValue())
                    .build();

            final DashboardModel dashboardModel = DashboardModel.builder()
                    .dateModel(dashboardDateModel)
                    .magnitude(t.getMagnitude())
                    .location(t.getLocation())
                    .build();

            returnList.add(dashboardModel);
        });

        return returnList;
    }
}
