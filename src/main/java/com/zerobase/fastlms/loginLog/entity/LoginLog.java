package com.zerobase.fastlms.loginLog.entity;

import com.zerobase.fastlms.loginLog.dto.LoginLogDto;
import com.zerobase.fastlms.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Member userId;

    private LocalDateTime loginDate;
    private String loginIp;
    private String loginUsrAgent;

    public LoginLogDto of() {
        return LoginLogDto.builder()
                .id(this.id)
                .loginIp(this.loginIp)
                .loginUsrAgent(this.loginUsrAgent)
                .loginDate(this.loginDate)
                .build();
    }

}
