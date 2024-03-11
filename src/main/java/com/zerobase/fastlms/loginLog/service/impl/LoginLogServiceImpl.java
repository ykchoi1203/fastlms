package com.zerobase.fastlms.loginLog.service.impl;

import com.zerobase.fastlms.admin.model.LogParam;
import com.zerobase.fastlms.loginLog.dto.LoginLogDto;
import com.zerobase.fastlms.loginLog.entity.LoginLog;
import com.zerobase.fastlms.loginLog.mapper.LoginLogMapper;
import com.zerobase.fastlms.loginLog.repository.LoginLogRepository;
import com.zerobase.fastlms.loginLog.service.LoginLogService;
import com.zerobase.fastlms.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginLogServiceImpl implements LoginLogService {
    private final LoginLogRepository loginLogRepository;
    private final LoginLogMapper loginLogMapper;

    @Override
    public boolean add(Member member, String ipAddress, String agent) {

        LoginLog loginLog = LoginLog.builder()
                                    .userId(member)
                                    .loginIp(ipAddress)
                                    .loginDate(LocalDateTime.now().withNano(0))
                                    .loginUsrAgent(agent)
                                    .build();

        loginLogRepository.save(loginLog);
        return true;
    }

    @Override
    public List<LoginLogDto> list(LogParam logParam) {
        long totalCount = loginLogMapper.selectListCount(logParam);

        List<LoginLogDto> loginList = loginLogMapper.selectList(logParam);

        if (!CollectionUtils.isEmpty(loginList)) {
            int i = 0;
            for(LoginLogDto loginLog : loginList) {
                loginLog.setTotalCount(totalCount);
                loginLog.setSeq(totalCount- logParam.getPageStart()-i);
                i++;
            }
        }

        return loginList;
    }
}
