package com.crypto.service.coinmarketcap.converter;

import com.crypto.model.cmc.CmcPriceModel;
import com.crypto.model.cmc.core.CoreCmcPrice;
import com.assistant.commonapi.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 21:08:24
 */
@Service
@RequiredArgsConstructor
public class CmcPriceConverter implements SimpleTask<CoreCmcPrice, CmcPriceModel> {

	@Override
	public CmcPriceModel apply(CoreCmcPrice coreCmcPrice) {

		if (Objects.nonNull(coreCmcPrice)) {
			return CmcPriceModel.builder()
					.price(coreCmcPrice.getPrice())
					.volume24h(coreCmcPrice.getVolume24h())
					.percentChange1h(coreCmcPrice.getPercentChange1h())
					.percentChange7d(coreCmcPrice.getPercentChange7d())
					.percentChange24h(coreCmcPrice.getPercentChange24h())
					.percentChange30d(coreCmcPrice.getPercentChange30d())
					.percentChange60d(coreCmcPrice.getPercentChange60d())
					.marketCap(coreCmcPrice.getMarketCap())
					.lastUpdated(coreCmcPrice.getLastUpdated())
					.build();
		}

		return null;
	}
}
