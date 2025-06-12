package com.techeazy.zeromile.dto;

import lombok.*;
import java.time.LocalDate;

@Data
public class DeliveryOrderDTO {
    private LocalDate orderDate;
    private String vendorName;
    private int totalOrders;
    private String fileLink;
}

