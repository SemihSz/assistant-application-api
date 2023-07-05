package com.crypto.service.coinmarketcap.converter;

import com.crypto.model.SupplyModel;
import com.crypto.model.input.SupplyInput;
import com.assistant.commonapi.task.SimpleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 15.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
public class CmcSupplyConverter implements SimpleTask<SupplyInput, SupplyModel> {

    @Override
    public SupplyModel apply(SupplyInput supplyInput) {

        if (Objects.nonNull(supplyInput)) {
            return SupplyModel.builder()
                    .maxSupply(supplyInput.getMaxSupply())
                    .circulatingSupply(supplyInput.getCirculatingSupply())
                    .totalSupply(supplyInput.getTotalSupply())
                    .build();
        }

        return null;
    }
}
