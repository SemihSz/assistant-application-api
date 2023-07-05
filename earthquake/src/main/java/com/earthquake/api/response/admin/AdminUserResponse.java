package com.earthquake.api.response.admin;

import com.earthquake.api.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by Semih, 12.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class AdminUserResponse {

    private List<UserEntity> userList;
}
