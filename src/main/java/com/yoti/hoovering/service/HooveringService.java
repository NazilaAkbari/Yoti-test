package com.yoti.hoovering.service;

import com.yoti.hoovering.domain.Hoovering;
import com.yoti.hoovering.dto.HooveringResponse;

/**
 * Responsible for hoovering and moving bot in room
 */
public interface HooveringService {

    /**
     * Moves and hoover bot in room and find final state of cleaned patches and bot
     *
     * @param hoovering initial state of hoovering
     * @return hoovering response which is final state of bot and number of cleaned patches
     */
    HooveringResponse hoover(Hoovering hoovering);
}
