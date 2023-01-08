package com.emmanuela.openweatherchallenge.controller;

import com.emmanuela.openweatherchallenge.exceptions.EndpointException;
import com.emmanuela.openweatherchallenge.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OpenWeatherController {
    private final OpenWeatherService openWeatherService;
    @GetMapping("/city/{city}")
    public String getApiResponseByCity(@PathVariable String city) throws EndpointException {
        return openWeatherService.apiCall(city);
    }
}
