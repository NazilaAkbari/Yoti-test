package com.yoti.hoovering.mapper;

import com.yoti.hoovering.domain.Coordination;
import com.yoti.hoovering.domain.Hoovering;
import com.yoti.hoovering.dto.HooveringRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * This class is responsible for mapping hoovering request to hoovering object
 */
@Component
public class HooveringMapper {

    /**
     * maps request to hoover
     * @param hooveringRequest initial state of hoovering
     * @return hoovering
     * @throws IllegalArgumentException if bot coordination is not in the room
     */
    public Hoovering map(HooveringRequest hooveringRequest) {
        if (hooveringRequest.getCoords().get(0) > hooveringRequest.getRoomSize().get(0) ||
                hooveringRequest.getCoords().get(1) > hooveringRequest.getRoomSize().get(1))
            throw new IllegalArgumentException("Bot Coordination not valid");
        Hoovering hoovering = new Hoovering();
        hoovering.setRoomCoordination(new Coordination(
                        hooveringRequest.getRoomSize().get(0), hooveringRequest.getRoomSize().get(1)
                )
        ).setBotCoordination(new Coordination(
                        hooveringRequest.getCoords().get(0), hooveringRequest.getCoords().get(1)
                )
        ).setPatches(hooveringRequest.getPatches() == null ? new HashSet<>() :
                hooveringRequest.getPatches().stream()
                        .map(p -> new Coordination(p.get(0), p.get(1)))
                        .collect(Collectors.toSet())
        ).setInstructions(hooveringRequest.getInstructions());
        return hoovering;
    }
}
