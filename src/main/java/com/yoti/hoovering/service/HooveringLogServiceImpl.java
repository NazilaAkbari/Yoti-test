package com.yoti.hoovering.service;

import com.yoti.hoovering.domain.HooveringLog;
import com.yoti.hoovering.repository.HooveringLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class HooveringLogServiceImpl implements HooveringLogService {
    private final HooveringLogRepository hooveringLogRepository;

    public HooveringLogServiceImpl(HooveringLogRepository hooveringLogRepository) {
        this.hooveringLogRepository = hooveringLogRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(String request, String response) {
        hooveringLogRepository.save(new HooveringLog()
                .setRequest(request)
                .setResponse(response));
    }

}
