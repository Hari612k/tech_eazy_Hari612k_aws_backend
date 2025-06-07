package com.techeazy.zeromile.dto;

import com.techeazy.zeromile.entity.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelDTO {
	
	private String customerName;
	private String deliveryAddress;
	private String trackingNumber;
	
}
