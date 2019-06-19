package com.kutluoglu.demo.presentation.mapper

/**
 * Created by F.K. on 2019-05-02
 *
 */

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from domain layer
 *
 * @param <V> the view model output type
 * @param <D> the domain model input type
 */
interface ViewMapper<in D, out V> {
    fun mapToView(type: D) : V
}