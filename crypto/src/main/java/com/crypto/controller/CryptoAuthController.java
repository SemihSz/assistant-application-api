package com.crypto.controller;

import com.crypto.model.RestResponse;
import com.crypto.request.auth.CryptoAuthRequest;
import com.crypto.request.auth.CryptoAuthResponse;
import com.crypto.service.auth.CryptoAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Semih, 27.05.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CryptoAuthController {

	private final CryptoAuthService authService;

	@PostMapping(value = "/register-auth", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseEntity<RestResponse<Boolean>> registerAuth(CryptoAuthRequest request) {
		return ResponseEntity.ok(new RestResponse<>(200, authService.registerAuth(request)));
	}

	@PostMapping(value = "/generate-user")
	ResponseEntity<RestResponse<CryptoAuthResponse>> generateUser() {
		return ResponseEntity.ok(new RestResponse<>(200, authService.generateUser()));
	}

}
