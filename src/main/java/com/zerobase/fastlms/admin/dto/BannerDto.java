package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {
    Long id;
    String bannerName;
    String imageUrl;
    String linkUrl;

    String target;
    Long orderNo;
    boolean openYN;
    LocalDateTime createTime;

    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .imageUrl(banner.getImageUrl())
                .linkUrl(banner.getLinkUrl())
                .target(banner.getTarget())
                .openYN(banner.isOpenYN())
                .createTime(banner.getCreateTime())
                .build();
    }

}
