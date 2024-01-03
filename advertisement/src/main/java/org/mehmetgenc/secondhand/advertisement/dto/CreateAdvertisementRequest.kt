package org.mehmetgenc.secondhand.advertisement.dto

data class CreateAdvertisementRequest(
        val title: String,
        val description: String,
        val price: Double,
        val userId: Long,
        val hashtag: Set<String>
)
