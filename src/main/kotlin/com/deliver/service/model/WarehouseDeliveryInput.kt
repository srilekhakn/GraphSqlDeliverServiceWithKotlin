package com.deliver.service.model

class WarehouseDeliveryInput(
    val deliveryId : String,
    val product : String,
    val supplier : String,
    val quantity : Long,
    val expectedDate : String,
    val expectedWarehouse: String,
    val deliveryStatus: Boolean
) {
}