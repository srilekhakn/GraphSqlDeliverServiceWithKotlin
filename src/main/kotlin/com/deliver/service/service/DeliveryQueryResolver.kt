package com.deliver.service.service

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.deliver.service.data.WarehouseDelivery
import com.deliver.service.model.WarehouseDeliveryResponse
import com.deliver.service.repository.DeliveryRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeliveryQueryResolver : GraphQLQueryResolver {
    @Autowired
    var deliveryRepository: DeliveryRepository? = null

    fun application() : String {
        return "Warehouse Delivery Status tracker"
    }

    fun getWarehouseDelivery(deliveryId: String): WarehouseDeliveryResponse {
        var delivery = deliveryRepository?.findById(deliveryId)?.get()
        return mapDeliveryResponse(delivery)
    }

    fun getAllWarehouseDeliveries(): List<WarehouseDeliveryResponse>? {
        return getWarehouseDeliveryResponse(deliveryRepository?.findAll() as List<WarehouseDelivery>)
    }

    fun allWarehouseDeliveriesBydDeliveryStatus(deliveryStatus: Boolean): List<WarehouseDeliveryResponse>? {
        return getWarehouseDeliveryResponse(deliveryRepository?.findByDeliveryStatus(deliveryStatus) as List<WarehouseDelivery>)
    }

    private fun getWarehouseDeliveryResponse(deliveryList: List<WarehouseDelivery>): List<WarehouseDeliveryResponse>? {
        val deliveryResponseList: MutableList<WarehouseDeliveryResponse> = ArrayList()
        deliveryList.stream().forEach { delivery: WarehouseDelivery ->
            deliveryResponseList.add(mapDeliveryResponse(delivery))
        }
        return deliveryResponseList
    }

    private fun mapDeliveryResponse(delivery: WarehouseDelivery?): WarehouseDeliveryResponse {
        return WarehouseDeliveryResponse(
            delivery?.deliveryId,
            delivery?.product,
            delivery?.supplier,
            delivery?.quantity,
            delivery?.expectedDate,
            delivery?.expectedWarehouse,
            delivery?.deliveryStatus
        );
    }
}