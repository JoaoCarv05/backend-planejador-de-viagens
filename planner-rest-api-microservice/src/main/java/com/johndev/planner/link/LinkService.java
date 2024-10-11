package com.johndev.planner.link;

import com.johndev.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {
    @Autowired
    linkRepository linkRepository;

    public LinkResponse registerLink(LinkPayload linkPayload, Trip trip) {
        Link link = new Link(linkPayload, trip);
        this.linkRepository.save(link);
        return new LinkResponse(link.getId());
    }

    public List<LinkData> findAllTripLinks(UUID id) {
        return linkRepository.findByTrip_id(id).stream().map(link -> new LinkData(link.getTitle(), link.getUrl())).toList();
    }

}
