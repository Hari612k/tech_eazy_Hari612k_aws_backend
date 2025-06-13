package com.techeazy.zeromile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techeazy.zeromile.dto.ApiResponse;
import com.techeazy.zeromile.entity.Parcel;
import com.techeazy.zeromile.service.ParcelService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parcels")
public class ParcelController {
	
	@Autowired
	private ParcelService parcelService;
	
	@GetMapping
	public ResponseEntity<List<Parcel>> getAllParcels(){
		return ResponseEntity.ok(parcelService.getAllParcels());
	}
	
	@GetMapping("/{trackingNumber}")
	public ResponseEntity<ApiResponse<Parcel>> getParcelByTrackingNumber(
			@PathVariable String trackingNumber, HttpServletRequest request){
		Parcel parcel = parcelService.getParcelByTrackingNumber(trackingNumber);
		return ResponseEntity.ok(new ApiResponse<>(
				HttpStatus.OK.value(), 
				"Parcel retrieved successfully", 
				parcel, 
				request.getRequestURI()
		));
	}
	
	@PostMapping
	public ResponseEntity<Parcel> createParcel(@RequestBody Parcel parcel){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(parcelService.createParcel(parcel));
	}
	
	@PostMapping("/bulk")
	public ResponseEntity<List<Parcel>> createParcels(@RequestBody List<Parcel> parcels) {
	    List<Parcel> savedParcels = parcelService.createParcels(parcels);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedParcels);
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Parcel> updateParcel(@PathVariable Long id, @RequestBody Parcel parcel){
		return ResponseEntity.ok(parcelService.updateParcel(id, parcel));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteParcel(@PathVariable Long id){
		parcelService.deleteParcel(id);
		return ResponseEntity.noContent().build();
	}
	
}
