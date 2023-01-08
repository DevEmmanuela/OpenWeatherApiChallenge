package com.emmanuela.openweatherchallenge.service;

import com.emmanuela.openweatherchallenge.exceptions.EndpointException;

public interface OpenWeatherService {

    String apiCall (String city) throws EndpointException;
}
