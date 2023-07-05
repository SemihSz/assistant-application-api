package com.crypto.model.cmc.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 20:18:02
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCmcPlatform implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("slug")
	private String slug;

	@JsonProperty("token_address")
	private String tokenAddress;
}
