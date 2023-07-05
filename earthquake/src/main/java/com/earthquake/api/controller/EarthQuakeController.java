package com.earthquake.api.controller;

import com.earthquake.api.model.AllEarthQuakeDbModel;
import com.earthquake.api.model.EarthQuakeDetailInfo;
import com.earthquake.api.model.RestResponse;
import com.earthquake.api.model.world.WorldEarthQuakeModel;
import com.earthquake.api.model.world.WorldEarthQuakeQueryModel;
import com.earthquake.api.request.*;
import com.earthquake.api.request.world.WorldEarthQuakeRequest;
import com.earthquake.api.request.world.WorldEarthquakeQueryRequest;
import com.earthquake.api.response.*;
import com.earthquake.api.response.world.WorldEarthQuakeGenericResponse;
import com.earthquake.api.service.EarthQuakeService;
import com.earthquake.api.service.GeneralService;
import com.earthquake.api.service.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static com.earthquake.api.constant.Constant.SUCCESS_CODE;


@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class EarthQuakeController {

    private final EarthQuakeService worldGenericService;

    private final GeneralService earthQuakeService;

    private final TokenService tokenService;

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<String>> getToken(TokenRequest request) {

        return ResponseEntity.ok(new RestResponse<String>(SUCCESS_CODE, tokenService.generateToken(request)));
    }

    @PostMapping(value = "/all-list", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeResponse>> getAllList(EarthQuakeRequest request) throws IOException {

        return ResponseEntity.ok(new RestResponse<EarthQuakeResponse>(SUCCESS_CODE, earthQuakeService.allList(request)));
    }

    @PostMapping(value = "/first-n-list", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeResponse>> getFirsNList(EarthQuakeRequest request) {

        return ResponseEntity.ok(new RestResponse<EarthQuakeResponse>(SUCCESS_CODE, earthQuakeService.nList(request)));
    }

    @PostMapping(value = "/filter-magnitude-list", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeResponse>> getFilterMagnitudeList(EarthQuakeRequest request) {

        return ResponseEntity.ok(new RestResponse<EarthQuakeResponse>(SUCCESS_CODE, earthQuakeService.filterMagnitude(request)));
    }

    @PostMapping(value = "/filter-depth-list", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeResponse>> getFilterDepthList(EarthQuakeRequest request) {

        return ResponseEntity.ok(new RestResponse<EarthQuakeResponse>(SUCCESS_CODE, earthQuakeService.filterDepth(request)));
    }

    @PostMapping(value = "/power-magnitude", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeSimpleResponse>> getHigherMagnitude(EarthQuakeRequest request) {

        return ResponseEntity.ok(new RestResponse<EarthQuakeSimpleResponse>(SUCCESS_CODE, earthQuakeService.highestMagnitude(request)));
    }

    @PostMapping(value = "/distance-earthquake", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeDistanceResponse>> getDistance(EarthQuakeDistanceRequest request) {

        return ResponseEntity.ok(new RestResponse<EarthQuakeDistanceResponse>(SUCCESS_CODE, earthQuakeService.distanceService(request)));
    }

    @PostMapping(value = "/excel-list", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<String>> generateExcelData(EarthQuakeRequest request) throws IOException {

        return ResponseEntity.ok(new RestResponse<String>(SUCCESS_CODE, earthQuakeService.generateExcelFile(request)));
    }

    @PostMapping(value = "/detail-filter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<GenericResponse<List<EarthQuakeDetailInfo>>>> getDetailFilter(EarthQuakeDetailRequest request) throws IOException {

        return ResponseEntity.ok(new RestResponse<GenericResponse<List<EarthQuakeDetailInfo>>>(SUCCESS_CODE, earthQuakeService.detailFilter(request)));
    }

    @PostMapping(value = "/send-email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse> sendEmail(SendEarthQuakeEmailRequest request) throws MessagingException {

        earthQuakeService.sendEmail(request);
        return ResponseEntity.ok(new RestResponse(SUCCESS_CODE));
    }

    @PostMapping(value = "/closest-earthquake", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeDistanceResponse>> getClosestDistanceEarthQuake(EarthQuakeDistanceRequest request) {

        return ResponseEntity.ok(new RestResponse<EarthQuakeDistanceResponse>(SUCCESS_CODE, earthQuakeService.closestEarthQuakeInformation(request)));
    }

    @SneakyThrows
    @PostMapping(value = "/excel/earthquake", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<InputStreamResource> getExcel(EarthQuakeRequest request) {
        final ExcelResponse response = earthQuakeService.generateExcel(request);
        final ByteArrayInputStream inputStream = response.getByteArrayInputStream();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=earth-quake.xlsx");
        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(inputStream));
    }

    @PostMapping(value = "/calculate/distance", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<DistanceUserLocationResponse>> getUserLocation(DistanceCalculatorRequest request) {

        return ResponseEntity.ok(new RestResponse<DistanceUserLocationResponse>(SUCCESS_CODE, earthQuakeService.calculateDistanceWithUserLocation(request)));
    }

    @PostMapping(value = "/storeAllEarthquakeList", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AllEarthQuakeDbModel>> getUserLocation(StoreAllEarthQuakeRequest request) {

        return ResponseEntity.ok(new RestResponse<AllEarthQuakeDbModel>(SUCCESS_CODE, earthQuakeService.allEarthQuakeDbInfo(request)));
    }

    @SneakyThrows
    @PostMapping(value = "/excel/store-data-earthquake-info", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<InputStreamResource> getStoreDataExcel(StoreAllEarthQuakeRequest request) {
        final ExcelResponse response = earthQuakeService.generateStoreDataExcel(request);
        final ByteArrayInputStream inputStream = response.getByteArrayInputStream();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=earth-quake.xlsx");
        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(inputStream));
    }

    @PostMapping(value = "/save-email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<String>> saveEmail(SaveEmailRequest request) {

        return ResponseEntity.ok(new RestResponse<String>(SUCCESS_CODE, earthQuakeService.saveEmail(request)));
    }

    @PostMapping(value = "/storeDb-filter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<EarthQuakeResponse>> getStoreDbFilter(StoreDbFilterRequest request) throws IOException {

        return ResponseEntity.ok(new RestResponse<EarthQuakeResponse>(SUCCESS_CODE, earthQuakeService.filterDbInfo(request)));
    }

    @SneakyThrows
    @PostMapping(value = "/dashboard", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<DashboardResponse>> getStoreDbFilter(StoreDashboardRequest request) throws IOException {

        return ResponseEntity.ok(new RestResponse<DashboardResponse>(SUCCESS_CODE, earthQuakeService.dashboardService(request)));
    }

    @PostMapping(value = "/world-earthquake-result", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<WorldEarthQuakeGenericResponse<WorldEarthQuakeModel>>> getWorldEarthQuakeResult(WorldEarthQuakeRequest request) throws IOException {

        return ResponseEntity.ok(new RestResponse<WorldEarthQuakeGenericResponse<WorldEarthQuakeModel>>(SUCCESS_CODE, worldGenericService.worldGenericService(request)));
    }

    @PostMapping(value = "/world-earthquake-query", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<WorldEarthQuakeGenericResponse<WorldEarthQuakeQueryModel>>> getWorldEarthQuakeQuery(WorldEarthquakeQueryRequest request) {

        return ResponseEntity.ok(new RestResponse<WorldEarthQuakeGenericResponse<WorldEarthQuakeQueryModel>>(SUCCESS_CODE, worldGenericService.queryEarthquakeResult(request)));
    }

}
