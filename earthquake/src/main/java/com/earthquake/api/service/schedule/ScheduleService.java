package com.earthquake.api.service.schedule;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.entity.AFADEarthquakeEntity;
import com.earthquake.api.entity.KandilliEarthQuakeEntity;
import com.earthquake.api.entity.UserEntity;
import com.earthquake.api.entity.WorldEarthQuakeEntity;
import com.earthquake.api.model.ConfigModel;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.model.world.WorldEarthQuakeModel;
import com.earthquake.api.model.world.WorldEarthQuakeSimpleModel;
import com.earthquake.api.repository.AFADEarthquakeRepository;
import com.earthquake.api.repository.KandilliEarthQuakeRepository;
import com.earthquake.api.repository.UserRepository;
import com.earthquake.api.repository.WorldEarthQuakeRepository;
import com.earthquake.api.request.EarthQuakeRequest;
import com.earthquake.api.request.SendEarthQuakeEmailRequest;
import com.earthquake.api.request.StoreAllEarthQuakeRequest;
import com.earthquake.api.request.TokenRequest;
import com.earthquake.api.response.EarthQuakeResponse;
import com.earthquake.api.response.EarthQuakeSimpleResponse;
import com.earthquake.api.response.ExcelResponse;
import com.earthquake.api.response.world.WorldEarthQuakeGenericResponse;
import com.earthquake.api.service.EarthQuakeService;
import com.earthquake.api.service.config.GetConfigsService;
import com.earthquake.api.service.core.CoreAfadService;
import com.earthquake.api.service.core.CoreAfadV2Service;
import com.earthquake.api.service.filter.AfadAllEarthQuakeListService;
import com.earthquake.api.service.filter.AllEarthQuakeListService;
import com.earthquake.api.service.mail.MailService;
import com.earthquake.api.service.token.TokenService;
import com.earthquake.api.service.world.WorldEarthQuakeService;
import com.earthquake.api.shared.util.UniqueIdGenerator;
import com.earthquake.api.type.AutoEmailStatusType;
import com.earthquake.api.type.InstitutionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by Semih, 18.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Component
public class ScheduleService {

    private final TokenService tokenService;

    private final EarthQuakeService earthQuakeService;

    private final UserRepository userRepository;

    private final KandilliEarthQuakeRepository kandilliEarthQuakeRepository;

    private final AfadAllEarthQuakeListService afadAllEarthQuakeListService;

    private final CoreAfadService coreAfadService;

    private final CoreAfadV2Service coreAfadV2Service;

    private final AFADEarthquakeRepository afadEarthquakeRepository;

    private final AllEarthQuakeListService coreKandilliService;

    private final MailService mailService;

    private final GetConfigsService getConfigsService;

    private final WorldEarthQuakeService worldEarthQuakeService;

    private final WorldEarthQuakeRepository worldEarthQuakeRepository;

    private static final String IS_DAILY = "dailyAutoSendEmail";

    private static final String ALL_DATA = "autoAllDataSendEmail";

    public ScheduleService(TokenService tokenService, EarthQuakeService earthQuakeService, KandilliEarthQuakeRepository kandilliEarthQuakeRepository,
                           AfadAllEarthQuakeListService afadAllEarthQuakeListService, CoreAfadService coreAfadService,
                           AFADEarthquakeRepository afadEarthquakeRepository, AllEarthQuakeListService coreKandilliService,
                           UserRepository userRepository, CoreAfadV2Service coreAfadV2Service, MailService mailService, GetConfigsService getConfigsService, WorldEarthQuakeService worldEarthQuakeService, WorldEarthQuakeRepository worldEarthQuakeRepository) {
        this.tokenService = tokenService;
        this.earthQuakeService = earthQuakeService;
        this.kandilliEarthQuakeRepository = kandilliEarthQuakeRepository;
        this.afadAllEarthQuakeListService = afadAllEarthQuakeListService;
        this.coreAfadService = coreAfadService;
        this. afadEarthquakeRepository = afadEarthquakeRepository;
        this.coreKandilliService = coreKandilliService;
        this.userRepository = userRepository;
        this.coreAfadV2Service = coreAfadV2Service;
        this.mailService = mailService;
        this.getConfigsService = getConfigsService;
        this.worldEarthQuakeService = worldEarthQuakeService;
        this.worldEarthQuakeRepository = worldEarthQuakeRepository;
    }

    /**
     * Every 5 minutes add earthquake info. Date is primary key for Earthquake time
     */
    //@Scheduled(cron = "0 0/5 * 1/1 * ?")
    @Scheduled(cron = "0 0 0/2 ? * *")
    public void scheduleForKandilliAllEarthQuake() {

        try {
            final EarthQuakeResponse coreResponse = EarthQuakeResponse.builder().quakeInfoList(coreKandilliService.getAllEarthQuakes()).build();
            for (EarthQuakeInfo info : coreResponse.getQuakeInfoList()) {
                final KandilliEarthQuakeEntity kandilliEarthQuakeEntity = KandilliEarthQuakeEntity.builder()
                        .depth(info.getDepth())
                        .date(info.getDate())
                        .latitude(info.getLatitude())
                        .location(info.getLocationName())
                        .longitude(info.getLongitude())
                        .magnitude(info.getMagnitude())
                        .earthQuakeId(UniqueIdGenerator.uniqueId())
                        .build();
                kandilliEarthQuakeRepository.save(kandilliEarthQuakeEntity);
            }

            log.info("from kandilli add all earthquake info into db....");

        }
        catch (Exception e) {
            log.error("Kandilli Schedule Service error {}", e.getMessage());
        }
    }

    //@Scheduled(cron = "0 0/5 * 1/1 * ?")
    @Scheduled(cron = "0 0 0/2 ? * *")
    public void scheduleForAfadAllEarthQuake() {

        try {

            final EarthQuakeResponse infoList = afadAllEarthQuakeListService.apply(coreAfadV2Service.coreAfadService());

            for (EarthQuakeInfo earthQuakeInfo : infoList.getAfadQuakeList()) {
                final AFADEarthquakeEntity earthQuake = AFADEarthquakeEntity.builder()
                        .depth(earthQuakeInfo.getDepth())
                        .date(earthQuakeInfo.getDate())
                        .latitude(earthQuakeInfo.getLatitude())
                        .location(earthQuakeInfo.getLocationName())
                        .longitude(earthQuakeInfo.getLongitude())
                        .magnitude(earthQuakeInfo.getMagnitude())
                        .earthQuakeId(UniqueIdGenerator.uniqueId())
                        .build();

                afadEarthquakeRepository.save(earthQuake);
            }

            log.info("from afad add all earthquake info into db....");

        }
        catch (Exception e) {
            log.error("Afad Schedule Service error {}", e.getMessage());
        }
    }

    //@Scheduled(cron = "0 0/5 * 1/1 * ?")
    @Scheduled(cron = "0 0 0/2 ? * *")
    public void scheduleForWordEarthQuake() {

        try {

            final WorldEarthQuakeGenericResponse<WorldEarthQuakeModel> response = worldEarthQuakeService.worldScheduleService();
            final WorldEarthQuakeSimpleModel simpleModel = response.getResponse().getSimpleModel();

            simpleModel.getEarthQuakeList().forEach(t-> {

                final WorldEarthQuakeEntity entity = WorldEarthQuakeEntity.builder()
                        .earthquakeId(t.getId())
                        .date(t.getTime())
                        .magnitude(t.getMag())
                        .latitude(t.getGeometry().getCoordinates().get(0))
                        .longitude(t.getGeometry().getCoordinates().get(1))
                        .location(t.getPlace())
                        .build();

                worldEarthQuakeRepository.save(entity);

            });



        }
        catch (Exception e) {
            log.error("World Schedule Service error {}", e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0/2 ? * *")
    //@Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void autoHighestEarthquakeSendEmail() {

        try {

            TokenRequest tokenRequest = new TokenRequest();
            tokenRequest.setInstitutionType(InstitutionType.KANDILLI);

            EarthQuakeRequest request = new EarthQuakeRequest();
            request.setInstitutionType(InstitutionType.KANDILLI);
            request.setToken(tokenService.generateToken(tokenRequest));

            earthQuakeService.allList(request);

            final List<UserEntity> list = userRepository.findByIsSendEmailActive();

            final EarthQuakeSimpleResponse<EarthQuakeInfo, EarthQuakeInfo> highestMagnitudeResponse = earthQuakeService.highestMagnitude(request);

            list.stream().filter(t -> Boolean.TRUE.equals(t.getIsSendEmailActive())).forEach(t -> {

                SendEarthQuakeEmailRequest sendEarthQuakeEmailRequest = new SendEarthQuakeEmailRequest();
                sendEarthQuakeEmailRequest.setEmail(t.getEmail());
                sendEarthQuakeEmailRequest.setInstitutionType(InstitutionType.KANDILLI);

                try {

                    mailService.sendHigherMagnitudeEarthQuakeViaMail(sendEarthQuakeEmailRequest, highestMagnitudeResponse);

                } catch (MessagingException e) {
                    log.error("Schedule service error is {}", e.getMessage());
                    e.printStackTrace();
                }
                log.info("User email is {}", t.getEmail());
            });

        } catch (Exception e) {
            log.error("Service error {}", e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0/2 ? * *") //TODO Only one time in a day
    public void autoAllDataSendEmail() {

        final ConfigModel configModel = getConfigsService.apply(ALL_DATA);

        if (AutoEmailStatusType.ACTIVE.toString().equals(configModel.getSecondValue())) {

            StoreAllEarthQuakeRequest requestKandilli = new StoreAllEarthQuakeRequest();
            requestKandilli.setInstitutionType(InstitutionType.KANDILLI);

            StoreAllEarthQuakeRequest requestAfad = new StoreAllEarthQuakeRequest();
            requestAfad.setInstitutionType(InstitutionType.AFAD);

            ExcelResponse afadExcel = earthQuakeService.generateStoreDataExcel(requestAfad);
            ExcelResponse kandilliExcel = earthQuakeService.generateStoreDataExcel(requestKandilli);

            final List<UserEntity> list = userRepository.findByIsSendEmailActive();

            list.stream().filter(t -> Boolean.TRUE.equals(t.getIsSendEmailActive())).forEach(t -> {

                SendEarthQuakeEmailRequest sendEarthQuakeEmailRequest = new SendEarthQuakeEmailRequest();
                sendEarthQuakeEmailRequest.setEmail(t.getEmail());
                sendEarthQuakeEmailRequest.setInstitutionType(t.getInstitutionType());

                if (Boolean.TRUE.equals(t.getAllDataExcelSheetActive())) {

                    if (InstitutionType.KANDILLI.equals(t.getInstitutionType())) {

                        mailService.sendEmailWithByteArray(sendEarthQuakeEmailRequest, kandilliExcel, Constant.Excel.GENERAL_EXCEL_FILE_NAME);
                        log.info("Send Kandilli Data Excel");
                    }
                    else {
                        mailService.sendEmailWithByteArray(sendEarthQuakeEmailRequest, afadExcel, Constant.Excel.GENERAL_EXCEL_FILE_NAME);
                        log.info("Send Afad Data Excel");
                    }
                }

                log.info("User email is {}", t.getEmail());
            });
        }
        else {
            log.info("All Data email is disabled!");
        }
    }

    //@Scheduled(cron = "0 0/5 * 1/1 * ?")
    @Scheduled(cron = "0 1 1 * * ?") //TODO Only one time in a day
    public void autoDailySendEmail() {

        final ConfigModel configModel = getConfigsService.apply(IS_DAILY);

        if (AutoEmailStatusType.ACTIVE.toString().equals(configModel.getSecondValue())) {

            TokenRequest tokenKandilli = new TokenRequest();
            tokenKandilli.setInstitutionType(InstitutionType.KANDILLI);

            EarthQuakeRequest requestKandilli = new EarthQuakeRequest();
            requestKandilli.setInstitutionType(InstitutionType.KANDILLI);
            requestKandilli.setToken(tokenService.generateToken(tokenKandilli));

            TokenRequest tokenAfad = new TokenRequest();
            tokenAfad.setInstitutionType(InstitutionType.KANDILLI);

            EarthQuakeRequest requestAfad = new EarthQuakeRequest();
            requestAfad.setInstitutionType(InstitutionType.AFAD);
            requestAfad.setToken(tokenService.generateToken(tokenAfad));

            ExcelResponse afadExcel = earthQuakeService.generateExcel(requestAfad);
            ExcelResponse kandilliExcel = earthQuakeService.generateExcel(requestKandilli);

            final List<UserEntity> list = userRepository.findByIsSendEmailActive();

            list.stream().filter(t -> Boolean.TRUE.equals(t.getIsSendEmailActive())).forEach(t -> {

                SendEarthQuakeEmailRequest sendEarthQuakeEmailRequest = new SendEarthQuakeEmailRequest();
                sendEarthQuakeEmailRequest.setEmail(t.getEmail());
                sendEarthQuakeEmailRequest.setInstitutionType(t.getInstitutionType());

                if (Boolean.TRUE.equals(t.getIsDailyExcelSheetActive())) {

                    if (InstitutionType.KANDILLI.equals(t.getInstitutionType())) {

                        mailService.sendEmailWithByteArray(sendEarthQuakeEmailRequest, kandilliExcel, Constant.Excel.GENERAL_EXCEL_FILE_NAME);
                        log.info("Send Daily Kandilli Data Excel");
                    }
                    else {
                        mailService.sendEmailWithByteArray(sendEarthQuakeEmailRequest, afadExcel, Constant.Excel.GENERAL_EXCEL_FILE_NAME);
                        log.info("Send Daily Afad Data Excel");
                    }
                }

                log.info("User email is {}", t.getEmail());
            });
        }
        else {
            log.info("Is daily email is disabled!");
        }

    }
}
