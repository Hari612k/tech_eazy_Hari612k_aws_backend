package com.techeazy.zeromile.service;

import com.techeazy.zeromile.dto.DeliveryOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface DeliveryOrderService {
    DeliveryOrderDTO uploadOrderFile(String vendorName, LocalDate date, MultipartFile file);
    Page<DeliveryOrderDTO> getOrders(String vendorName, LocalDate date, int page, int size);
}
