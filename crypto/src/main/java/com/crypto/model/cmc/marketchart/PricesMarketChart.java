package com.crypto.model.cmc.marketchart;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author semih on Eylül, 2021, 18.09.2021, 19:54:52
 */
@Getter
@Builder
public class PricesMarketChart {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime time;

	private BigDecimal price;
}
