package com.crypto.service.coingecko.coin_generator;

import com.crypto.entity.AbstractBaseEntity;
import com.crypto.model.coingecko.coin_generator.EntityGeneratorInput;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

/**
 * Created by Semih, 21.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EntityGeneratorService implements Consumer<EntityGeneratorInput> {

    private static final String PACKAGE_NAME = "com.crypto.entity";

    private static final String PATS = "src/main/java";

    @SneakyThrows
    @Override
    public void accept(EntityGeneratorInput entityGeneratorInput) {

        final TypeSpec spec = TypeSpec
                .classBuilder(entityGeneratorInput.getClassName())
                .addAnnotations(entityGeneratorInput.getAnnotations())
                .superclass(AbstractBaseEntity.class)
                //.addFields(entityGeneratorInput.getFieldSpecs())
                .addModifiers(Modifier.PUBLIC).build();

        final JavaFile javaFile = JavaFile.builder(PACKAGE_NAME, spec)
                .indent("    ")
                .build();

        final Path path = Paths.get(PATS);
        javaFile.writeTo(path);

    }
}
