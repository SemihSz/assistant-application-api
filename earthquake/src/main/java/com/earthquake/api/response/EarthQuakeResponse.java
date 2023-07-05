package com.earthquake.api.response;

import com.earthquake.api.model.EarthQuakeInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarthQuakeResponse {

    private List<EarthQuakeInfo> quakeInfoList;

    private List<EarthQuakeInfo> afadQuakeList;
}
