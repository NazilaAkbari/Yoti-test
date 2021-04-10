package com.yoti.hoovering.repository;

import com.yoti.hoovering.domain.HooveringLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooveringLogRepository extends JpaRepository<HooveringLog, Long> {
}
