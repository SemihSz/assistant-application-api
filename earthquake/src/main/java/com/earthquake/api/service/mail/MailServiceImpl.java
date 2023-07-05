package com.earthquake.api.service.mail;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.entity.EmailStatusEntity;
import com.earthquake.api.exception.CoreKandilliException;
import com.earthquake.api.freemarker.template.FreemarkerTemplateProcessor;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.model.EmailInfoModel;
import com.earthquake.api.model.InternalErrorMailModel;
import com.earthquake.api.repository.EmailStatusRepository;
import com.earthquake.api.request.SendEarthQuakeEmailRequest;
import com.earthquake.api.request.admin.SendAdminEmailRequest;
import com.earthquake.api.response.EarthQuakeDistanceResponse;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.response.EarthQuakeSimpleResponse;
import com.earthquake.api.response.ExcelResponse;
import com.earthquake.api.type.MailType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.*;

/**
 * Created by Semih, 29.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final MessageSource messageSource;

    private final Environment environment;

    private final FreemarkerTemplateProcessor freemarkerTemplateProcessor;

    private final EmailStatusRepository emailStatusRepository;


    /**
     * Send email services.
     * If the filename is null, the service will not add an attachment to the email.
     * If the filename is not null, the service will add an attachment to the mail.
     *
     * @param EmailInfoModel
     * @param @Nullable      String
     * @throws MessagingException
     */
    @SneakyThrows
    private void sendMail(EmailInfoModel model, @Nullable String fileName, @Nullable ByteArrayInputStream byteArrayInputStream, @Nullable String attachmentName) throws MessagingException {

        // TODO Mail servisinin çalışır durumda olmasını kontrol et. Admin tarafından kapatılır bir durumda olur.

        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setCc(model.getTo());
        mimeMessageHelper.setSubject(model.getSubject());
        mimeMessageHelper.setText(model.getBody());

        if (Objects.nonNull(model.getMailBody())) {
            final String html = freemarkerTemplateProcessor.apply("mail.html", model.getMailBody());
            mimeMessageHelper.setText(html, true);
        }

        FileInputStream fis = null;
        if (Objects.nonNull(fileName)) {
            try {

                //No read permission
                final File file = new File(fileName);

                if (!file.canRead())
                    file.setReadable(true);
                mimeMessageHelper.addAttachment("earth-quake.xlsx", file);
                fis = new FileInputStream(file);
                mailSender.send(mimeMessage);

            } catch (FileNotFoundException er) {
                log.info("Email send fail....");
                log.error(er.getMessage(), er);

            } finally {

                try {

                    if (fis != null)
                        fis.close();

                } catch (IOException ioe) {
                    log.info("Email send fail....");
                    log.error(ioe.getMessage(), ioe);
                }
            }
        } else if (Objects.nonNull(byteArrayInputStream)) {
            final InputStream inputStream = byteArrayInputStream;
            final DataSource attachment = new ByteArrayDataSource(inputStream, "application/octet-stream");

            mimeMessageHelper.addAttachment(attachmentName, attachment);
            mailSender.send(mimeMessage);
        } else {
            mailSender.send(mimeMessage);
        }


        final EmailStatusEntity statusEntity = EmailStatusEntity.builder()
                .date(new Date())
                .email(model.getTo())
                .mailType(model.getMailType())
                .build();

        emailStatusRepository.save(statusEntity);

        log.info("Email send successfully..");
    }

    /**
     * Sending via email excel file containing 500 earthquake information.
     *
     * @param SendEmailRequest
     * @param EarthQuakeResponse
     * @param fileName
     */
    @SneakyThrows
    @Override
    public void sendExcelViaMail(SendEarthQuakeEmailRequest request, EarthQuakeResponse response, String fileName) {

        try {

            final Object[] objects = {response.getQuakeInfoList().size()};
            final EmailInfoModel infoModel = EmailInfoModel.builder()
                    .to(request.getEmail())
                    .subject(messageSource.getMessage(Constant.Message.EXCEL_EMAIL_SUBJECT, objects, Locale.ENGLISH))
                    .body(messageSource.getMessage(Constant.Message.EXCEL_EMAIL_BODY, objects, Locale.ENGLISH))
                    .mailType(request.getMailType())
                    .build();

            sendMail(infoModel, null, null, null);
        } catch (Exception e) {
            log.info("Email send fail....");
            throw new MessagingException("Email send fail....");
        }
    }

    /**
     * Send filter earthquakes information via mail.
     *
     * @param SendEmailRequest
     * @param fileName
     */
    @SneakyThrows
    @Override
    public void sendFilterExcelViaMail(SendEarthQuakeEmailRequest request, ExcelResponse response, String fileName) {

        try {

            final EmailInfoModel infoModel = EmailInfoModel.builder()
                    .to(request.getEmail())
                    .subject(messageSource.getMessage(Constant.Message.EXCEL_FILTER_SUBJECT, null, Locale.ENGLISH))
                    .body(messageSource.getMessage(Constant.Message.EXCEL_FILTER_BODY, null, Locale.ENGLISH))
                    .mailType(request.getMailType())
                    .build();


            sendMail(infoModel, null, response.getByteArrayInputStream(), null);

        } catch (Exception e) {
            log.info("Email send fail....");
            throw new MessagingException("Email send fail....");
        }
    }

    @SneakyThrows
    @Override
    public void sendEmailWithByteArray(SendEarthQuakeEmailRequest request, ExcelResponse response, String fileName) {

        try {
            final EmailInfoModel infoModel = EmailInfoModel.builder()
                    .to(request.getEmail())
                    .subject(messageSource.getMessage(Constant.Message.EXCEL_FILTER_SUBJECT, null, Locale.ENGLISH))
                    .body(messageSource.getMessage(Constant.Message.EXCEL_FILTER_BODY, null, Locale.ENGLISH))
                    .mailType(request.getMailType())
                    .build();


            sendMail(infoModel, null, response.getByteArrayInputStream(), fileName);

        } catch (Exception e) {
            log.info("Email send fail....");
            throw new MessagingException("Email send fail....");
        }
    }

    /**
     * Send closest earthquakes information for user location.
     *
     * @param SendEmailRequest
     * @param EarthQuakeDistanceResponse
     */
    @SneakyThrows
    @Override
    public void sendClosestEarthQuakeViaMail(SendEarthQuakeEmailRequest request, EarthQuakeDistanceResponse response) {

        final Object[] objects = {
                response.getLocation(),
                response.getDistance(),
                response.getMagnitude(),
                response.getQuakeLatitude(),
                response.getQuakeLongitude()
        };

        final EmailInfoModel infoModel = EmailInfoModel.builder()
                .to(request.getEmail())
                .subject(messageSource.getMessage(Constant.Message.CLOSEST_EARTH_QUAKE_SUBJECT, null, Locale.ENGLISH))
                .body(messageSource.getMessage(Constant.Message.CLOSEST_EARTH_QUAKE_BODY, objects, Locale.ENGLISH))
                .mailType(request.getMailType())
                .build();

        try {

            sendMail(infoModel, null, null, null);
        } catch (Exception e) {
            log.info("Email send fail....");
            throw new MessagingException("Email send fail....");
        }

    }

    /**
     * Send Highest Magnitude Information From Current List.
     *
     * @param SendEmailRequest
     */
    @SneakyThrows
    @Override
    public void sendHigherMagnitudeEarthQuakeViaMail(SendEarthQuakeEmailRequest request, EarthQuakeSimpleResponse<EarthQuakeInfo, EarthQuakeInfo> response) {

        final EarthQuakeInfo highestInfo = response.getFirstInfo();
        final Object[] objects = {highestInfo.getMagnitude(), highestInfo.getDepth(), highestInfo.getLocationName()};
        Map<String, Object> freeMakerModel = new HashMap<>();
        freeMakerModel.put("magnitude", highestInfo.getMagnitude());
        freeMakerModel.put("depth", highestInfo.getDepth());
        freeMakerModel.put("location", highestInfo.getLocationName());
        freeMakerModel.put("latitude", highestInfo.getLatitude());
        freeMakerModel.put("longitude", highestInfo.getLongitude());

        final EmailInfoModel infoModel = EmailInfoModel.builder()
                .to(request.getEmail())
                .subject(messageSource.getMessage(Constant.Message.HIGHEST_MAGNITUDE_EMAIL_SUBJECT, null, Locale.ENGLISH))
                .body(messageSource.getMessage(Constant.Message.HIGHEST_MAGNITUDE_EMAIL_BODY, objects, Locale.ENGLISH))
                .mailBody(freeMakerModel)
                .mailType(request.getMailType())
                .build();

        try {

            sendMail(infoModel, null, null, null);
        } catch (Exception e) {
            log.info("Email send fail....");
            throw new MessagingException(e.getMessage());
        }

    }

    @SneakyThrows
    @Override
    public void sendInternalErrorMail(InternalErrorMailModel model) {

        if (model.getException() instanceof CoreKandilliException) {
            if (model.isSendErrorEmailActive()) {
                final String activeProfile = environment.getActiveProfiles()[0];
                final String stackTrace = ExceptionUtils.getStackTrace((Throwable) model.getException());
                final Object[] objects = {stackTrace};
                final EmailInfoModel infoModel = EmailInfoModel.builder()
                        .to(model.getEmailAddress())
                        .subject(messageSource.getMessage(Constant.Message.CORE_ERROR_MAIL_SUBJECT, new Object[]{activeProfile}, Locale.ENGLISH))
                        .body(messageSource.getMessage(Constant.Message.CORE_ERROR_MAIL_BODY, objects, Locale.ENGLISH))
                        .build();
                try {

                    sendMail(infoModel, null, null, null);
                } catch (Exception e) {
                    log.info("Email send fail....");
                    throw new MessagingException("Email send fail....");
                }
            } else {
                log.info("Send error mail is disable.");
            }

        }
    }

    @SneakyThrows
    @Override
    public int sendAuthenticationTokenViaMail(SendAdminEmailRequest request, String token) {

        final Object[] objects = {token};

        final EmailInfoModel infoModel = EmailInfoModel.builder()
                .to(request.getEmail())
                .subject(messageSource.getMessage(Constant.Message.ADMIN_TOKEN, null, Locale.ENGLISH))
                .body(messageSource.getMessage(Constant.Message.ADMIN_TOKEN_BODY, objects, Locale.ENGLISH))
                .mailType(MailType.ADMIN_TOKEN)
                .build();

        try {

            sendMail(infoModel, null, null, null);

            return 200;

        } catch (Exception e) {
            log.info("Email send fail....");
            throw new MessagingException("Email send fail....");
        }

    }
}
