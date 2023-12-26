package org.mehmetgenc.secondhand.advertisement.model

import java.time.LocalDateTime

data class BaseEntity(
        val createdDate: LocalDateTime? = null,
        val updatedDate: LocalDateTime? = null
){

}
