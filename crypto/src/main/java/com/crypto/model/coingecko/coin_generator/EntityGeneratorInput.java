package com.crypto.model.coingecko.coin_generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 21.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class EntityGeneratorInput {

    private String className;

    private String packageName;

    private String pats;

    private Iterable<FieldSpec> fieldSpecs;

    private Iterable<AnnotationSpec> annotations;


}
