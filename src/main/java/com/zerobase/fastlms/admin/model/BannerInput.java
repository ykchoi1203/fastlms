package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class BannerInput {
    long id;

    String bannerName;
    String imageUrl;
    String linkUrl;

    String target;
    Long orderNo;

    boolean openYN;

    String idList;
}
