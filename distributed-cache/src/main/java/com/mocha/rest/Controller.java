package com.mocha.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.mocha.model.partnerProfile.PartnerProfileData;
import com.mocha.service.PartnerProfileCacheService;

@RestController("drivewealth")
@RequestMapping(path = "/partnerProfiles")
public class Controller {

	@Autowired
    private final PartnerProfileCacheService cacheService;

    public Controller(PartnerProfileCacheService cacheClient) {
        this.cacheService = cacheClient;
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public PartnerProfileData put(@RequestBody PartnerProfileData ppItem) {
        return cacheService.put(ppItem.getUserID(), ppItem);
    }

    @GetMapping(value = "/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PartnerProfileData get(@PathVariable String userID) {
        return cacheService.get(userID);
    }
}
