package com.vibuy.legacy.data.source

import com.vibuy.legacy.data.repository.DataStore
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */
open class DataStoreFactory @Inject constructor (
    private val cacheDataStore: CacheDataStore,
    private val remoteDataStore: RemoteDataStore
) {

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retreiveCacheDataStore() : DataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retreiveRemoteDataStore() : DataStore {
        return remoteDataStore
    }
}