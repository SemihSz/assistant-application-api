package com.earthquake.api.response;

import com.earthquake.api.type.InstitutionType;
import lombok.*;

import java.io.ByteArrayInputStream;

/**
 * @author semih on Ekim, 2020, 9.10.2020, 22:21:33
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelResponse {

	private ByteArrayInputStream byteArrayInputStream;

	private int status;

	private InstitutionType institutionType;
}
