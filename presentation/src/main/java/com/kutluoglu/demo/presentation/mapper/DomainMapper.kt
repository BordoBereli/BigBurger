package com.kutluoglu.demo.presentation.mapper

/**
 * Created by F.K. on 2019-05-02
 *
 */

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from view layer
 *
 * @param <V> the view model input type
 * @param <D> the domain model output type
 */
interface DomainMapper<in V, out D> {
    fun mapFromView(type: V) : D
}