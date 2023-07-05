package com.crypto.model.cmc.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author semih on Haziran, 2021, 12.06.2021, 22:44:46
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCmcStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("timestamp")
	private String timestamp;

	@JsonProperty("error_code")
	private String error_code;

	@JsonProperty("error_message")
	private String error_message;

	@JsonProperty("elapsed")
	private String elapsed;

	@JsonProperty("credit_count")
	private String credit_count;

	@JsonProperty("notice")
	private String notice;

	@JsonProperty("total_count")
	private String total_count;
}
