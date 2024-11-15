package com.adiev.posananta.core.data

import com.adiev.posananta.core.data.local.SpendingDao
import com.adiev.posananta.core.domain.LocalSpendingDataSource
import com.adiev.posananta.core.domain.Spending
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

class RoomSpendingDataSource(private val dao: SpendingDao): LocalSpendingDataSource {
    override suspend fun getAllSpending(): List<Spending> {
        return dao.getAllSpending().map { it.toSpending() }
    }

    override suspend fun getSpendingByDate(dateTimeUtc: ZonedDateTime): List<Spending> {
        return dao.getAllSpending().map { it.toSpending() }
            .filter {
                it.dateTimeUtc.dayOfMonth == dateTimeUtc.dayOfMonth
                        && it.dateTimeUtc.month == dateTimeUtc.month
                        && it.dateTimeUtc.year == dateTimeUtc.year
            }
    }

    override suspend fun getAllDates(): List<ZonedDateTime> {
        val uniqueDates = mutableSetOf<LocalDate>()
        return dao.getAllDates()
            .map {
                Instant.parse(it).atZone(ZoneId.of("UTC"))
            }
            .filter {
                uniqueDates.add(it.toLocalDate())
            }
    }

    override suspend fun getSpending(id: Int): Spending {
        return dao.getSpending(id).toSpending()
    }

    override suspend fun upsertSpending(spending: Spending) {
        return dao.upsertSpending(spending.toNewSpendingEntity())
    }

    override suspend fun getSpendingBalance(): Double {
        return dao.getSpendBalance() ?: 0.0
    }

    override suspend fun deleteSpending(id: Int) {
        return dao.deleteSpending(id)
    }
}