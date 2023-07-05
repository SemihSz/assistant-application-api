package com.earthquake.api.service.mail;

import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.model.InternalErrorMailModel;
import com.earthquake.api.request.SendEarthQuakeEmailRequest;
import com.earthquake.api.request.admin.SendAdminEmailRequest;
import com.earthquake.api.response.EarthQuakeDistanceResponse;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.response.EarthQuakeSimpleResponse;
import com.earthquake.api.response.ExcelResponse;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * Created by Semih, 29.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
public interface MailService {

    void sendExcelViaMail(SendEarthQuakeEmailRequest request, EarthQuakeResponse response, String fileName);

    void sendFilterExcelViaMail(SendEarthQuakeEmailRequest request, ExcelResponse response, String fileName);

    void sendClosestEarthQuakeViaMail(SendEarthQuakeEmailRequest request, EarthQuakeDistanceResponse response);

    void sendHigherMagnitudeEarthQuakeViaMail(SendEarthQuakeEmailRequest request, EarthQuakeSimpleResponse<EarthQuakeInfo, EarthQuakeInfo> response) throws MessagingException;

    void sendInternalErrorMail(InternalErrorMailModel input);

    void sendEmailWithByteArray(SendEarthQuakeEmailRequest request, ExcelResponse response, String fileName);

    int sendAuthenticationTokenViaMail(SendAdminEmailRequest request, String token);
}
