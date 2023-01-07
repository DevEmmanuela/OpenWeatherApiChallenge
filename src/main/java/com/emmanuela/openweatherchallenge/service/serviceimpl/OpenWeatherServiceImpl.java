package com.emmanuela.openweatherchallenge.service.serviceimpl;

import com.emmanuela.openweatherchallenge.httpcall.OpenWeatherApiCall;
import com.emmanuela.openweatherchallenge.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherServiceImpl implements OpenWeatherService {
    private final OpenWeatherApiCall openWeatherApiCall;
    private String apiCall;
    @Override
    public String apiCall(String city) {

        try{
            apiCall = openWeatherApiCall.openWeatherApi(city);
        }
        catch (Exception ex){}
        return apiCall;
    }
}
