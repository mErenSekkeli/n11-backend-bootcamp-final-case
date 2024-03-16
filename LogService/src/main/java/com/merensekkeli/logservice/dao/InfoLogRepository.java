package com.merensekkeli.logservice.dao;

import com.merensekkeli.logservice.entity.InfoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoLogRepository extends JpaRepository<InfoLog, Long> {

}
