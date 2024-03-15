package com.merensekkeli.customerreviewservice.service;


import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    public List<CompanyClientDTO> recommendRestaurants(List<CompanyClientDTO> allRestaurants, Customer customer, Map<String, Double> companyRatings) {


        return allRestaurants.stream()
                .filter(company -> distance(customer.getLatitude(), customer.getLongitude(), company.latitude(), company.longitude()) <= 10.0) // 10 km limit
                .sorted((c1, c2) -> {
                    double score1 = calculateScore(customer, companyRatings.get(c1.id()), c1);
                    double score2 = calculateScore(customer, companyRatings.get(c2.id()), c2);
                    return Double.compare(score2, score1); // higher score first
                })
                .limit(3)
                .collect(Collectors.toList());
    }

    private double distance(double lat1, double long1, double lat2, double long2) {
        // Haversine formula to calculate the distance between two points on the Earth
        double earthRadius = 6371; // kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(long2 - long1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    private double calculateScore(Customer customer, double companyRating, CompanyClientDTO company){
        double distance = distance(customer.getLatitude(), customer.getLongitude(), company.latitude(), company.longitude());
        double distanceScore = (10.0 - distance) / 10.0;
        return companyRating * 0.7 + distanceScore * 0.3;
    }
}