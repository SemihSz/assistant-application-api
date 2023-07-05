package com.earthquake.api.service.cache;

import com.earthquake.api.response.ExcelResponse;
import com.earthquake.api.service.cache.model.GenericCacheModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;


/**
 * Created by Semih, 7.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelCacheService implements Consumer<ExcelResponse> {

    private static final String EXCEL_TOKEN = "excel";

    private final CacheService<String, GenericCacheModel<ExcelResponse>> cacheService;

    @Override
    public void accept(ExcelResponse excelResponse) {

        try {

            GenericCacheModel<ExcelResponse> cacheModel = new GenericCacheModel<>();
            cacheModel.setData(excelResponse);
            cacheModel.setInstitutionType(excelResponse.getInstitutionType());

            log.info("Generate cache model");
            log.info("Starting to the put cache model into cacheService");
            cacheService.put(EXCEL_TOKEN, cacheModel);
            log.info("Successfully put model into cache");

        }
        catch (Exception e) {
            log.error(e.getMessage());
            log.info("Error put model into cache!!");
        }
    }

    public ExcelResponse getExcelInfo() {
        try {
            return Objects.nonNull(cacheService.get(EXCEL_TOKEN).getData()) ? cacheService.get(EXCEL_TOKEN).getData() : null;
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }
}
