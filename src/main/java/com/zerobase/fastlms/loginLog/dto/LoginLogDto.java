package com.zerobase.fastlms.loginLog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginLogDto {
    Long id;
    LocalDateTime loginDate;
    String loginIp;
    String loginUsrAgent;
    long totalCount;
    long seq;
}
