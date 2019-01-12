package org.thehellnet.onlinegaming.servermanager.core.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.JsonResponse;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.request.LoginRequestDTO;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUser;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUserToken;
import org.thehellnet.onlinegaming.servermanager.core.service.AppUserRoleService;
import org.thehellnet.onlinegaming.servermanager.core.service.AppUserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/v1/public")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserRoleService appUserRoleRepository;

    @Autowired
    public AppUserController(AppUserService appUserService, AppUserRoleService appUserRoleRepository1) {
        this.appUserService = appUserService;
        this.appUserRoleRepository = appUserRoleRepository1;
    }

    @RequestMapping(
            path = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public JsonResponse login(@RequestBody LoginRequestDTO dto) {
        AppUser appUser = appUserService.findByEmailAndPassword(dto.email, dto.password);
        if (appUser == null) {
            return JsonResponse.getErrorInstance("User not found");
        }

        if (!appUserRoleRepository.appUserHasRoleTag(appUser, "login")) {
            return JsonResponse.getErrorInstance("User not enabled");
        }

        AppUserToken appUserToken = appUserService.newToken(appUser);

        Map<String, Object> data = new HashMap<>();
        data.put("token", appUserToken.getToken());
        data.put("expiration", appUserToken.getExpirationDateTime());

        return JsonResponse.getInstance(data);
    }
}
