package com.adiev.posananta.core.persentation.utils

sealed interface Screen {
    @kotlinx.serialization.Serializable
    data object SpendingOverview: Screen

    @kotlinx.serialization.Serializable
    data class SpendingDetails(val spendingId: Int = -1): Screen

    @kotlinx.serialization.Serializable
    data object Balance: Screen
}