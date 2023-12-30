package org.mehmetgenc.secondhand.advertisement.dto

import java.time.LocalDateTime

data class AdvertisementDto(
        val id:String,
        val title:String,
        val description: String,
        val price: Double,
        val creationDate: LocalDateTime,
        val lastModifiedDate: LocalDateTime,
        val userId: Long,
        val hashtags: Set<String>
){

}
