package com.techeazy.zeromile.service;

import com.techeazy.zeromile.entity.Parcel;
import com.techeazy.zeromile.exception.InvalidParcelDataException;
import com.techeazy.zeromile.exception.ParcelNotFoundException;
import com.techeazy.zeromile.repository.ParcelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParcelService {

    private static final Logger logger = LoggerFactory.getLogger(ParcelService.class);

    private final ParcelRepository parcelRepository;

    public List<Parcel> getAllParcels() {
        logger.info("Retrieving all parcels from database");
        return parcelRepository.findAll();
    }

    public Parcel getParcelByTrackingNumber(String trackingNumber) {
        logger.debug("Looking up parcel with tracking number: {}", trackingNumber);
        return parcelRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> {
                    logger.error("No parcel found for tracking number: {}", trackingNumber);
                    return new ParcelNotFoundException("No parcel found with tracking number: " + trackingNumber);
                });
    }

    @Transactional
    public Parcel createParcel(Parcel parcel) {
        logger.info("Creating a new parcel");

        if (parcel.getCustomerName() == null || parcel.getCustomerName().trim().isEmpty()) {
            logger.warn("Invalid customer name in createParcel");
            throw new InvalidParcelDataException("Customer name is required.");
        }

        if (parcel.getDeliveryAddress() == null || parcel.getDeliveryAddress().trim().isEmpty()) {
            logger.warn("Invalid delivery address in createParcel");
            throw new InvalidParcelDataException("Delivery address is required.");
        }

        parcel.setTrackingNumber(UUID.randomUUID().toString());
        Parcel savedParcel = parcelRepository.save(parcel);

        logger.info("Parcel created with ID: {} and tracking number: {}", savedParcel.getId(), savedParcel.getTrackingNumber());
        return savedParcel;
    }

    public List<Parcel> createParcels(List<Parcel> parcels) {
    	logger.info("Creating an array of new parcels");
        return parcelRepository.saveAll(parcels);
    }

    
    @Transactional
    public Parcel updateParcel(Long id, Parcel parcelDetails) {
        logger.debug("Updating parcel with ID: {}", id);

        return parcelRepository.findById(id).map(parcel -> {
            if (parcelDetails.getCustomerName() != null) parcel.setCustomerName(parcelDetails.getCustomerName());
            if (parcelDetails.getDeliveryAddress() != null) parcel.setDeliveryAddress(parcelDetails.getDeliveryAddress());
            if (parcelDetails.getContactNumber() != null) parcel.setContactNumber(parcelDetails.getContactNumber());
            if (parcelDetails.getParcelSize() != null) parcel.setParcelSize(parcelDetails.getParcelSize());
            if (parcelDetails.getParcelWeight() > 0) parcel.setParcelWeight(parcelDetails.getParcelWeight());

            Parcel updated = parcelRepository.save(parcel);
            logger.info("Parcel updated successfully with ID: {}", id);
            return updated;
        }).orElseThrow(() -> {
            logger.error("Update failed — parcel not found with ID: {}", id);
            return new ParcelNotFoundException("Parcel not found with ID: " + id);
        });
    }

    @Transactional
    public void deleteParcel(Long id) {
        logger.debug("Attempting to delete parcel with ID: {}", id);

        if (!parcelRepository.existsById(id)) {
            logger.error("Parcel not found for deletion with ID: {}", id);
            throw new ParcelNotFoundException("Parcel not found with ID: " + id);
        }

        parcelRepository.deleteById(id);
        logger.info("Parcel deleted successfully with ID: {}", id);
    }
}
