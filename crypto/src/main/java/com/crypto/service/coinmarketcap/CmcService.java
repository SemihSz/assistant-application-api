package com.crypto.service.coinmarketcap;

import com.crypto.model.cmc.response.CmcApiResponse;
import com.crypto.request.cmc.CmcRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * @author semih on Haziran, 2021, 11.06.2021, 22:40:53
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmcService implements GeneralCmcService {

	private final CmcFlowService cmcFlowService;

	//TODO CORE TARAFTAN GELEN BİLGİLERİN BURADA SERVİSLERİN İHTİYACA GÖRE ÇALIŞMASI, FİLTER, HESAPLAMA TARZI VB: GENEL SERVİS İŞLERİ

	@Override
	public CmcApiResponse restCmcService(CmcRequest request) {

		if (Objects.nonNull(request.getCmcFlowType())) {

			switch (request.getCmcFlowType()) {

				case LATEST:
					return cmcFlowService.cryptoTop100();

				case NEW_ADDED:
					return cmcFlowService.getNewAdded();

				default:
					log.error("error flow type");
					break;
			}
		}
		return null;
	}
}
