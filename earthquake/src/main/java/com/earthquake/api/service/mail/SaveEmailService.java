package com.earthquake.api.service.mail;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.entity.UserEntity;
import com.earthquake.api.repository.UserRepository;
import com.earthquake.api.request.SaveEmailRequest;
import com.earthquake.api.service.usage.UsageCountService;
import com.earthquake.api.shared.util.UserIdGenerator;
import com.assistant.commonapi.task.SimpleTask;
import com.earthquake.api.type.SaveEmailType;
import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by Semih, 21.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SaveEmailService implements SimpleTask<SaveEmailRequest, String> {

    private final UserIdGenerator userIdGenerator;

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    private final UsageCountService usageCountService;

    @Override
    public String apply(SaveEmailRequest request) {

        final UserEntity controlUser = userRepository.findByEmail(request.getEmail());

        if (Objects.nonNull(controlUser)) {

            if (SaveEmailType.ADD.equals(request.getEmailType())) {

                usageCountService.accept(Constant.Request.SAVE_EMAIL, ServiceUsageStatusType.SUCCESS);
                throw new RuntimeException(messageSource.getMessage(Constant.Message.SAVE_EMAIL_FAIL, null, Locale.ENGLISH));
            }

            else if (SaveEmailType.UPDATE.equals(request.getEmailType())) {

                controlUser.setEmail(Objects.nonNull(request.getNewEmail()) ? request.getNewEmail() : controlUser.getEmail());
                controlUser.setAllDataExcelSheetActive(Objects.nonNull(request.getIsDailyExcelSheetActive()) ? request.getIsDailyExcelSheetActive() : controlUser.getAllDataExcelSheetActive());
                controlUser.setIsSendEmailActive(Objects.nonNull(request.getIsSendEmailActive()) ? request.getIsSendEmailActive() : controlUser.getIsSendEmailActive());
                controlUser.setIsDailyExcelSheetActive(Objects.nonNull(request.getIsDailyExcelSheetActive()) ? request.getIsDailyExcelSheetActive() : controlUser.getIsDailyExcelSheetActive());
                controlUser.setIsHighestEarthQuakeActive(Objects.nonNull(request.getIsHighestEarthQuakeActive()) ? request.getIsHighestEarthQuakeActive() : controlUser.getIsHighestEarthQuakeActive());
                controlUser.setInstitutionType(Objects.nonNull(request.getInstitutionType()) ? request.getInstitutionType() : controlUser.getInstitutionType());
                userRepository.save(controlUser);
            }

            else {
                userRepository.delete(controlUser);
            }

        }
        else {

            final UserEntity userEntity = UserEntity.builder()
                    .userId(userIdGenerator.generateRandomString())
                    .email(request.getEmail())
                    .isSendEmailActive(request.getIsSendEmailActive())
                    .allDataExcelSheetActive(Objects.nonNull(request.getIsDailyExcelSheetActive()) ? request.getIsDailyExcelSheetActive() : false)
                    .isDailyExcelSheetActive(Objects.nonNull(request.getAllDataExcelSheetActive()) ? request.getAllDataExcelSheetActive() : false)
                    .isHighestEarthQuakeActive(Objects.nonNull(request.getIsHighestEarthQuakeActive()) ? request.getIsHighestEarthQuakeActive() : false)
                    .institutionType(request.getInstitutionType())
                    .build();

            userRepository.save(userEntity);
        }

        usageCountService.accept(Constant.Request.SAVE_EMAIL, ServiceUsageStatusType.FAIL);

        return messageSource.getMessage(Constant.Message.SAVE_EMAIL_SUCCESS, null, Locale.ENGLISH);
    }

}
