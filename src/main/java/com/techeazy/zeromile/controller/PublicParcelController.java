package com.techeazy.zeromile.controller;

import com.techeazy.zeromile.entity.Parcel;
import com.techeazy.zeromile.repository.ParcelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/public/parcels")
@RequiredArgsConstructor
public class PublicParcelController {

    private final ParcelRepository parcelRepository;

    @GetMapping("/{trackingNumber}")
    public ResponseEntity<?> getParcelByTrackingNumber(@PathVariable String trackingNumber) {
        return parcelRepository.findByTrackingNumber(trackingNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
