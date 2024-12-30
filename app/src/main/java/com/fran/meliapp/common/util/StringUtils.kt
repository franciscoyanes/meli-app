package com.fran.meliapp.common.util

import java.util.Locale

object StringUtils {

    fun floatToPriceString(price: Float): String {
        return buildString {
            append("$ ")
            append(String.format(Locale.GERMANY, "%,.0f", price))
        }
    }
}