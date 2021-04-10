package com.yoti.hoovering.service;

import com.yoti.hoovering.domain.Coordination;
import com.yoti.hoovering.domain.Hoovering;
import com.yoti.hoovering.dto.HooveringResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

/**
 * {@inheritDoc}
 */
@Service
public class HooveringServiceImpl implements HooveringService {


    /**
     * {@inheritDoc}
     */
    @Override
    public HooveringResponse hoover(Hoovering hoovering) {
        Coordination current = hoovering.getBotCoordination();
        Coordination room = hoovering.getRoomCoordination();

        Integer cleanedPatch = getCleanedPatches(hoovering.getInstructions(),
                hoovering.getPatches(),
                current,
                room);

        ArrayList<Integer> finalCoord = new ArrayList<>();
        finalCoord.add(current.getX());
        finalCoord.add(current.getY());
        return new HooveringResponse().setCoords(finalCoord).setPatches(cleanedPatch);
    }

    /**
     * Gets number of cleaned patches
     *
     * @param instruction of moving bot
     * @param patches     coordination of dirty patches
     * @param current     current coordination of bot
     * @param room        coordination of room
     * @return number of cleaned patches
     */
    private Integer getCleanedPatches(String instruction,
                                      Set<Coordination> patches,
                                      Coordination current,
                                      Coordination room) {
        if (instruction == null) return 0;
        Integer cleanedPatch = 0;
        for (Character c : instruction.toCharArray()) {
            moveBot(current, room, c);
            if (patches.contains(current)) {
                patches.remove(current);
                cleanedPatch++;
            }
        }
        return cleanedPatch;
    }

    /**
     * Moves bot and changes current coordination of bot
     *
     * @param current coordination of bot
     * @param room    coordination of room
     * @param c       command of moving address
     */
    private void moveBot(Coordination current, Coordination room, Character c) {
        Integer y = current.getY();
        Integer x = current.getX();
        switch (c) {
            case 'N':
                if (y < room.getY())
                    current.setY(++y);
                break;
            case 'S':
                if (y > 0)
                    current.setY(--y);
                break;
            case 'E':
                if (x < room.getX())
                    current.setX(++x);
                break;
            case 'W':
                if (x > 0)
                    current.setX(--x);
                break;
            default:
                throw new IllegalStateException("Command " + c + " not valid");
        }
    }

}
