package com.crypto.request.cmc;

import com.crypto.model.RestRequestHeader;
import com.crypto.type.CmcFlowType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Haziran, 2021, 11.06.2021, 22:51:50
 */
@Getter
@Setter
public class CmcRequest extends RestRequestHeader {

	private CmcFlowType cmcFlowType;
}
