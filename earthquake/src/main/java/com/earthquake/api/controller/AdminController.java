package com.earthquake.api.controller;

import com.earthquake.api.model.RestResponse;
import com.earthquake.api.request.admin.*;
import com.earthquake.api.response.admin.*;
import com.earthquake.api.service.admin.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.earthquake.api.constant.Constant.SUCCESS_CODE;

/**
 * Created by Semih, 12.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RestController
@RequestMapping("/com/earthquake/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping(value = "/email-status", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AdminEmailStatusResponse>> emailStatus(AdminEmailRequest request) {

        return ResponseEntity.ok(new RestResponse<AdminEmailStatusResponse>(SUCCESS_CODE, adminService.emailListStatus(request)));
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AdminUserResponse>> showUsers(AdminUserRequest request) {

        return ResponseEntity.ok(new RestResponse<AdminUserResponse>(SUCCESS_CODE, adminService.showUsers(request)));
    }

    @PostMapping(value = "/users-filter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AdminUserResponse>> usersFilter(AdminUserRequest request) {

        return ResponseEntity.ok(new RestResponse<AdminUserResponse>(SUCCESS_CODE, adminService.filterUsers(request)));
    }

    @PostMapping(value = "/service-usage", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AdminServiceUsageResponse>> serviceUsage(AdminServiceUsageRequest request) {

        return ResponseEntity.ok(new RestResponse<AdminServiceUsageResponse>(SUCCESS_CODE, adminService.serviceUsage(request)));
    }

    @PostMapping(value = "/config-settings", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AdminConfigResponse>> configAddOrUpdate(AdminConfigRequest request) {

        return ResponseEntity.ok(new RestResponse<AdminConfigResponse>(SUCCESS_CODE, adminService.configAddOrUpdate(request)));
    }

    @PostMapping(value = "/save-account", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AdminSaveResponse>> saveAdmin(AdminSaveRequest request) {

        return ResponseEntity.ok(new RestResponse<AdminSaveResponse>(SUCCESS_CODE, adminService.saveAdmin(request)));
    }

    @PostMapping(value = "/login-account", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<AdminSaveResponse>> loginAdmin(AdminLoginRequest request) {

        return ResponseEntity.ok(new RestResponse<AdminSaveResponse>(SUCCESS_CODE, adminService.loginAdmin(request)));
    }

}
