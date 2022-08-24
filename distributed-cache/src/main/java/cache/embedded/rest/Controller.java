package cache.embedded.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import cache.embedded.CacheClient;

@RestController("thomberg")
@RequestMapping(path = "/cars")
public class Controller {

    private final CacheClient cacheClient;

    public Controller(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Car put(@RequestBody Car car) {
        return cacheClient.put(car.getNumber(), car);
    }

    @GetMapping(value = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car get(@PathVariable String number) {
        return cacheClient.get(number);
    }
}
