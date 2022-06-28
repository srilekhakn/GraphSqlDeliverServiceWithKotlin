package com.deliver.service.model

class WarehouseDeliveryResponse(
    val deliveryId: String?,
    val product: String?,
    val supplier: String?,
    val quantity: Long?,
    val expectedDate: String?,
    val expectedWarehouse: String?,
    var deliveryStatus: Boolean?) {
}