package com.earthquake.api.service.world;

import com.earthquake.api.entity.WorldEarthQuakeEntity;
import com.earthquake.api.model.world.WorldEarthQuakeQueryList;
import com.earthquake.api.model.world.WorldEarthQuakeQueryModel;
import com.earthquake.api.repository.WorldEarthQuakeRepository;
import com.earthquake.api.request.world.WorldEarthquakeQueryRequest;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Semih, 21.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WorldEarthquakeQueryService implements SimpleTask<WorldEarthquakeQueryRequest, WorldEarthQuakeQueryModel> {

    private final WorldEarthQuakeRepository earthQuakeRepository;

    @Override
    public WorldEarthQuakeQueryModel apply(WorldEarthquakeQueryRequest worldEarthquakeQueryRequest) {

        List<WorldEarthQuakeQueryList> earthquakeList = new ArrayList<>();
        List<WorldEarthQuakeQueryList> queryListModel = new ArrayList<>();

        final Iterable<WorldEarthQuakeEntity> queryIterableList = earthQuakeRepository.findAll();

        queryIterableList.forEach(t -> {
            final WorldEarthQuakeQueryList listModel = WorldEarthQuakeQueryList.builder()
                    .earthquakeId(t.getEarthquakeId())
                    .date(t.getDate())
                    .latitude(t.getLatitude())
                    .longitude(t.getLongitude())
                    .location(t.getLocation())
                    .magnitude(t.getMagnitude())
                    .build();

            earthquakeList.add(listModel);

        });

        queryListModel = earthquakeList.stream()
                .filter(t -> {
                    if (Objects.nonNull(worldEarthquakeQueryRequest.getMagnitude())) {
                        return worldEarthquakeQueryRequest.getMagnitude() < t.getMagnitude();
                    }
                    return true;
                })
                .filter(t -> {
                    if (Objects.nonNull(worldEarthquakeQueryRequest.getEarthquakeId())) {
                        return t.getEarthquakeId().equals(worldEarthquakeQueryRequest.getEarthquakeId());
                    }
                    return true;
                })
                .limit(Objects.nonNull(worldEarthquakeQueryRequest.getLimit()) ? worldEarthquakeQueryRequest.getLimit() : Long.MAX_VALUE)
                .collect(Collectors.toList());

        return WorldEarthQuakeQueryModel.builder().queryListModel(queryListModel).build();
    }
}
