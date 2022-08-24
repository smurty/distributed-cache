package com.smurty.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.smurty.model.partnerProfile.PartnerProfileItem;
import com.smurty.service.CacheService;

@RestController
@RequestMapping(path = "/partnerprofile")
public class Controller {

    private final CacheService cacheService;

    public Controller(CacheService cacheClient) {
        this.cacheService = cacheClient;
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public PartnerProfileItem put(@RequestBody PartnerProfileItem ppItem) {
        return cacheService.put(ppItem.getUserID(), ppItem);
    }

    @GetMapping(value = "/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PartnerProfileItem get(@PathVariable String userID) {
        return cacheService.get(userID);
    }
}
