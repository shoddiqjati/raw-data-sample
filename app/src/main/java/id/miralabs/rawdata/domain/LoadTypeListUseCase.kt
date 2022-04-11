package id.miralabs.rawdata.domain

import id.miralabs.rawdata.data.repository.TypeRepository
import javax.inject.Inject

class LoadTypeListUseCase @Inject constructor(private val repo: TypeRepository) {
    suspend operator fun invoke() = repo.loadTypeList()
}