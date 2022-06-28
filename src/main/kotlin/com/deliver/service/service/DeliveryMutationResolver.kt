package com.deliver.service.service

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.deliver.service.data.WarehouseDelivery
import com.deliver.service.model.WarehouseDeliveryInput
import com.deliver.service.model.WarehouseDeliveryResponse
import com.deliver.service.repository.DeliveryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeliveryMutationResolver : GraphQLMutationResolver{
    @Autowired
    var deliveryRepository: DeliveryRepository? = null

    var deliveryService : DeliveryQueryResolver? = null

    fun persistDeliveryData(persistDelivery: WarehouseDeliveryInput): WarehouseDeliveryResponse {
        val saveDeliveryData = WarehouseDelivery(persistDelivery.deliveryId,persistDelivery.product,persistDelivery.supplier,persistDelivery.quantity,persistDelivery.expectedDate,persistDelivery.expectedWarehouse,persistDelivery.deliveryStatus)
        val persistedDelivery: WarehouseDelivery? = deliveryRepository?.save(saveDeliveryData)
        return mapDeliveryToDeliveryResponse(persistedDelivery)
    }

    private fun mapDeliveryToDeliveryResponse (delivery : WarehouseDelivery?) : WarehouseDeliveryResponse {
        var DeliveryResponse = WarehouseDeliveryResponse(delivery?.deliveryId,
            delivery?.product ,delivery?.supplier,delivery?.quantity,delivery?.expectedDate,delivery?.expectedWarehouse,delivery?.deliveryStatus)

        return DeliveryResponse;
    }

    fun persistDeliveriesData(persistDeliveries: List<WarehouseDeliveryInput>): List<WarehouseDeliveryResponse> {
        val warehouseDeliveryResponseList: MutableList<WarehouseDeliveryResponse> = mutableListOf()
        for (persistDelivery in persistDeliveries) {
            val saveDeliveryData = WarehouseDelivery(
                persistDelivery.deliveryId,
                persistDelivery.product,
                persistDelivery.supplier,
                persistDelivery.quantity,
                persistDelivery.expectedDate,
                persistDelivery.expectedWarehouse,
                persistDelivery.deliveryStatus
            )
            val persistedDelivery: WarehouseDelivery? = deliveryRepository?.save(saveDeliveryData)
            val deliveryResponse: WarehouseDeliveryResponse = mapDeliveryToDeliveryResponse(persistedDelivery)
            warehouseDeliveryResponseList.add(deliveryResponse)
        }
        return warehouseDeliveryResponseList;
    }

    fun updateDeliveryStatus(deliveryStatus: Boolean,deliveryId: String): WarehouseDeliveryResponse {
        val deliveryData : WarehouseDelivery? = deliveryRepository?.findById(deliveryId)?.get()
        val persistedDelivery: WarehouseDelivery? = deliveryRepository?.save(mapWarehouseDeliver(deliveryData,deliveryStatus))
        return mapDeliveryToDeliveryResponse(persistedDelivery)
    }

    private fun mapWarehouseDeliver(delivery: WarehouseDelivery?, deliveryStatus: Boolean): WarehouseDelivery {
        if (delivery != null) {
            return WarehouseDelivery(
                delivery.deliveryId,
                delivery.product,
                delivery.supplier,
                delivery.quantity,
                delivery.expectedDate,
                delivery.expectedWarehouse,
                deliveryStatus
            )
        };
        return WarehouseDelivery()
    }
}