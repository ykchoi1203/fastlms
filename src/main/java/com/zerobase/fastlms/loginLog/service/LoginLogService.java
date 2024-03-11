package com.zerobase.fastlms.loginLog.service;

import com.zerobase.fastlms.admin.model.LogParam;
import com.zerobase.fastlms.loginLog.dto.LoginLogDto;
import com.zerobase.fastlms.member.entity.Member;

import java.util.List;

public interface LoginLogService {
    /**
     * 로그인 로그 추가
     */
    boolean add(Member member, String ipAddress, String agent);

    /**
     * 로그인 로그 목록
     */
    List<LoginLogDto> list( LogParam logParam);
}
