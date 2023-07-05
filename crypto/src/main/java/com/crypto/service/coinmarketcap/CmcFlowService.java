package com.crypto.service.coinmarketcap;

import com.crypto.model.cmc.response.CmcApiResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 16.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
public interface CmcFlowService {

    CmcApiResponse cryptoTop100();

    CmcApiResponse getNewAdded();

}
