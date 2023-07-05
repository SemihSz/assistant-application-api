package com.crypto;

import com.crypto.model.coingecko.coin_generator.FieldSpecInput;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author semih on Haziran, 2021, 11.06.2021, 22:44:38
 */
@Component
public class Constant {

	public static class CMC {

		public static class Request {

			public static final String LATEST_LIST = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

		}

		public static class Body {

			public static final String START_PARAMS = "start";

			public static final String START_VALUE = "1";

			public static final String LIMIT_PARAMS = "limit";

			public static final String LIMIT_VALUE = "5000";

			public static final String CONVERT_PARAMS = "convert";

			public static final String CONVERT_VALUE = "USD";

			public static final String SORT_PARAMS = "sort";

			public static final String SORT_VALUE = "date_added";

			public static final String SORT_TYPE_PARAMS = "sort_dir";

			public static final String SORT_TYPE_VALUE = "desc";

		}
	}

	public static class Coingecko {

		public static class Request {

			public static final String BASE_URL = "https://api.coingecko.com/api/v3/";

			public static final String COINS_MARKET = BASE_URL.concat("coins/markets");

			public static final String TREND_COINS = BASE_URL.concat("search/trending");

			public static final String CATEGORIES_LIST = BASE_URL.concat("coins/categories/list");

			public static final String COMPANIES_TREASURY = BASE_URL.concat("companies/public_treasury/");

			public static final Object[] MARKET_CHART = {"coins/", "id", "/market_chart"};

			public static final String MARKET_CHART_ID_CONTROL = "id";

			public static class QueryParam {

				public static final String VS_CURRENCY = "vs_currency";

				public static final String DAYS = "days";

				public static final String INTERVAL = "interval";

				public static final String PAGE = "page";

				public static final String PER_PAGE = "per_page";

				public static final String ORDER = "order";

				public static final String SPARKLINE = "sparkline";

			}

		}

		public static class EntityGenerator {

			private static final String TIME = "time";

			private static final String PRICE = "price";

			private static final String VOLUME = "volume";

			private static final String MARKET_CAP = "markerCap";

			public static final Class<?>[] CLASSES = {Entity.class};
//										Builder.class,
//										NoArgsConstructor.class,
//										AllArgsConstructor.class,
//										Getter.class,
//										Setter.class};

			private static final FieldSpecInput<LocalDateTime> fieldSpecTimeInput = FieldSpecInput.<LocalDateTime>builder()
					.annotation(Id.class)
					.fieldType(LocalDateTime.class)
					.name(TIME)
					.build();

			private static final FieldSpecInput<BigDecimal> fieldSpecPriceInput = FieldSpecInput.<BigDecimal>builder()
					.fieldType(BigDecimal.class)
					.name(PRICE)
					.build();

			private static final FieldSpecInput<BigDecimal> fieldSpecVolumeInput = FieldSpecInput.<BigDecimal>builder()
					.fieldType(BigDecimal.class)
					.name(VOLUME)
					.build();

			private static final FieldSpecInput<BigDecimal> fieldSpecMarketCapInput = FieldSpecInput.<BigDecimal>builder()
					.fieldType(BigDecimal.class)
					.name(MARKET_CAP)
					.build();

			public static final List<FieldSpecInput> FIELD_SPEC_INPUT_LIST;

			static {
				FIELD_SPEC_INPUT_LIST = Arrays.asList(new FieldSpecInput[]{fieldSpecTimeInput, fieldSpecPriceInput, fieldSpecVolumeInput, fieldSpecMarketCapInput});
			}


		}

	}


}
