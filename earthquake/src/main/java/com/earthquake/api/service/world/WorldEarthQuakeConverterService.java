package com.earthquake.api.service.world;

import com.earthquake.api.model.world.Feature;
import com.earthquake.api.model.world.Root;
import com.earthquake.api.model.world.WorldEarthQuakeInformation;
import com.earthquake.api.model.world.WorldEarthQuakeSimpleModel;
import com.earthquake.api.shared.util.DateUtils;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semih, 19.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WorldEarthQuakeConverterService implements SimpleTask<Root, WorldEarthQuakeSimpleModel> {

    @Override
    public WorldEarthQuakeSimpleModel apply(Root root) {

        List<WorldEarthQuakeInformation> earthQuakeList = new ArrayList<>();

        final List<Feature> features = root.getFeatures();

        features.forEach(t -> {

            final WorldEarthQuakeInformation info = WorldEarthQuakeInformation.builder()
                    .id(t.getId())
                    .geometry(t.getGeometry())
                    .mag(t.getProperties().getMag())
                    .place(t.getProperties().getPlace())
                    .time(DateUtils.convertTimeStampToDate(t.getProperties().getTime().toString()))
                    .updated(t.getProperties().getUpdated())
                    .build() ;

            earthQuakeList.add(info);
        });


        return WorldEarthQuakeSimpleModel.builder()
                .earthQuakeList(earthQuakeList)
                .build();
    }
}
