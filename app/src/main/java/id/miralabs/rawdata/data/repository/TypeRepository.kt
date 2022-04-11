package id.miralabs.rawdata.data.repository

import id.miralabs.rawdata.data.dto.Type
import kotlinx.coroutines.flow.Flow

interface TypeRepository {
    suspend fun loadTypeList(): Flow<List<Type>>
}