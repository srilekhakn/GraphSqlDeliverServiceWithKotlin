package com.deliver.service.data

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "WarehouseDelivery")
class WarehouseDelivery(
    @Id
    val deliveryId: String,
    val product: String,
    val supplier: String,
    val quantity: Long,
    val expectedDate: String,
    val expectedWarehouse: String,
    var deliveryStatus: Boolean = true){
    constructor(): this("","","",0,"","",true){}
}