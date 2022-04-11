package id.miralabs.rawdata.data.repository

import android.app.Application
import com.google.gson.Gson
import id.miralabs.rawdata.data.dto.toType
import id.miralabs.rawdata.data.response.TypeListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TypeRepositoryImpl @Inject constructor(private val app: Application) : TypeRepository {

    override suspend fun loadTypeList() =
        flowOf(loadTypeListDataFromAssets().types.map { it.toType() }).flowOn(Dispatchers.IO)

    private fun loadTypeListDataFromAssets() = Gson().fromJson(
        app.assets.open(ASSET_TYPE_FILENAME).bufferedReader(Charsets.UTF_8).use { it.readText() }
            .toString(), TypeListResponse::class.java
    )

    companion object {
        private const val ASSET_TYPE_FILENAME = "types.json"
    }
}