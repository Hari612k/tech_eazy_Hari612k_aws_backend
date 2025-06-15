package com.techeazy.zeromile.dto;

import com.techeazy.zeromile.enums.SubscriptionType;

import lombok.*;

@Data
public class VendorDTO {
    private String vendorName;
    private SubscriptionType subscriptionType;
}

