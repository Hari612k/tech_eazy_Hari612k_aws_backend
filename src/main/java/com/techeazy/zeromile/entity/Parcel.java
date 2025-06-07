package com.techeazy.zeromile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Parcel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String customerName;
	private String deliveryAddress;
	private String contactNumber;
	private String parcelSize;
	private Double parcelWeight;
	private String trackingNumber;
	
	/*
	 * private String deliveryArea; private String deliveryStatus;
	 * 
	 * private String assignedDriver; private String assignedVehicle;
	 */
	
	public Parcel() {
		super();
	}
	
	
	
}
