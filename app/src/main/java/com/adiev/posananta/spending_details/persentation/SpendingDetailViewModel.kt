package com.adiev.posananta.spending_details.persentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiev.posananta.core.domain.Spending
import com.adiev.posananta.spending_details.domain.UpsertSpendingUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class SpendingDetailViewModel(
    private val upsertSpendingUseCase: UpsertSpendingUseCase,
) : ViewModel() {
    var state by mutableStateOf(SpendingDetailState())
        private set

    private val _eventChannel = Channel<SpendingDetailEvent>()
    val event = _eventChannel.receiveAsFlow()

    fun onAction(action: SpendingDetailAction) {
        when(action) {
            is SpendingDetailAction.UpdateName -> {
                state = state.copy(
                    name = action.newName,
                )
            }
            is SpendingDetailAction.UpdatePrice -> {
                state = state.copy(
                    price = action.newPrice,
                )
            }
            is SpendingDetailAction.UpdateKilogram -> {
                state = state.copy(
                    kilogram = action.newKilogram,
                )
            }
            is SpendingDetailAction.UpdateQuantity -> {
                state = state.copy(
                    quantity = action.newQuantity,
                )
            }
            is SpendingDetailAction.SaveSpending -> {
                viewModelScope.launch {
                    if (saveSpending()) {
                        _eventChannel.send(SpendingDetailEvent.SaveSuccess)
                    } else {
                        _eventChannel.send(SpendingDetailEvent.SaveFailed)
                    }
                }
            }
        }
    }

    private suspend fun saveSpending(): Boolean {
        val spending = Spending(
            spendingId = null,
            name = state.name,
            price = state.price,
            kilograms = state.kilogram,
            quantity = state.quantity,
            dateTimeUtc = ZonedDateTime.now(),
        )

        return upsertSpendingUseCase(spending)
    }
}