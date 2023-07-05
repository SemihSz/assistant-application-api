package com.crypto.model.cmc.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 19:57:14
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCmcQuote implements Serializable {

	@JsonProperty("USD")
	private CoreCmcPrice coreCmcPrice;
}
