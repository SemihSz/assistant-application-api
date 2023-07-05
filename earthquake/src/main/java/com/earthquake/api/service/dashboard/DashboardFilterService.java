package com.earthquake.api.service.dashboard;

import com.earthquake.api.model.dashboard.DashboardFilterInput;
import com.earthquake.api.model.dashboard.DashboardModel;
import com.earthquake.api.model.dashboard.EarthquakeDateModel;
import com.earthquake.api.model.dashboard.FilterEarthquakeModel;
import com.earthquake.api.response.dashboard.*;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.DateFilterType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Semih, 30.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardFilterService implements SimpleTask<DashboardFilterInput, DashboardResponse> {

    @Override
    public DashboardResponse apply(DashboardFilterInput input) {

        final Map<String, Integer> afad = monthlyEarthquake(input.getAfad());
        final Map<String, Integer> kandilli = monthlyEarthquake(input.getKandilli());
        final Map<String, Integer> world = monthlyEarthquake(input.getWorld());

        final EarthquakeDateModel earthquakeDateModel = EarthquakeDateModel.builder()
                .afad(afad)
                .kandilli(kandilli)
                .world(world)
                .build();

        final EarthquakeDateModel yearlyFilterMagnitude = filterMagnitude(input, DateFilterType.YEAR);
        final EarthquakeDateModel monthlyFilterMagnitude = filterMagnitude(input, DateFilterType.MONTH);
        final EarthquakeDateModel dailyFilterMagnitude = filterMagnitude(input, DateFilterType.DAILY);

        final EarthquakeDateModel yearlyFilterDepth = filterMDepth(input, DateFilterType.YEAR);
        final EarthquakeDateModel monthlyFilterDepth = filterMDepth(input, DateFilterType.MONTH);
        final EarthquakeDateModel dailyFilterDepth = filterMDepth(input, DateFilterType.DAILY);

        final FilterEarthquakeModel filterEarthquakeModel = FilterEarthquakeModel.builder()
                .magnitudeYearAfad(yearlyFilterMagnitude.getAfad())
                .magnitudeYearWorld(yearlyFilterMagnitude.getWorld())
                .magnitudeYearKandilli(yearlyFilterMagnitude.getKandilli())
                .magnitudeMonthKandilli(monthlyFilterMagnitude.getKandilli())
                .magnitudeMonthAfad(monthlyFilterMagnitude.getAfad())
                .magnitudeMonthWorld(monthlyFilterMagnitude.getWorld())
//                .magnitudeDailyWorld(dailyFilterMagnitude.getWorld())
//                .magnitudeDailyAfad(dailyFilterMagnitude.getAfad())
//                .magnitudeDailyKandilli(dailyFilterMagnitude.getKandilli())
                .depthYearAfad(yearlyFilterDepth.getAfad())
                .depthYearKandilli(yearlyFilterDepth.getKandilli())
                .depthYearWorld(yearlyFilterDepth.getWorld())
                .depthMonthAfad(monthlyFilterDepth.getAfad())
                .depthMonthKandilli(monthlyFilterDepth.getKandilli())
                .depthMonthWorld(monthlyFilterDepth.getWorld())
//                .depthDailyAfad(dailyFilterDepth.getAfad())
//                .depthDailyKandilli(dailyFilterDepth.getKandilli())
//                .depthDailyWorld(dailyFilterDepth.getWorld())
                .build();

        return DashboardResponse.builder()
                .earthquakeDateModel(earthquakeDateModel)
                .filterEarthquakeModel(filterEarthquakeModel)
                .build();
    }

    private EarthquakeDateModel filterMagnitude(DashboardFilterInput input, DateFilterType dateFilterType) {

        final List<DashboardModel> filterAfadList = input.getAfad().stream().filter(t -> {
           if (Objects.nonNull(input.getMagnitude())) {
               return t.getMagnitude() > input.getMagnitude();
           }
            return true;
        }).sorted(Comparator.comparing(t -> t.getDateModel().getDate())).collect(Collectors.toList());

        final List<DashboardModel> filterKandilliList = input.getKandilli().stream().filter(t -> {
            if (Objects.nonNull(input.getMagnitude())) {
                return t.getMagnitude() > input.getMagnitude();
            }
            return true;
        }).sorted(Comparator.comparing(t -> t.getDateModel().getDate())).collect(Collectors.toList());

        final List<DashboardModel> filterWorldList = input.getWorld().stream().filter(t -> {
            if (Objects.nonNull(input.getMagnitude())) {
                return t.getMagnitude() > input.getMagnitude();
            }
            return true;
        }).collect(Collectors.toList());

        final Map<String, Integer> afad = DateFilterType.MONTH.equals(dateFilterType) ? monthlyEarthquake(filterAfadList) :
                (DateFilterType.YEAR.equals(dateFilterType) ? yearlyEarthquake(filterAfadList) : dailyEarthquake(filterAfadList));
        final Map<String, Integer> kandilli = DateFilterType.MONTH.equals(dateFilterType) ? monthlyEarthquake(filterKandilliList) :
                (DateFilterType.YEAR.equals(dateFilterType) ? yearlyEarthquake(filterKandilliList) : dailyEarthquake(filterKandilliList));
        final Map<String, Integer> world = DateFilterType.MONTH.equals(dateFilterType) ? monthlyEarthquake(filterWorldList) :
                (DateFilterType.YEAR.equals(dateFilterType) ? yearlyEarthquake(filterWorldList) : dailyEarthquake(filterWorldList));

        return EarthquakeDateModel.builder()
                .world(world)
                .afad(afad)
                .kandilli(kandilli)
                .build();

    }

    private EarthquakeDateModel filterMDepth(DashboardFilterInput input, DateFilterType dateFilterType) {

        final List<DashboardModel> filterAfadList = input.getAfad().stream().filter(t -> {
            if (Objects.nonNull(input.getDepth())) {
                return t.getDepth() > input.getDepth();
            }
            return true;
        }).collect(Collectors.toList());

        final List<DashboardModel> filterKandilliList = input.getKandilli().stream().filter(t -> {
            if (Objects.nonNull(input.getDepth())) {
                return t.getDepth() > input.getDepth();
            }
            return true;
        }).collect(Collectors.toList());

        final List<DashboardModel> filterWorldList = input.getWorld().stream().filter(t -> {
            if (Objects.nonNull(input.getDepth())) {
                return t.getDepth() > input.getDepth();
            }
            return true;
        }).collect(Collectors.toList());

        final Map<String, Integer> afad = DateFilterType.MONTH.equals(dateFilterType) ? monthlyEarthquake(filterAfadList) :
                (DateFilterType.YEAR.equals(dateFilterType) ? yearlyEarthquake(filterAfadList) : dailyEarthquake(filterAfadList));
        final Map<String, Integer> kandilli = DateFilterType.MONTH.equals(dateFilterType) ? monthlyEarthquake(filterKandilliList) :
                (DateFilterType.YEAR.equals(dateFilterType) ? yearlyEarthquake(filterKandilliList) : dailyEarthquake(filterKandilliList));
        final Map<String, Integer> world = DateFilterType.MONTH.equals(dateFilterType) ? monthlyEarthquake(filterWorldList) :
                (DateFilterType.YEAR.equals(dateFilterType) ? yearlyEarthquake(filterWorldList) : dailyEarthquake(filterWorldList));

        return EarthquakeDateModel.builder()
                .world(world)
                .afad(afad)
                .kandilli(kandilli)
                .build();

    }

    private Map<String, Integer> monthlyEarthquake(List<DashboardModel> input) {

        Map<String, List<DashboardModel>> hashMap = new HashMap<>();
        hashMap = input.stream().collect(Collectors.groupingBy(a -> a.getDateModel().getDate().toString()));

        Map<LocalDate, Integer> resultList = new HashMap<>();

        for (Map.Entry<String, List<DashboardModel>> entry : hashMap.entrySet()) {
            LocalDate key = LocalDate.parse(entry.getKey());
            int count = entry.getValue().size();
            resultList.put(key, count);
        }

        Map<String, Integer> monthlyReturn = new HashMap<>();

        for (Map.Entry<LocalDate, Integer> entry : resultList.entrySet()) {
            final LocalDate date = entry.getKey();
            int count = entry.getValue();
            final String key = date.getMonthValue() + " - " + date.getYear();

            final boolean controlMap = monthlyReturn.containsKey(key);

            if (controlMap) {
                final Integer getValue = monthlyReturn.get(key);
                final Integer newValue = entry.getValue();
                monthlyReturn.put(key, getValue + newValue);
            }
            else {
                monthlyReturn.put(key, count);
            }
        }

        return monthlyReturn;
    }

    private Map<String, Integer> yearlyEarthquake(List<DashboardModel> input) {

        Map<String, List<DashboardModel>> hashMap = new HashMap<>();
        hashMap = input.stream().collect(Collectors.groupingBy(a -> a.getDateModel().getDate().toString()));

        Map<LocalDate, Integer> resultList = new HashMap<>();

        for (Map.Entry<String, List<DashboardModel>> entry : hashMap.entrySet()) {
            LocalDate key = LocalDate.parse(entry.getKey());
            int count = entry.getValue().size();
            resultList.put(key, count);
        }

        Map<String, Integer> yearlyReturn = new HashMap<>();

        for (Map.Entry<LocalDate, Integer> entry : resultList.entrySet()) {
            final LocalDate date = entry.getKey();
            int count = entry.getValue();
            final String key = String.valueOf(date.getYear());

            final boolean controlMap = yearlyReturn.containsKey(key);

            if (controlMap) {
                final Integer getValue = yearlyReturn.get(key);
                final Integer newValue = entry.getValue();
                yearlyReturn.put(key, getValue + newValue);
            }
            else {
                yearlyReturn.put(key, count);
            }
        }

        return yearlyReturn;
    }

    private Map<String, Integer> dailyEarthquake(List<DashboardModel> input) {

        Map<String, List<DashboardModel>> hashMap = new HashMap<>();
        hashMap = input.stream().collect(Collectors.groupingBy(a -> a.getDateModel().getDate().toString()));

        Map<LocalDate, Integer> resultList = new HashMap<>();

        for (Map.Entry<String, List<DashboardModel>> entry : hashMap.entrySet()) {
            LocalDate key = LocalDate.parse(entry.getKey());
            int count = entry.getValue().size();
            resultList.put(key, count);
        }

        Map<String, Integer> yearlyReturn = new HashMap<>();

        for (Map.Entry<LocalDate, Integer> entry : resultList.entrySet()) {
            final LocalDate date = entry.getKey();
            int count = entry.getValue();
            final String key = date.getDayOfMonth() + " - " + date.getMonth().toString();

            final boolean controlMap = yearlyReturn.containsKey(key);

            if (controlMap) {
                final Integer getValue = yearlyReturn.get(key);
                final Integer newValue = entry.getValue();
                yearlyReturn.put(key, getValue + newValue);
            }
            else {
                yearlyReturn.put(key, count);
            }
        }

        return yearlyReturn;
    }


}
