package com.earthquake.api.service.world;

import com.earthquake.api.request.world.WorldEarthQuakeRequest;
import com.earthquake.api.request.world.WorldEarthquakeQueryRequest;
import com.earthquake.api.response.world.WorldEarthQuakeGenericResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 18.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
public interface WorldEarthQuakeGeneralService {

    WorldEarthQuakeGenericResponse<?> worldGenericService(WorldEarthQuakeRequest request);

    WorldEarthQuakeGenericResponse<?> worldScheduleService();

    WorldEarthQuakeGenericResponse<?> worldEarthquakeQuery(WorldEarthquakeQueryRequest request);


}
