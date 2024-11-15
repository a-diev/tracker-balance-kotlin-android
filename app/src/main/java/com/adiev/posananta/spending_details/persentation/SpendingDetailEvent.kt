package com.adiev.posananta.spending_details.persentation

sealed interface SpendingDetailEvent {
    data object SaveSuccess: SpendingDetailEvent
    data object SaveFailed: SpendingDetailEvent
}