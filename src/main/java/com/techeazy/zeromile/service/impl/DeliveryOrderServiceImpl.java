package com.techeazy.zeromile.service.impl;

import com.techeazy.zeromile.dto.DeliveryOrderDTO;
import com.techeazy.zeromile.entity.*;
import com.techeazy.zeromile.repository.*;
import com.techeazy.zeromile.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final VendorRepository vendorRepository;
    private final ParcelRepository parcelRepository;

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public DeliveryOrderDTO uploadOrderFile(String vendorName, LocalDate date, MultipartFile file) {
        try {
            Files.createDirectories(Paths.get(uploadDir));
            String filePath = uploadDir + "/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            Vendor vendor = vendorRepository.findByVendorName(vendorName)
                    .orElseThrow(() -> new RuntimeException("Vendor not found"));

            DeliveryOrder order = new DeliveryOrder();
            order.setVendor(vendor);
            order.setOrderDate(date);
            order.setFileLink(filePath);

            List<Parcel> parcels = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;
                Parcel p = new Parcel();
                p.setCustomerName(parts[0]);
                p.setDeliveryAddress(parts[1]);
                p.setContactNumber(parts[2]);
                p.setParcelSize(parts[3]);
                p.setParcelWeight(Double.parseDouble(parts[4]));
                p.setTrackingNumber(UUID.randomUUID().toString());
                p.setDeliveryOrder(order);
                parcels.add(p);
            }

            order.setParcels(parcels);
            deliveryOrderRepository.save(order);

            DeliveryOrderDTO dto = new DeliveryOrderDTO();
            dto.setOrderDate(order.getOrderDate());
            dto.setVendorName(vendor.getVendorName());
            dto.setTotalOrders(parcels.size());
            dto.setFileLink(filePath);
            return dto;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Page<DeliveryOrderDTO> getOrders(String vendorName, LocalDate date, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DeliveryOrder> pageData = deliveryOrderRepository.filterOrders(vendorName, date, pageable);
        return pageData.map(order -> {
            DeliveryOrderDTO dto = new DeliveryOrderDTO();
            dto.setOrderDate(order.getOrderDate());
            dto.setVendorName(order.getVendor().getVendorName());
            dto.setTotalOrders(order.getParcels().size());
            dto.setFileLink(order.getFileLink());
            return dto;
        });
    }
}
