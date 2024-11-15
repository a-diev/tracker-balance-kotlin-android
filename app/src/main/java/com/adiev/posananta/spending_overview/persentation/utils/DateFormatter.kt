package com.adiev.posananta.spending_overview.persentation.utils

import java.time.ZonedDateTime

fun ZonedDateTime.formatDate(): String {
    return "$dayOfMonth-$monthValue-$year"
}