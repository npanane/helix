package com.framework.helix.controller;

import com.framework.helix.entity.Client;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nuwan.n.bandara on 6/3/2014.
 */
@Controller
@RequestMapping("/uploadService")
public class UploadServiceController {

    @RequestMapping(value = "test/{code}", method = RequestMethod.GET)
    public @ResponseBody
    Client getUploadTest(@PathVariable String code) {
        Client c = new Client();
        c.setClientName("Nuwan B");
        return c;
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createEmployee(final @RequestBody String param) {
        return param;
    }

}