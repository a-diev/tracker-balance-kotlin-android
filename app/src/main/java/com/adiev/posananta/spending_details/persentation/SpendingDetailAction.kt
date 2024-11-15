package com.adiev.posananta.spending_details.persentation

interface SpendingDetailAction {
    data class UpdateName(val newName: String): SpendingDetailAction
    data class UpdatePrice(val newPrice: Double): SpendingDetailAction
    data class UpdateKilogram(val newKilogram: Double): SpendingDetailAction
    data class UpdateQuantity(val newQuantity: Double): SpendingDetailAction
    data object SaveSpending: SpendingDetailAction
}