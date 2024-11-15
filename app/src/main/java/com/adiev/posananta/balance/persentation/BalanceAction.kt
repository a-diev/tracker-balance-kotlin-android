package com.adiev.posananta.balance.persentation

sealed interface BalanceAction {
    data class OnBalanceChanged(val newBalance: Double) : BalanceAction

    data object OnBalanceSaved : BalanceAction
}