package com.crypto.model.cmc.response;

import com.crypto.model.cmc.CmcModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 20:10:24
 */
@Getter
@Setter
@Builder
public class CmcApiResponse {

	private List<CmcModel> cmc;

}
