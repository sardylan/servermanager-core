package org.thehellnet.onlinegaming.servermanager.core.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thehellnet.onlinegaming.servermanager.core.controller.api.v1.aspect.CheckRoles;
import org.thehellnet.onlinegaming.servermanager.core.controller.api.v1.aspect.CheckToken;
import org.thehellnet.onlinegaming.servermanager.core.model.constant.Role;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.JsonResponse;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.request.token.ServerListDTO;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUser;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Server;
import org.thehellnet.onlinegaming.servermanager.core.repository.ServerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/v1/public/server")
public class ServerController {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @RequestMapping(
            path = "/list",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CheckToken
    @CheckRoles(Role.READ_PRIVATE)
    @ResponseBody
    public JsonResponse list(AppUser appUser, @RequestBody ServerListDTO dto) {
        List<Server> servers = serverRepository.findAll();

        List<Map<String, Object>> data = new ArrayList<>();
        for (Server server : servers) {
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("name", server.getName());
            mapData.put("gameTag", server.getGame().getTag());
            mapData.put("address", server.getAddress());
            mapData.put("port", server.getPort());
            data.add(mapData);
        }

        return JsonResponse.getInstance(data);

    }
}
