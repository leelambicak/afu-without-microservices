package com.imaginnovate.afu.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class GeolocationService {

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    public String getAddressByLatLng(double latitude, double longitude) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(googleMapsApiKey)
                .build();
        GeocodingResult[] results;
        try {
            results = GeocodingApi.reverseGeocode(context, new LatLng(latitude, longitude))
                    .await();
            if (results.length > 0) {
                return results[0].formattedAddress;
            }
        } catch (ApiException | InterruptedException | IOException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
        return null;
    }
}