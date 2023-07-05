package com.earthquake.api.service.config;

import com.earthquake.api.entity.ConfigEntity;
import com.earthquake.api.model.ConfigModel;
import com.earthquake.api.repository.ConfigRepository;
import com.earthquake.api.request.admin.AdminConfigRequest;
import com.earthquake.api.response.admin.AdminConfigResponse;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.SqlStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 20.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigAddOrUpdateService implements SimpleTask<AdminConfigRequest, AdminConfigResponse> {

    private final GetConfigsService getConfigsService;

    private final ConfigRepository configRepository;

    @Override
    public AdminConfigResponse apply(AdminConfigRequest request) {

        final ConfigModel configModel = getConfigsService.apply(request.getParameterName());

        if (Objects.nonNull(request.getParameterName()) && Objects.nonNull(request.getValue())
                && Objects.nonNull(request.getSecondValue())) {

            if (Objects.nonNull(configModel)) {

                final ConfigEntity configEntity = ConfigEntity.builder()
                        .parameterName(configModel.getParameterName())
                        .secondValue(configModel.getSecondValue())
                        .value(request.getValue())
                        .build();

                configRepository.save(configEntity);

                return AdminConfigResponse.builder()
                        .configStatus(SqlStatusType.UPDATE.toString())
                        .build();
            }
            else {

                final ConfigEntity configEntity = ConfigEntity.builder()
                        .parameterName(request.getParameterName())
                        .value(request.getValue())
                        .secondValue(request.getSecondValue())
                        .build();

                configRepository.save(configEntity);

                return AdminConfigResponse.builder()
                        .configStatus(SqlStatusType.ADD.toString())
                        .build();
            }
        }

        return null;
    }
}
