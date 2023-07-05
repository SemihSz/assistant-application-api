package com.earthquake.api.service.excel;

import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.model.ExcelFilterInput;
import com.assistant.commonapi.task.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Semih, 4.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelFilterListService implements Mappers<List<EarthQuakeInfo>, ExcelFilterInput, List<EarthQuakeInfo>> {

    @Override
    public List<EarthQuakeInfo> apply(List<EarthQuakeInfo> earthQuakeInfos, ExcelFilterInput input) {
        return earthQuakeInfos.stream()
                .filter(t -> input.getMinMagnitude() < t.getMagnitude())
                .filter(t -> input.getDepth() < t.getDepth())
                .limit((Objects.nonNull(input.getLimit()) && input.getLimit() != 0) ? input.getLimit() : 0)
                .collect(Collectors.toList());
    }
}
