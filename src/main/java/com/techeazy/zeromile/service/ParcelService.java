package com.techeazy.zeromile.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techeazy.zeromile.entity.Parcel;
import com.techeazy.zeromile.exception.InvalidParcelDataException;
import com.techeazy.zeromile.exception.ParcelNotFoundException;
import com.techeazy.zeromile.repository.ParcelRepository;

@Service
public class ParcelService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParcelService.class);
	
	@Autowired
	private ParcelRepository parcelRepository;
	
	public List<Parcel> getAllParcels(){
		logger.info("Fetching all parcels");
		return parcelRepository.findAll();
	}
	
	public Parcel getParcelByTrackingNumber(String trackingNumber) {
		logger.debug("Fetching parcel with tracking number: {}", trackingNumber);
		return parcelRepository.findByTrackingNumber(trackingNumber)
				.orElseThrow(() -> {
					logger.error("Parcel not found with tracking number: {}", trackingNumber);
					return new ParcelNotFoundException("Parcel not found with tracking number:" + trackingNumber);
				});
	}
	
	public Parcel createParcel(Parcel parcel) {
		if(parcel.getCustomerName() == null || parcel.getCustomerName().isEmpty()) {
			logger.warn("Attempt to create parcel with empty customer name");
			throw new InvalidParcelDataException("Customer name cannot be empty");
		}
		
		if(parcel.getDeliveryAddress() == null || parcel.getDeliveryAddress().isEmpty()) {
			logger.warn("Attempt to create parcel with empty delivery address");
			throw new InvalidParcelDataException("Delivery address cannot be empty");
		}
		
		parcel.setTrackingNumber(UUID.randomUUID().toString());
		Parcel savedParcel = parcelRepository.save(parcel);
		logger.info("Created new parcel with ID: {}", savedParcel.getId());
		return savedParcel;
	}
	
	public Parcel updateParcel(Long id, Parcel parcelDetails) {
		logger.debug("Updating parcel with ID: {}", id);
		return parcelRepository.findById(id)
				.map(parcel -> {
					if(parcelDetails.getCustomerName() != null) {
						parcel.setCustomerName(parcelDetails.getCustomerName());
					}
					if(parcelDetails.getDeliveryAddress() != null) {
						parcel.setDeliveryAddress(parcelDetails.getDeliveryAddress());
					}
					if(parcelDetails.getContactNumber() != null) {
						parcel.setContactNumber(parcelDetails.getContactNumber());
					}
					if(parcelDetails.getParcelSize() != null) {
						parcel.setParcelSize(parcelDetails.getParcelSize());
					}
					if(parcelDetails.getParcelWeight() > 0) {
						parcel.setParcelWeight(parcelDetails.getParcelWeight());
					}
					
					Parcel updatedParcel = parcelRepository.save(parcel);
					logger.info("Updated parcel with ID: {}", id);
					return updatedParcel;
				})
				.orElseThrow(() -> {
					logger.error("Parcel not found with ID: {}", id);
					return new ParcelNotFoundException("Parcel not found with ID:" + id);
				});
	}
	
	public void deleteParcel(Long id) {
		logger.debug("Attempting to delete parcel with ID: {}", id);
		if(!parcelRepository.existsById(id)) {
			logger.error("Parcel not found for deletion with ID: {}", id);
			throw new ParcelNotFoundException("Parcel not found for deletion with ID:" + id);
		}
		parcelRepository.deleteById(id);
		logger.info("Deleted parcel with ID: {}", id);
	}
	
	
}
