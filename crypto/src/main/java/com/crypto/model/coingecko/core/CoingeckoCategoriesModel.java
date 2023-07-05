package com.crypto.model.coingecko.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Semih, 9.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoingeckoCategoriesModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("name")
    private String name;
}
