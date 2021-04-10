package com.yoti.hoovering.service;

/**
 * Responsible for handling log of request and response in hoovering
 */
public interface HooveringLogService {

    /**
     * Saves every request and response to repository if request is valid
     *
     * @param request  hoovering request
     * @param response final response
     */
    void log(String request, String response);
}
