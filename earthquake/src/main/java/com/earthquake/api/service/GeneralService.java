package com.earthquake.api.service;

import com.earthquake.api.model.AllEarthQuakeDbModel;
import com.earthquake.api.model.EarthQuakeDetailInfo;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.request.*;
import com.earthquake.api.response.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author semih on Eylül, 2020, 24.09.2020, 22:05:05
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
public interface GeneralService {

	EarthQuakeResponse allList(EarthQuakeRequest request);

	EarthQuakeResponse nList(EarthQuakeRequest request);

	EarthQuakeResponse filterMagnitude(EarthQuakeRequest request);

	EarthQuakeResponse filterDepth(EarthQuakeRequest request);

	EarthQuakeSimpleResponse<EarthQuakeInfo, EarthQuakeInfo> highestMagnitude(EarthQuakeRequest request);

	EarthQuakeDistanceResponse distanceService(EarthQuakeDistanceRequest request);

	String generateExcelFile(EarthQuakeRequest request) throws IOException;

	GenericResponse<List<EarthQuakeDetailInfo>> detailFilter(EarthQuakeDetailRequest request);

	void sendEmail(SendEarthQuakeEmailRequest request);

	EarthQuakeDistanceResponse closestEarthQuakeInformation(EarthQuakeDistanceRequest request);

	ExcelResponse generateExcel(EarthQuakeRequest request);

	ExcelResponse generateFilterExcel(SendEarthQuakeEmailRequest request);

	DistanceUserLocationResponse calculateDistanceWithUserLocation(DistanceCalculatorRequest request);

	AllEarthQuakeDbModel allEarthQuakeDbInfo(StoreAllEarthQuakeRequest request);

	EarthQuakeResponse filterDbInfo(StoreDbFilterRequest request);

	ExcelResponse generateStoreDataExcel(StoreAllEarthQuakeRequest request); // Send All Data via Excel.

	String saveEmail(SaveEmailRequest request);

	DashboardResponse dashboardService(StoreDashboardRequest input) throws Exception;

}
