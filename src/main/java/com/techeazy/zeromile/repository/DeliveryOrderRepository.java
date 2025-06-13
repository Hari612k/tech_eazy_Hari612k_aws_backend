package com.techeazy.zeromile.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techeazy.zeromile.entity.DeliveryOrder;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
    
    List<DeliveryOrder> findByOrderDate(LocalDate date);

    @Query("SELECT d FROM DeliveryOrder d WHERE " +
           "(:vendorName IS NULL OR d.vendor.vendorName = :vendorName) AND " +
           "(:orderDate IS NULL OR d.orderDate = :orderDate)")
    Page<DeliveryOrder> filterOrders(
        @Param("vendorName") String vendorName,
        @Param("orderDate") LocalDate orderDate,
        Pageable pageable
    );
}
