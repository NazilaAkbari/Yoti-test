package com.yoti.hoovering.controller;

import com.yoti.hoovering.dto.HooveringRequest;
import com.yoti.hoovering.dto.HooveringResponse;
import com.yoti.hoovering.mapper.HooveringMapper;
import com.yoti.hoovering.service.HooveringLogService;
import com.yoti.hoovering.service.HooveringService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Hoovering controller that accepts rest command for hoover
 */
@RestController
@RequestMapping("/hoover")
public class HooveringController {
    private final HooveringMapper hooveringMapper;
    private final HooveringService hooveringService;
    private final HooveringLogService hooveringLogService;

    public HooveringController(HooveringMapper hooveringMapper,
                               HooveringService hooveringService,
                               HooveringLogService hooveringLogService) {
        this.hooveringMapper = hooveringMapper;
        this.hooveringService = hooveringService;
        this.hooveringLogService = hooveringLogService;
    }

    /**
     * Hoover with bot moving in room
     * @param hooveringRequest to have initial state for hoover
     * @return hoovering response which is the final situation of bot and patches
     */
    @PostMapping
    public HooveringResponse hoover(@Valid
                                    @RequestBody HooveringRequest hooveringRequest) {
        HooveringResponse hoover = hooveringService.hoover(hooveringMapper.map(hooveringRequest));
        hooveringLogService.log(hooveringRequest.toString(), hoover.toString());
        return hoover;
    }

}
