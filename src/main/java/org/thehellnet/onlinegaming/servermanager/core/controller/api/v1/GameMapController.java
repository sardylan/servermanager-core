package org.thehellnet.onlinegaming.servermanager.core.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thehellnet.onlinegaming.servermanager.core.model.constant.Role;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.JsonResponse;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.request.token.GameMapListDTO;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUser;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Game;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.GameMap;
import org.thehellnet.onlinegaming.servermanager.core.repository.GameMapRepository;
import org.thehellnet.onlinegaming.servermanager.core.repository.GameRepository;
import org.thehellnet.onlinegaming.servermanager.core.service.AppUserService;
import org.thehellnet.onlinegaming.servermanager.core.service.AppUserTokenService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/v1/public/gamemap")
public class GameMapController {

    private final AppUserTokenService appUserTokenService;
    private final AppUserService appUserService;
    private final GameRepository gameRepository;
    private final GameMapRepository gameMapRepository;

    @Autowired
    public GameMapController(AppUserTokenService appUserTokenService, AppUserService appUserService, GameRepository gameRepository, GameMapRepository gameMapRepository) {
        this.appUserTokenService = appUserTokenService;
        this.appUserService = appUserService;
        this.gameRepository = gameRepository;
        this.gameMapRepository = gameMapRepository;
    }

    @RequestMapping(
            path = "/list",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public JsonResponse list(@RequestBody GameMapListDTO dto) {
        AppUser appUser = appUserTokenService.getAppUserByToken(dto.token);
        if (appUser == null) {
            return JsonResponse.getErrorInstance("Token not enabled");
        }

        if (!appUserService.hasRoles(appUser, Role.READ_PUBLIC)) {
            return JsonResponse.getErrorInstance("User doesn't have permissions");
        }

        List<GameMap> gameMaps = gameMapRepository.findAll();

        if (dto.gameTag != null) {
            Game game = gameRepository.findByTag(dto.gameTag);
            if (game != null) {
                gameMaps = gameMapRepository.findByGame(game);
            }
        }

        List<Map<String, String>> data = new ArrayList<>();
        for (GameMap gameMap : gameMaps) {
            Map<String, String> mapData = new HashMap<>();
            mapData.put("tag", gameMap.getTag());
            mapData.put("name", gameMap.getName());
            mapData.put("gameTag", gameMap.getGame().getTag());
            data.add(mapData);
        }

        return JsonResponse.getInstance(data);
    }
}
