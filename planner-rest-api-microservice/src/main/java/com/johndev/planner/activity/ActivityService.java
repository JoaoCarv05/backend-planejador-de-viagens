package com.johndev.planner.activity;
import com.johndev.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {
    @Autowired
    ActivityRepository activitiesRepository;

    public Activity saveActivity(ActivityPayload activitiesPayload, Trip trip) {
        return activitiesRepository.save(new Activity(activitiesPayload, trip));
    }

    public List<Activity> findAllActivitiesByTripId(UUID TripId) {
        return activitiesRepository.findByTrip_id(TripId);
    }
}
