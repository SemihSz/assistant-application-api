package com.earthquake.api.service.config;

import com.earthquake.api.entity.ConfigEntity;
import com.earthquake.api.model.ConfigModel;
import com.earthquake.api.repository.ConfigRepository;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by Semih, 11.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetConfigsService implements SimpleTask<String, ConfigModel> {

    private final ConfigRepository configRepository;

    @Override
    public ConfigModel apply(String filter) {
        Iterable<ConfigEntity> configEntityList = configRepository.findAll();
        List<ConfigEntity> list = new ArrayList<>();
        configEntityList.forEach(list::add);

        Optional<ConfigEntity> optionalConfigEntity =
                list.stream().filter(t -> t.getParameterName().equals(filter)).findAny();

        return optionalConfigEntity.map(configEntity -> ConfigModel.builder()
                .parameterName(configEntity.getParameterName())
                .value(configEntity.getValue())
                .secondValue(configEntity.getSecondValue())
                .build()).orElse(null);

    }
}
