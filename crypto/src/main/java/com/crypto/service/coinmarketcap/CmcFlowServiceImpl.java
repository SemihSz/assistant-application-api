package com.crypto.service.coinmarketcap;

import com.crypto.model.SupplyModel;
import com.crypto.model.cmc.CmcModel;
import com.crypto.model.cmc.CmcPriceModel;
import com.crypto.model.cmc.core.CoreCmcResponse;
import com.crypto.model.cmc.response.CmcApiResponse;
import com.crypto.model.input.SupplyInput;
import com.crypto.service.coinmarketcap.converter.CmcPriceConverter;
import com.crypto.service.coinmarketcap.converter.CmcSupplyConverter;
import com.crypto.service.coinmarketcap.core.CmcCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semih, 16.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CmcFlowServiceImpl implements CmcFlowService {

    private final CmcCoreService cmcCoreService;

    private final CmcPriceConverter priceConverter;

    private final CmcSupplyConverter supplyConverter;

    @Override
    public CmcApiResponse cryptoTop100() {
        final List<CmcModel> cmc = new ArrayList<>();

        final CoreCmcResponse coreCmcResponse = cmcCoreService.getTop100();

        coreCmcResponse.getData().forEach(t -> {

            final CmcPriceModel cmcPriceModel = priceConverter.apply(t.getQuote().getCoreCmcPrice());

            final SupplyInput supplyInput = SupplyInput.builder()
                    .maxSupply(t.getMaxSupply())
                    .circulatingSupply(t.getCirculatingSupply())
                    .totalSupply(t.getTotalSupply())
                    .build();

            final SupplyModel supplyModel = supplyConverter.apply(supplyInput);
            final CmcModel cmcModel = CmcModel.builder()
                    .name(t.getName())
                    .price(cmcPriceModel)
                    .supply(supplyModel)
                    .build();

            cmc.add(cmcModel);
        });

        return CmcApiResponse.builder().cmc(cmc).build();
    }

    @Override
    public CmcApiResponse getNewAdded() {
        final List<CmcModel> cmc = new ArrayList<>();

        final CoreCmcResponse coreCmcResponse = cmcCoreService.getNewAdded();

        coreCmcResponse.getData().forEach(t -> {

            final CmcPriceModel cmcPriceModel = priceConverter.apply(t.getQuote().getCoreCmcPrice());

            final SupplyInput supplyInput = SupplyInput.builder()
                    .maxSupply(t.getMaxSupply())
                    .circulatingSupply(t.getCirculatingSupply())
                    .totalSupply(t.getTotalSupply())
                    .build();

            final SupplyModel supplyModel = supplyConverter.apply(supplyInput);
            final CmcModel cmcModel = CmcModel.builder()
                    .id(t.getId())
                    .name(t.getName())
                    .price(cmcPriceModel)
                    .supply(supplyModel)
                    .build();

            cmc.add(cmcModel);
        });

        return CmcApiResponse.builder().cmc(cmc).build();
    }
}
