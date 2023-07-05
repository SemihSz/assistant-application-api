package com.crypto.service.coinmarketcap.core;

import com.crypto.model.cmc.core.CoreCmcResponse;
import org.springframework.stereotype.Service;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 21:02:53
 */
@Service
public interface CoreCmcService {

	CoreCmcResponse getTop100();

	CoreCmcResponse getNewAdded();
}
