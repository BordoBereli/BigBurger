package com.kutluoglu.demo.cache.mapper

/**
 * Created by F.K. on 2019-05-02
 *
 */

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving and putting of models from data layer
 *
 * @param <M> the model input type
 * @param <C> the cached model return type
 */

interface EntityMapper<M, C> {

    fun mapToCached(type: M): C

    fun mapFromCached(type: C): M
}