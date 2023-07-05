package com.earthquake.api.service.usage;

import com.earthquake.api.entity.ServiceUsageEntity;
import com.earthquake.api.repository.ServiceUsageRepository;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Created by Semih, 16.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UsageCountService implements BiConsumer<String, ServiceUsageStatusType> {

    private final ServiceUsageRepository usageRepository;

    @Override
    public void accept(String s, ServiceUsageStatusType statusType) {

        final ServiceUsageEntity updateModel = usageRepository.service(s, statusType.toString());

        if (Objects.nonNull(updateModel)) {

            updateModel.setCount(updateModel.getCount() + 1);
            usageRepository.save(updateModel);
        }
        else {
            final ServiceUsageEntity newModel = ServiceUsageEntity.builder()
                    .serviceName(s)
                    .count(1L)
                    .status(statusType)
                    .build();
            usageRepository.save(newModel);
        }
    }
}
