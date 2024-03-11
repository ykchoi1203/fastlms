package com.zerobase.fastlms.loginLog.mapper;

import com.zerobase.fastlms.admin.model.LogParam;
import com.zerobase.fastlms.loginLog.dto.LoginLogDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper {
    long selectListCount(LogParam logParam);
    List<LoginLogDto> selectList(LogParam logParam);
}
