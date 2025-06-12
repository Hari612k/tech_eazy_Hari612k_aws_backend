package com.techeazy.zeromile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techeazy.zeromile.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	Optional<Vendor> findByVendorName(String vendorName);
	
}
