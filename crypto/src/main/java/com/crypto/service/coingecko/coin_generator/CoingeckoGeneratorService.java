package com.crypto.service.coingecko.coin_generator;

import com.crypto.entity.CoinsEntity;
import com.crypto.exception.CryptoException;
import com.crypto.model.coingecko.CoingeckoApiResponse;
import com.crypto.model.coingecko.coin_generator.EntityGeneratorInput;
import com.crypto.model.coingecko.coin_generator.RepositoryGeneratorInput;
import com.crypto.model.input.CoinsEntityInput;
import com.crypto.repository.CoinsRepository;
import com.crypto.request.coingecko.CoinGeneratorRequest;
import com.crypto.type.EnvironmentType;
import com.crypto.type.GenerateRepoLocationType;
import com.squareup.javapoet.AnnotationSpec;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.crypto.Constant.Coingecko.EntityGenerator.CLASSES;

/**
 * Created by Semih, 23.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@AllArgsConstructor
//TODO Server üzerinden üretilen dosyaların auto generate yapılması için bir endpoint gerekiyor.
public class CoingeckoGeneratorService extends CoinGeneratorService {

    private final EntityGeneratorService entityGeneratorService;

    private final RepositoryGeneratorService repositoryGeneratorService;

    private final CoinsRepository coinsRepository;

    private final Environment environment;

    private static final String ENTITY_PACKAGE_NAME = "com.crypto.repository.";

    @SneakyThrows
    @Override
    public CoingeckoApiResponse<?> coingeckoCoinGenerator(CoinGeneratorRequest request) {

        final CoinsEntity controlCoinExist = coinsRepository.coinsControl(StringUtils.capitalize(request.getCoinName()));

        if (Objects.isNull(controlCoinExist)) {

            final List<AnnotationSpec> annotationSpecs = new ArrayList<>();

            for (Class<?> c: CLASSES) {
                final AnnotationSpec spec = AnnotationSpec.builder(c).build();
                annotationSpecs.add(spec);
            }

            // TODO İlk olarak entity yaratma işlemini yap ardından yapıldıktan sonra schedule servisin çalışmasını sağla

/*            final List<FieldSpecInput> fieldSpecInputs = FIELD_SPEC_INPUT_LIST;

            fieldSpecInputs.forEach(t -> {

                final FieldSpec.Builder fieldSpec = FieldSpec.builder(t.getFieldType(), t.getName())
                        .addModifiers(Modifier.PUBLIC);

                if (Objects.nonNull(t.getAnnotation())) {
                    fieldSpec.addAnnotation(t.getAnnotation());
                }

                fieldSpecs.add(fieldSpec.build());
            });*/

            final EntityGeneratorInput input = EntityGeneratorInput.builder()
                    .className(StringUtils.capitalize(request.getCoinName()))
                    .annotations(annotationSpecs)
                    //.fieldSpecs(fieldSpecs)
                    .build();

            try {

                entityGeneratorService.accept(input);
                final CoinsEntityInput coinsEntityInput = CoinsEntityInput.builder()
                        .coin(request.getCoinName())
                        .createdDate(LocalDateTime.now())
                        .isRepository(Boolean.FALSE)
                        .build();
                saveCoins(coinsEntityInput);
                return CoingeckoApiResponse.builder().data(Boolean.TRUE).build();
            }
            catch (Exception e) {
                log.error(e.getMessage());
                return CoingeckoApiResponse.builder().data(Boolean.FALSE).build();
            }
        }

        if (controlClass(request.getCoinName())) {

            controlRepository(request.getCoinName());
        }
        throw new CryptoException("Coins has exist.");
    }

    @Override
    protected void saveCoins(CoinsEntityInput input) {

        final CoinsEntity controlCoinExist = coinsRepository.coinsControl(input.getCoin());

        if (Objects.isNull(controlCoinExist)) {
            final CoinsEntity coinsEntity = CoinsEntity.builder()
                    .coin(StringUtils.capitalize(input.getCoin()))
                    .createdDate(input.getCreatedDate())
                    .isRepository(input.getIsRepository())
                    .repoLocationType(environment.getActiveProfiles()[0].equals(EnvironmentType.dev.toString()) ? GenerateRepoLocationType.LOCAL : GenerateRepoLocationType.PROD)
                    .build();
            coinsRepository.save(coinsEntity);
            log.info("New coins add into the CoinsEntity");
        }
        else {
            log.info("{} has exist, control the repository", input.getCoin());
        }
        log.info(input.coin, "{}, has exist");
    }


    @Override
    protected void controlRepository(String coinName) {

        final String coin = StringUtils.capitalize(coinName);
        final CoinsEntity controlCoinExist = coinsRepository.coinsRepoControl(coin);

        if (Objects.nonNull(controlCoinExist)) {

            if (Boolean.FALSE.equals(controlCoinExist.getIsRepository())) {
                final RepositoryGeneratorInput repositoryGeneratorInput = RepositoryGeneratorInput.builder()
                        .className(coin)
                        .build();
                try {

                    final String repositoryClassName = repositoryGeneratorService.apply(repositoryGeneratorInput);
                    log.info("{} has create the repository", coin);
                    controlCoinExist.setIsRepository(Boolean.TRUE);
                    controlCoinExist.setRepositoryCreatedDate(LocalDateTime.now());
                    controlCoinExist.setRepositoryName(repositoryClassName);
                    coinsRepository.save(controlCoinExist);
                }
                catch (Exception e) {
                    log.error("{} has create the repository fail", coin);
                }
            }
            else {
                log.info("{} has has exist the repository", coin);
            }
        }
    }

    @SneakyThrows
    private Boolean controlClass(String input) {
        final Class<?> entity = Class.forName(ENTITY_PACKAGE_NAME.concat(StringUtils.capitalize(input)));

        return Objects.nonNull(entity);

    }
}
