package com.crypto.service.coinmarketcap;

import com.crypto.model.cmc.response.CmcApiResponse;
import com.crypto.request.cmc.CmcRequest;
import org.springframework.stereotype.Service;

/**
 * @author semih on Haziran, 2021, 11.06.2021, 22:39:31
 */
@Service
public interface GeneralCmcService {

	CmcApiResponse restCmcService(CmcRequest request);
}
