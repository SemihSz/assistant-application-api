package com.crypto.controller;


import com.crypto.model.CoinWhitelistRequest;
import com.crypto.model.RestResponse;
import com.crypto.model.cmc.response.CmcApiResponse;
import com.crypto.model.coingecko.CoingeckoApiResponse;
import com.crypto.model.portfolio.UserPortfolio;
import com.crypto.request.cmc.CmcRequest;
import com.crypto.request.coingecko.CoinGeneratorRequest;
import com.crypto.request.coingecko.CoingeckoRequest;
import com.crypto.request.number_of_coins.UserAddNumberOfCoins;
import com.crypto.request.porfolio.UserPortfolioRequest;
import com.crypto.service.coingecko.GeneralCoingeckoService;
import com.crypto.service.coingecko.coin_generator.CoinGeneratorService;
import com.crypto.service.coingecko.getcoinslist.CoinWhitelistSave;
import com.crypto.service.coinmarketcap.GeneralCmcService;
import com.crypto.service.number_of_coins.UserNumberOfCoinService;
import com.crypto.service.porfolio.PortfolioAbstraction;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Semih, 27.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CryptoController {

	private final GeneralCmcService generalCmcService;

	private final GeneralCoingeckoService generalCoingeckoService;

	private final CoinGeneratorService coingeckoGeneratorService;

	private final CoinWhitelistSave coinWhitelistSave;

	private final UserNumberOfCoinService userNumberOfCoinService;

	private final PortfolioAbstraction portfolioAbstraction;

	@PostMapping(value = "/rest-cmc", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseEntity<RestResponse<CmcApiResponse>> getCmcService(CmcRequest request) {

		return ResponseEntity.ok(new RestResponse<CmcApiResponse>(200, generalCmcService.restCmcService(request)));
	}

	@PostMapping(value = "/rest-coingecko", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseEntity<RestResponse<CoingeckoApiResponse>> getCoingeckoService(CoingeckoRequest request) {

		return ResponseEntity.ok(new RestResponse<CoingeckoApiResponse>(200, generalCoingeckoService.coingeckoGeneralMarket(request)));
	}

	@PostMapping(value = "/coin-generator", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseEntity<RestResponse<CoingeckoApiResponse>> generateCoin(CoinGeneratorRequest request) {

		return ResponseEntity.ok(new RestResponse<CoingeckoApiResponse>(200, coingeckoGeneratorService.coingeckoCoinGenerator(request)));
	}


	@PostMapping(value = "/add-coin-whitelist", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseEntity<RestResponse<Boolean>> addCoinWhitelist(CoinWhitelistRequest request) {

		return ResponseEntity.ok(new RestResponse<Boolean>(200, coinWhitelistSave.test(request)));
	}

	@PostMapping(value = "/add-user-coin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseEntity<RestResponse<Boolean>> addUserNumberOfCoins(UserAddNumberOfCoins request) {

		return ResponseEntity.ok(new RestResponse<Boolean>(200, userNumberOfCoinService.addUserNewNumberOfCoins(request)));
	}

	@PostMapping(value = "/show-user-portfolio")
	ResponseEntity<RestResponse<UserPortfolio>> userPortfolio(@RequestHeader(value = "userId") String userId) {

		UserPortfolioRequest request = new UserPortfolioRequest();
		request.setUserId(userId);
		return ResponseEntity.ok(new RestResponse<UserPortfolio>(200, portfolioAbstraction.showUserPortfolio(request)));
	}

}
