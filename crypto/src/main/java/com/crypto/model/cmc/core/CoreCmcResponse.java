package com.crypto.model.cmc.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author semih on Haziran, 2021, 12.06.2021, 22:52:39
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreCmcResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("status")
	private CoreCmcStatus status;

	@JsonProperty("data")
	private List<CoreCmcInfo> data;
}
