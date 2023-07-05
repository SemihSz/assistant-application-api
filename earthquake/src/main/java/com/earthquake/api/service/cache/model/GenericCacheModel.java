package com.earthquake.api.service.cache.model;

import com.earthquake.api.type.InstitutionType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 7.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class GenericCacheModel<T> {

    private InstitutionType institutionType;

    private T data;

}
