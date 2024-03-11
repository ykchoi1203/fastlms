package com.zerobase.fastlms.loginLog.repository;

import com.zerobase.fastlms.loginLog.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

}
