package id.miralabs.rawdata.data.response

import kotlinx.serialization.Serializable

@Serializable
data class TypeResponse(val id: Int, val label: String)

@Serializable
data class TypeListResponse(val types: List<TypeResponse>)