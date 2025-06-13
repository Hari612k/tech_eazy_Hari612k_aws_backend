package com.techeazy.zeromile.controller;

import com.techeazy.zeromile.entity.Parcel;
import com.techeazy.zeromile.repository.ParcelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/summary")
@RequiredArgsConstructor
public class ParcelSummaryController {

    private final ParcelRepository parcelRepository;

    @GetMapping("/today")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTodayParcelSummary() {
        LocalDate today = LocalDate.now();

        List<Parcel> todayParcels = parcelRepository.findAll().stream()
                .filter(p -> p.getDeliveryOrder() != null &&
                        today.equals(p.getDeliveryOrder().getOrderDate()))
                .collect(Collectors.toList());

        Map<String, Long> groupedByAddress = todayParcels.stream()
                .collect(Collectors.groupingBy(Parcel::getDeliveryAddress, Collectors.counting()));

        return ResponseEntity.ok(groupedByAddress);
    }
}
