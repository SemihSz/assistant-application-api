package com.earthquake.api.service.admin;

import com.earthquake.api.request.admin.*;
import com.earthquake.api.response.admin.*;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 12.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
public interface AdminService {

    AdminEmailStatusResponse emailListStatus(AdminEmailRequest request);

    AdminUserResponse showUsers(AdminUserRequest request);

    AdminUserResponse filterUsers(AdminUserRequest request);

    AdminServiceUsageResponse serviceUsage(AdminServiceUsageRequest request);

    AdminConfigResponse configAddOrUpdate(AdminConfigRequest request);

    AdminSaveResponse saveAdmin(AdminSaveRequest request);

    AdminSaveResponse updateAdmin(AdminSaveRequest request);

    AdminSaveResponse loginAdmin(AdminLoginRequest request);

}
