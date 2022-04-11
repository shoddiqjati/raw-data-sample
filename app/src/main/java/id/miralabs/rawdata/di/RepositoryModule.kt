package id.miralabs.rawdata.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.miralabs.rawdata.data.repository.TypeRepository
import id.miralabs.rawdata.data.repository.TypeRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTypeRepository(repository: TypeRepositoryImpl): TypeRepository
}