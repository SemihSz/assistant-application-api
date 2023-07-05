package com.earthquake.api.service.filter;

import com.earthquake.api.entity.AFADEarthquakeEntity;
import com.earthquake.api.entity.KandilliEarthQuakeEntity;
import com.earthquake.api.model.AllEarthQuakeDbModel;
import com.earthquake.api.repository.AFADEarthquakeRepository;
import com.earthquake.api.repository.KandilliEarthQuakeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semih, 6.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StoreEarthQuakeService {

    private final AFADEarthquakeRepository afadEarthquakeRepository;

    private final KandilliEarthQuakeRepository kandilliEarthQuakeRepository;

    public List<AFADEarthquakeEntity> afadListDataFromDatabase() {

        final Iterable<AFADEarthquakeEntity> data = afadEarthquakeRepository.findAll();
        List<AFADEarthquakeEntity> afadShowResultList = new ArrayList<>();
        data.forEach(afadShowResultList::add);

        return afadShowResultList;
    }

    public List<KandilliEarthQuakeEntity> kandilliListDataFromDatabase() {

        final Iterable<KandilliEarthQuakeEntity> data = kandilliEarthQuakeRepository.findAll();
        List<KandilliEarthQuakeEntity> showResultList = new ArrayList<>();
        data.forEach(showResultList::add);

        return showResultList;
    }

    public AllEarthQuakeDbModel bothEarthQuakeDataFromDatabase() {

        AllEarthQuakeDbModel dbModel = new AllEarthQuakeDbModel();
        dbModel.setKandilliList(kandilliListDataFromDatabase());
        dbModel.setAfadList(afadListDataFromDatabase());

        return dbModel;
    }


}
