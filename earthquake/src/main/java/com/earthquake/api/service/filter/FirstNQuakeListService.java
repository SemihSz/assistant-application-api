package com.earthquake.api.service.filter;

import com.earthquake.api.request.EarthQuakeRequest;
import com.earthquake.api.response.EarthQuakeResponse;
import com.assistant.commonapi.task.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author semih on Eylül, 2020, 24.09.2020, 21:40:56
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FirstNQuakeListService implements Mappers<EarthQuakeRequest, EarthQuakeResponse, EarthQuakeResponse> {

    @Override
    public EarthQuakeResponse apply(EarthQuakeRequest request, EarthQuakeResponse earthQuakeResponse) {

        switch (request.institutionType) {

            case KANDILLI:
                return EarthQuakeResponse.builder().quakeInfoList(earthQuakeResponse.getQuakeInfoList().stream().limit(request.getLimit()).collect(Collectors.toList())).build();

            case AFAD:
                return EarthQuakeResponse.builder().afadQuakeList(earthQuakeResponse.getAfadQuakeList().stream().limit(request.getLimit()).collect(Collectors.toList())).build();

            default:
                throw new RuntimeException("Resource not found error");

        }
    }
}
