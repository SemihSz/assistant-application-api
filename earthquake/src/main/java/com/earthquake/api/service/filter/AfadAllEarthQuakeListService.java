package com.earthquake.api.service.filter;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.response.CoreAFADResponse;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.service.token.TokenService;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Semih, 4.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AfadAllEarthQuakeListService implements SimpleTask<List<CoreAFADResponse>, EarthQuakeResponse> {

    private final TokenService tokenService;

    @Override
    public EarthQuakeResponse apply(List<CoreAFADResponse> coreAFADResponses) {

        try {

            List<EarthQuakeInfo> quakeInfoList = new ArrayList<>();

            coreAFADResponses.forEach(t -> {
                try {
                    final DateFormat formatter = new SimpleDateFormat(Constant.AFAD_DATE_FORMAT);
                    final Date earthQuakeDate = formatter.parse(t.getTime());
                    final EarthQuakeInfo earthQuakeInfo = EarthQuakeInfo.builder()
                            .id(tokenService.generateId())
                            .date(earthQuakeDate)
                            .depth(Double.parseDouble(t.getDepth()))
                            .latitude(Double.parseDouble(t.getLatitude()))
                            .longitude(Double.parseDouble(t.getLongitude()))
                            .magnitude(Double.parseDouble(t.getMagnitude()))
                            .locationName(t.getOther()).build();

                    quakeInfoList.add(earthQuakeInfo);
                }
                catch (ParseException s) {
                    log.error(s.getMessage());
                }
            });

            return EarthQuakeResponse.builder().afadQuakeList(quakeInfoList).build();
        }
        catch (Exception e) {
            log.error("Afad core connection parse error, {}" , e.getMessage());
        }

        return null;
    }
}
