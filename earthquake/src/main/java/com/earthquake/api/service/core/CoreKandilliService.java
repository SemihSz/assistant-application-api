package com.earthquake.api.service.core;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.CoreKandilliException;
import com.earthquake.api.model.InternalErrorMailModel;
import com.earthquake.api.service.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author semih on Eylül, 2020, 24.09.2020, 21:27:29
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
public class CoreKandilliService  {

	private String url;

	private String emailAddress;

	private boolean sendErrorEmailActive;

	private MailService mailService;


	public CoreKandilliService(@Value("${kandilli-url}") String url, @Value("${error-internal-mail.address}") String emailAddress,
							   @Value("${error-internal-mail.active}") boolean sendErrorEmailActive, MailService mailService) {
		this.url = url;
		this.emailAddress = emailAddress;
		this.sendErrorEmailActive = sendErrorEmailActive;
		this.mailService = mailService;
	}

	public String sendPost() throws Exception {

		try {

			final URL obj = new URL(this.url);
			final HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

			// add request header
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("User-Agent", Constant.USER_AGENT);
			httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			// Send post request
			httpURLConnection.setDoOutput(true);
			DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
			outputStream.writeBytes("");
			outputStream.flush();
			outputStream.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine).append("\n");
			}
			in.close();
			return response.toString();
		}
		catch (CoreKandilliException e) {
			final InternalErrorMailModel<CoreKandilliException> mailModel = new InternalErrorMailModel<>();
			mailModel.setEmailAddress(this.emailAddress);
			mailModel.setSendErrorEmailActive(this.sendErrorEmailActive);
			mailModel.setException(e);
			mailService.sendInternalErrorMail(mailModel);
			throw new CoreKandilliException("Core connection or parse error");
		}
	}

}
