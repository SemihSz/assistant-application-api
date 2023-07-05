package com.earthquake.api.service;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.model.*;
import com.earthquake.api.model.world.WorldEarthQuakeModel;
import com.earthquake.api.model.world.WorldEarthQuakeQueryModel;
import com.earthquake.api.request.*;
import com.earthquake.api.request.world.WorldEarthQuakeRequest;
import com.earthquake.api.request.world.WorldEarthquakeQueryRequest;
import com.earthquake.api.response.*;
import com.earthquake.api.response.world.WorldEarthQuakeGenericResponse;
import com.earthquake.api.service.cache.*;
import com.earthquake.api.service.core.CoreAfadService;
import com.earthquake.api.service.core.CoreAfadV2Service;
import com.earthquake.api.service.distance.DistanceCalculatorService;
import com.earthquake.api.service.distance.DistanceCalculatorWithUserLocationService;
import com.earthquake.api.service.excel.ExcelFilterListService;
import com.earthquake.api.service.excel.GenerateExcelFileService;
import com.earthquake.api.service.filter.*;
import com.earthquake.api.service.hash.HashService;
import com.earthquake.api.service.logger.EarthQuakeLoggerService;
import com.earthquake.api.service.mail.MailService;
import com.earthquake.api.service.mail.SaveEmailService;
import com.earthquake.api.service.storedata.StoreDataDashboardService;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.service.world.WorldEarthQuakeService;
import com.earthquake.api.type.ServiceUsageStatusType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Eylül, 2020, 24.09.2020, 22:07:29
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EarthQuakeService implements GeneralService {

    private final GetKandilliInfoAllService getKandilliInfoAllService;

    private final FirstNQuakeListService firstNQuakeListService;

    private final FilterMagnitudeQuakeListService filterEarthQuakeListService;

    private final FilterDepthListService filterDepthListService;

    private final MaxMinMagnitudeQuakeService maxMinMagnitudeQuakeService;

    private final DistanceCalculatorService distanceCalculatorService;

    private final EarthQuakeLoggerService loggerService;

    private final GenerateExcelFileService generateExcelFileService;

    private final DetailFilterSearchService detailFilterSearchService;

    private final MailService mailService;

    private final FindClosestEarthQuakeServiceWithLocation findClosestEarthQuakeServiceWithLocation;

    private final ExcelFilterListService excelFilterListService;

    private final DistanceCalculatorWithUserLocationService distanceCalculatorWithUserLocationService;

    private final AfadCacheService cacheService;

    private final KandilliCacheService kandilliCacheService;

    private final StoreEarthQuakeService storeEarthQuakeService;

    private final AfadAllEarthQuakeListService afadAllEarthQuakeListService;

    private final CoreAfadService coreAfadService;

    private final CoreAfadV2Service coreAfadV2Service;

    private final ExcelCacheService excelCacheService;

    private final SaveEmailService saveEmailService;

    private final StoreEarthQuakeInfoFilterService storeEarthQuakeInfoFilterService;

    private final StoreDataDashboardService storeDataDashboardService;

    private final UsageCountService usageCountService;

    private final WorldEarthQuakeService worldEarthQuakeService;

    private final ObjectMapper objectMapper;

    private final String [] excelCell = {"Date", "Magnitude", "Depth", "Latitude", "Longitude", "Location Name"};


    /**
     * All List service have to run after generate token.
     * However, mail service execute this before send an email.
     *
     * @param EarthQuakeRequest
     * @return EarthQuakeResponse
     */
    @Override
    public EarthQuakeResponse allList(EarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.ALL_LIST);

        HashService<EarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        switch (request.institutionType) {

            case KANDILLI:
                return kandilliCacheService.apply(request);

            case AFAD:
                return cacheService.apply(request);

            default:
                throw new RuntimeException("Resource not found error");

        }


    }

    /**
     * Filter number of list from list.
     *
     * @param EarthQuakeRequest
     * @return EarthQuakeResponse
     */
    @Override
    public EarthQuakeResponse nList(EarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.FIRST_N_LIST);
        //log.info(firstNQuakeListService.apply(request).toString());

        EarthQuakeResponse data = allList(request);
        HashService<EarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return firstNQuakeListService.apply(request, data);
    }

    /**
     * Filter magnitude level.
     *
     * @param EarthQuakeRequest
     * @return EarthQuakeResponse
     */
    @Override
    public EarthQuakeResponse filterMagnitude(EarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.FILTER_MAGNITUDE_LIST);
        final EarthQuakeResponse response = filterEarthQuakeListService.apply(request);

        HashService<EarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return response;
    }

    /**
     * Filter depth level.
     *
     * @param EarthQuakeRequest
     * @return EarthQuakeResponse
     */
    @Override
    public EarthQuakeResponse filterDepth(EarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.FILTER_DEPTH_LIST);
        final EarthQuakeResponse response = filterDepthListService.apply(request);

        HashService<EarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return response;
    }

    /**
     * Find highest and lowest earthquakes information from current list.
     *
     * @param EarthQuakeRequest
     * @return EarthQuakeSimpleResponse<EarthQuakeInfo, EarthQuakeInfo>
     */
    @Override
    public EarthQuakeSimpleResponse<EarthQuakeInfo, EarthQuakeInfo> highestMagnitude(EarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.POWER_MAGNITUDE);
        final EarthQuakeSimpleResponse<EarthQuakeInfo, EarthQuakeInfo> response = new EarthQuakeSimpleResponse<>();
        allList(request);
        response.setFirstInfo(maxMinMagnitudeQuakeService.highestMagnitude(request));
        response.setSecondInfo(maxMinMagnitudeQuakeService.lowestMagnitude(request));
        log.info("highest earth quake magnitude: {}, quake ID: {}", response.getFirstInfo().getMagnitude(), response.getFirstInfo().getId());
        log.info("lowest earth quake magnitude: {}, quake ID: {}", response.getSecondInfo().getMagnitude(), response.getSecondInfo().getId());

        HashService<EarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return response;
    }

    /**
     * Provides detail filter
     *
     * @param EarthQuakeDetailRequest
     * @return GenericResponse<List < EarthQuakeDetailInfo>>
     */
    @Override
    public GenericResponse<List<EarthQuakeDetailInfo>> detailFilter(EarthQuakeDetailRequest request) {

        loggerService.requestLogging(request, Constant.Request.DETAIL_FILTER);

        HashService<EarthQuakeDetailRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return detailFilterSearchService.apply(request);
    }

    /**
     * Calculate distance with your location. when call the allList service generate static earthquakes id.
     * In this way, if the token is expire, you have to generate token then, call allList service take static random id from list.
     * Calling distanceService function provides calculating distance between you and earthquakes destination.
     *  TODO DB bulunan verilere göre hesaplama yap.
     *
     * @param EarthQuakeDistanceRequest
     * @return EarthQuakeDistanceResponse
     */
    @Override
    public EarthQuakeDistanceResponse distanceService(EarthQuakeDistanceRequest request) {

        loggerService.requestLogging(request, Constant.Request.DISTANCE_EARTHQUAKE);

        HashService<EarthQuakeDistanceRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return distanceCalculatorService.apply(request);
    }

    /**
     * Creates excel file containing 500 earthquake information.
     *
     * @param EarthQuakeRequest
     * @return String
     * @throws IOException
     */
    @Override
    public String generateExcelFile(EarthQuakeRequest request) throws IOException {

        loggerService.requestLogging(request, Constant.Request.EXCEL_LIST);

        HashService<EarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        final EarthQuakeResponse response = getKandilliInfoAllService.apply(request);
        int status = generateExcelFileService.generateExcel(response.getQuakeInfoList(), Constant.Excel.GENERAL_EXCEL_FILE_NAME, excelCell);

        if (status == 200) {
            usageCountService.accept(Constant.Request.EXCEL_LIST, ServiceUsageStatusType.SUCCESS);
        }
        else {
            usageCountService.accept(Constant.Request.EXCEL_LIST, ServiceUsageStatusType.FAIL);
        }

        return status == 200 ? Constant.EXCEL_SUCCESS : Constant.EXCEL_FAIL;
    }

    /**
     * Find closest earthquake for according to your location.
     *
     * @param EarthQuakeDistanceRequest
     * @return EarthQuakeDistanceResponse
     */
    @Override
    public EarthQuakeDistanceResponse closestEarthQuakeInformation(EarthQuakeDistanceRequest request) {

        HashService<EarthQuakeDistanceRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        loggerService.requestLogging(request, Constant.Request.CLOSEST_EARTHQUAKE);
        return findClosestEarthQuakeServiceWithLocation.apply(request);
    }

    /**
     * Send email Case by case.
     *
     * @param SendEmailRequest
     */
    @SneakyThrows
    @Override
    public void sendEmail(SendEarthQuakeEmailRequest request) {

        loggerService.requestLogging(request, Constant.Request.SEND_EMAIL);

        HashService<SendEarthQuakeEmailRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        EarthQuakeRequest earthQuakeRequest = new EarthQuakeRequest();
        earthQuakeRequest.setToken(request.getToken());
        earthQuakeRequest.setInstitutionType(request.getInstitutionType());
        switch (request.getMailType()) {

            case HIGHEST_MAGNITUDE:
                mailService.sendHigherMagnitudeEarthQuakeViaMail(request, highestMagnitude(earthQuakeRequest));
                break;

            case CLOSET_EARTH_QUAKE_LOCATION:

                EarthQuakeDistanceRequest earthQuakeDistanceRequest = new EarthQuakeDistanceRequest();
                earthQuakeDistanceRequest.setId(request.getId());
                earthQuakeDistanceRequest.setLatitude(request.getLatitude());
                earthQuakeDistanceRequest.setLongitude(request.getLongitude());
                earthQuakeDistanceRequest.setToken(request.getToken());
                final EarthQuakeDistanceResponse response = findClosestEarthQuakeServiceWithLocation.apply(earthQuakeDistanceRequest);
                mailService.sendClosestEarthQuakeViaMail(request, response);
                break;

            case ERROR_MAIL:
                break;

            case FILTER_EXCEL_FILE:
                final ExcelResponse filterResponse = generateFilterExcel(request);
                mailService.sendFilterExcelViaMail(request, filterResponse, Constant.Excel.FILTER_EXCEL_FILE_NAME);
                break;

            case ALL_FILE_VIA_EXCEL:
                final ExcelResponse allExcelResponse = generateExcel(earthQuakeRequest);
                mailService.sendEmailWithByteArray(request, allExcelResponse, Constant.Excel.GENERAL_EXCEL_FILE_NAME);
                break;

            default:
                throw new RuntimeException("Not Found");
        }
    }

    /**
     * Generate Excel for earthquake information
     *
     * @param EarthQuakeRequest
     * @return ExcelResponse
     */
    @SneakyThrows
    @Override
    public ExcelResponse generateExcel(EarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.EXCEL_EARTHQUAKE);

        HashService<EarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        ExcelResponse excelResponse = new ExcelResponse();
        // TODO CACHE BAK İLERİDE response dönmüyor postman üzerinden

//		final ExcelResponse excelCacheResponse = excelCacheService.getExcelInfo();
//
//		if (Objects.nonNull(excelCacheResponse)) {
//			excelResponse.setInstitutionType(excelCacheResponse.getInstitutionType());
//			excelResponse.setByteArrayInputStream(excelCacheResponse.getByteArrayInputStream());
//			excelResponse.setStatus(200);
//		}
//		else {
//			switch (request.institutionType) {
//
//				case KANDILLI:
//					final EarthQuakeResponse kandilliResponse = getKandilliInfoAllService.apply(request);
//					excelResponse = generateExcelFileService.byteArray(kandilliResponse.getQuakeInfoList());
//					break;
//
//				case AFAD:
//					final EarthQuakeResponse afadResponse = afadAllEarthQuakeListService.apply(coreAfadService.coreAfadService());
//					excelResponse = generateExcelFileService.byteArray(afadResponse.getAfadQuakeList());
//					break;
//
//				default:
//					log.error("Unaccepted type error");
//					break;
//
//			}
//
//		}
// 		if (excelCacheResponse == null) {
//			excelCacheService.accept(excelResponse);
//		}
        StoreAllEarthQuakeRequest storeAllEarthQuakeRequest = new StoreAllEarthQuakeRequest();
        storeAllEarthQuakeRequest.setInstitutionType(request.getInstitutionType());
        generateStoreDataExcel(storeAllEarthQuakeRequest);
        switch (request.institutionType) {

            case KANDILLI:
                final EarthQuakeResponse kandilliResponse = getKandilliInfoAllService.apply(request);
                excelResponse = generateExcelFileService.byteArray(kandilliResponse.getQuakeInfoList(), excelCell);
                break;

            case AFAD:
                final EarthQuakeResponse afadResponse = afadAllEarthQuakeListService.apply(coreAfadV2Service.coreAfadService());
                excelResponse = generateExcelFileService.byteArray(afadResponse.getAfadQuakeList(), excelCell);
                break;

            default:
                log.error("Unaccepted type error");
                break;

        }

        excelResponse.setInstitutionType(request.institutionType);
        excelCacheService.accept(excelResponse);
        return excelResponse;
    }

    /**
     * Generate filter excel file.
     *
     * @param SendEmailRequest
     * @return ExcelResponse
     */
    @SneakyThrows
    @Override
    public ExcelResponse generateFilterExcel(SendEarthQuakeEmailRequest request) {

        loggerService.requestLogging(request, Constant.Request.SEND_EMAIL);
        EarthQuakeRequest earthQuakeRequest = new EarthQuakeRequest();
        earthQuakeRequest.setToken(request.getToken());

        final EarthQuakeResponse response = allList(earthQuakeRequest);

        final ExcelFilterInput input = ExcelFilterInput.builder()
                .depth(request.getDepth())
                .limit(request.getLimit())
                .maxMagnitude(request.getMaxMagnitude())
                .build();

        return generateExcelFileService.byteArray(excelFilterListService.apply(response.getQuakeInfoList(), input), excelCell);
    }

    /**
     * Calculate distance according to the earthquake location.
     *
     * @param DistanceCalculatorRequest
     * @return DistanceUserLocationResponse
     */
    @Override
    public DistanceUserLocationResponse calculateDistanceWithUserLocation(DistanceCalculatorRequest request) {

        HashService<DistanceCalculatorRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        loggerService.requestLogging(request, Constant.Request.CALCULATE_DISTANCE);
        return distanceCalculatorWithUserLocationService.apply(request);
    }

    /**
     * This service provides to getter all earthquake information, according to 2 different institution data. User can get only
     * KANDILLI, AFAD or ALL earthquake information from database.
     *
     * @param StoreAllEarthQuakeRequest
     * @return AllEarthQuakeDbModel
     */
    @Override
    public AllEarthQuakeDbModel allEarthQuakeDbInfo(StoreAllEarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.STORE_ALL_EARTH_QUAKE_LIST);

        HashService<StoreAllEarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        AllEarthQuakeDbModel response = new AllEarthQuakeDbModel();

        switch (request.getInstitutionType()) {

            case KANDILLI:
                response.setKandilliList(storeEarthQuakeService.kandilliListDataFromDatabase());
                break;

            case AFAD:
                response.setAfadList(storeEarthQuakeService.afadListDataFromDatabase());
                break;

            case ALL:
                response = storeEarthQuakeService.bothEarthQuakeDataFromDatabase();
                break;

            default:
                usageCountService.accept(Constant.Request.STORE_ALL_EARTH_QUAKE_LIST, ServiceUsageStatusType.FAIL);
                log.error("Error undefined type InstitutionType");
                break;
        }

        usageCountService.accept(Constant.Request.STORE_ALL_EARTH_QUAKE_LIST, ServiceUsageStatusType.SUCCESS);

        return response;
    }


    @SneakyThrows
    @Override
    public ExcelResponse generateStoreDataExcel(StoreAllEarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.EXCEL_STORE_DATA_EARTHQUAKE_INFO);

        HashService<StoreAllEarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        StoreAllEarthQuakeRequest storeAllEarthQuakeRequest = new StoreAllEarthQuakeRequest();
        storeAllEarthQuakeRequest.setInstitutionType(request.getInstitutionType());
        final AllEarthQuakeDbModel dbResponse = allEarthQuakeDbInfo(storeAllEarthQuakeRequest);
        List<EarthQuakeInfo> excelListInfo = new ArrayList<>();

        switch (request.getInstitutionType()) {

            case KANDILLI:
                dbResponse.getKandilliList().forEach(t -> {

                    final EarthQuakeInfo earthQuakeInfo = EarthQuakeInfo.builder()
                            .date(t.getDate())
                            .latitude(t.getLatitude())
                            .longitude(t.getLongitude())
                            .magnitude(t.getMagnitude())
                            .depth(t.getDepth())
                            .locationName(t.getLocation()).build();

                    excelListInfo.add(earthQuakeInfo);
                });
                break;

            case AFAD:
                dbResponse.getAfadList().forEach(t -> {

                    final EarthQuakeInfo earthQuakeInfo = EarthQuakeInfo.builder()
                            .date(t.getDate())
                            .latitude(t.getLatitude())
                            .longitude(t.getLongitude())
                            .magnitude(t.getMagnitude())
                            .depth(t.getDepth())
                            .locationName(t.getLocation()).build();

                    excelListInfo.add(earthQuakeInfo);
                });
                break;

            default:
                usageCountService.accept(Constant.Request.EXCEL_STORE_DATA_EARTHQUAKE_INFO, ServiceUsageStatusType.FAIL);
                log.error("Unaccepted type error");
                throw new RuntimeException("Undefined InstitutionType");
        }

        usageCountService.accept(Constant.Request.EXCEL_STORE_DATA_EARTHQUAKE_INFO, ServiceUsageStatusType.SUCCESS);

        return generateExcelFileService.byteArray(excelListInfo, excelCell);
    }

    /**
     * Save user email for DB.
     *
     * @param SaveEmailRequest
     * @return String
     */
    @Override
    public String saveEmail(SaveEmailRequest request) {

        loggerService.requestLogging(request, Constant.Request.SAVE_EMAIL);

        HashService<SaveEmailRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return saveEmailService.apply(request);
    }


    @Override
    public EarthQuakeResponse filterDbInfo(StoreDbFilterRequest request) {

        loggerService.requestLogging(request, Constant.Request.STORE_DB_FILTER);

        HashService<StoreDbFilterRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        StoreAllEarthQuakeRequest storeAllEarthQuakeRequest = new StoreAllEarthQuakeRequest();
        storeAllEarthQuakeRequest.setInstitutionType(request.getInstitutionType());
        final AllEarthQuakeDbModel response = allEarthQuakeDbInfo(storeAllEarthQuakeRequest);

        final StoreDbInfoInput input = StoreDbInfoInput.builder()
                .allEarthQuakeDbModel(response)
                .institutionType(request.getInstitutionType())
                .maxMagnitude(request.getMaxMagnitude())
                .minMagnitude(request.getMinMagnitude())
                .depth(request.getDepth())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .limit(request.getLimit())
                .location(request.getLocation())
                .build();


        return storeEarthQuakeInfoFilterService.apply(input);
    }

    /**
     * General Dashboard Service
     *
     * @param StoreDbInfoInput
     * @return StoreDataDashboardResponse
     */
    @SneakyThrows
    @Override
    public DashboardResponse dashboardService(StoreDashboardRequest request) {

        loggerService.requestLogging(request, Constant.Request.DASHBOARD);

        HashService<StoreDashboardRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        StoreAllEarthQuakeRequest storeAllEarthQuakeRequest = new StoreAllEarthQuakeRequest();
        storeAllEarthQuakeRequest.setInstitutionType(request.getInstitutionType());
        final AllEarthQuakeDbModel response = allEarthQuakeDbInfo(storeAllEarthQuakeRequest);

        final StoreDbInfoInput input = StoreDbInfoInput.builder()
                .allEarthQuakeDbModel(response)
                .limit(request.getLimit())
                .location(request.getLocation())
                .institutionType(request.getInstitutionType())
                .build();


        return storeDataDashboardService.apply(input, request.getDashboard());
    }


    public WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> worldGenericService(WorldEarthQuakeRequest request) {

        loggerService.requestLogging(request, Constant.Request.WORLD_EARTH_QUAKE_RESULT);

        HashService<WorldEarthQuakeRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return worldEarthQuakeService.worldGenericService(request);

    }

    public WorldEarthQuakeGenericResponse<WorldEarthQuakeQueryModel> queryEarthquakeResult(WorldEarthquakeQueryRequest request) {

        loggerService.requestLogging(request, Constant.Request.WORLD_EARTH_QUAKE_QUERY);

        HashService<WorldEarthquakeQueryRequest> hashService = new HashService<>(objectMapper);
        log.info("Hash: " + hashService.apply(request));

        return worldEarthQuakeService.worldEarthquakeQuery(request);
    }
}
