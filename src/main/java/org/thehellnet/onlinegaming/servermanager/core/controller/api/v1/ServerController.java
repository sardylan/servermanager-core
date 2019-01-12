package org.thehellnet.onlinegaming.servermanager.core.controller.api.v1;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.JsonResponse;
import org.thehellnet.onlinegaming.servermanager.core.model.dto.request.token.ServerListDTO;

@Controller
@RequestMapping(path = "/api/v1/public/server")
public class ServerController {

//    @RequestMapping(
//            path = "/list",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ResponseBody
//    public JsonResponse list(@RequestBody ServerListDTO dto) {
//
//    }
}
