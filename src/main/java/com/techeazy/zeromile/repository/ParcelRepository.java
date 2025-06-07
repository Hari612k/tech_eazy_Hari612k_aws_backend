package com.techeazy.zeromile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techeazy.zeromile.entity.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
	
	Optional<Parcel> findByTrackingNumber(String trackingNumber);

}
