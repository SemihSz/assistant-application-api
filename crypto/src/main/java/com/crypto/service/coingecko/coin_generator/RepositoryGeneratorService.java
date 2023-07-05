package com.crypto.service.coingecko.coin_generator;

import com.crypto.model.coingecko.coin_generator.RepositoryGeneratorInput;
import com.crypto.repository.AbstractBaseRepository;
import com.assistant.commonapi.task.SimpleTask;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Semih, 21.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RepositoryGeneratorService implements SimpleTask<RepositoryGeneratorInput, String> {

    private static final String PACKAGE_NAME = "com.crypto.repository";

    private static final String ENTITY_PACKAGE_NAME = "com.crypto.entity.";

    private static final String PATS = "src/main/java";

    private static final String REPOSITORY = "Repository";

    @SneakyThrows
    @Override
    public String apply(RepositoryGeneratorInput repositoryGeneratorInput) {
        final Class<?> entity = Class.forName(ENTITY_PACKAGE_NAME.concat(repositoryGeneratorInput.getClassName()));

        final TypeSpec repository = TypeSpec.interfaceBuilder(repositoryGeneratorInput.getClassName().concat(REPOSITORY))
                .addAnnotation(Repository.class)
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get(AbstractBaseRepository.class),
                        ClassName.get(entity),
                        ClassName.get(String.class)))
                .addModifiers(Modifier.PUBLIC)
                .build();

        final JavaFile javaFile = JavaFile.builder(PACKAGE_NAME, repository)
                .indent("    ")
                .build();

        final Path path = Paths.get(PATS);
        javaFile.writeTo(path);

        return repositoryGeneratorInput.getClassName().concat(REPOSITORY);
    }

}
