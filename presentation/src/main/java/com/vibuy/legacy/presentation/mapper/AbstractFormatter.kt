package com.vibuy.legacy.presentation.mapper

import java.text.NumberFormat
import java.util.*

/**
 * Created by F.K. on 2019-05-06.
 *
 */
abstract class AbstractFormatter {
    val turkish = Locale("tr", "TR")
    val usa = Locale("en", "US")
    val currency = Currency.getInstance(turkish)
    val formatter = NumberFormat.getInstance(usa)
    init {
        formatter.minimumFractionDigits = 2
    }
}