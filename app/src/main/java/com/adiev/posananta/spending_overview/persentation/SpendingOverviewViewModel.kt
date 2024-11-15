package com.adiev.posananta.spending_overview.persentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiev.posananta.core.domain.CoreRepository
import com.adiev.posananta.core.domain.LocalSpendingDataSource
import com.adiev.posananta.core.domain.Spending
import com.adiev.posananta.spending_overview.persentation.utils.randomColor
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class SpendingOverviewViewModel(
    private val spendingDataSource: LocalSpendingDataSource,
    private val coreRepository: CoreRepository,
) : ViewModel() {
    var state by mutableStateOf(SpendingOverviewState())
        private set

    fun onAction(action: SpendingOverviewAction) {
        when (action) {
            SpendingOverviewAction.LoadSpendingOverviewAndBalance -> {
                loadSpendingListAndBalance()
            }
            is SpendingOverviewAction.OnDateChange -> {
                val newDate = state.dateList[action.newDate]
                viewModelScope.launch {
                    state = state.copy(
                        pickedDate = newDate,
                        spendingList = getSpendingListByDate(newDate),
                    )
                }
            }
            is SpendingOverviewAction.OnDeleteSpending -> {
                viewModelScope.launch {
                    spendingDataSource.deleteSpending(action.spendingId)
                    state = state.copy(
                        spendingList = getSpendingListByDate(state.pickedDate),
                        dateList = spendingDataSource.getAllDates(),
                        balance = coreRepository.getBalance() - spendingDataSource.getSpendingBalance(),
                    )
                }
            }
        }
    }

    private fun loadSpendingListAndBalance() {
        viewModelScope.launch {
            val allDates = spendingDataSource.getAllDates()

            state = state.copy(
                spendingList = getSpendingListByDate(
                    allDates.lastOrNull() ?: ZonedDateTime.now()
                ),
                balance = coreRepository.getBalance() - spendingDataSource.getSpendingBalance(),
                pickedDate = allDates.lastOrNull() ?: ZonedDateTime.now(),
                dateList = allDates.reversed(),
            )
        }
    }

    private suspend fun getSpendingListByDate(date: ZonedDateTime): List<Spending> {
        return spendingDataSource.getSpendingByDate(date)
            .reversed()
            .map { it.copy(color = randomColor()) }
    }
}