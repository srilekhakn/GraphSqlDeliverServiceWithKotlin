package com.deliver.service.repository

import com.deliver.service.data.WarehouseDelivery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DeliveryRepository : JpaRepository<WarehouseDelivery?, String?> {

    fun findByDeliveryStatus(deliveryStatus : Boolean) : List<WarehouseDelivery>
}