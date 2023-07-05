package com.crypto.model.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Semih, 23.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class CoinsEntityInput {

    public String coin;

    public LocalDateTime createdDate;
    
    public LocalDateTime repositoryCreatedDate;

    private Boolean isRepository;
}
