package com.techeazy.zeromile.controller;

import com.techeazy.zeromile.dto.DeliveryOrderDTO;
import com.techeazy.zeromile.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @PostMapping("/upload")
    public ResponseEntity<DeliveryOrderDTO> uploadOrder(
            @RequestParam String vendorName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate,
            @RequestParam MultipartFile file
    ) {
        return ResponseEntity.ok(deliveryOrderService.uploadOrderFile(vendorName, orderDate, file));
    }

    @GetMapping
    public ResponseEntity<?> getOrders(
            @RequestParam(required = false) String vendorName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(deliveryOrderService.getOrders(vendorName, date, page, size));
    }
}
