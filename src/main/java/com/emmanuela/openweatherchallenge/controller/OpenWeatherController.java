package com.emmanuela.openweatherchallenge.controller;

import com.emmanuela.openweatherchallenge.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class OpenWeatherController {
    private final OpenWeatherService openWeatherService;
    @GetMapping("/{city}")
    public String getApiResponseByCity(@PathVariable String city){
        return openWeatherService.apiCall(city);
    }
}
