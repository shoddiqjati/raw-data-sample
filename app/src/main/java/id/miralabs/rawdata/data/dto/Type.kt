package id.miralabs.rawdata.data.dto

import id.miralabs.rawdata.data.response.TypeResponse

data class Type(val id: Int, val label: String)

fun TypeResponse.toType() = Type(id, label)