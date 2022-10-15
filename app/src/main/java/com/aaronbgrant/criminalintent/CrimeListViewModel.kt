package com.aaronbgrant.criminalintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class CrimeListViewModel: ViewModel() {
    val crimes = mutableListOf<Crime>()

    init {
        viewModelScope.launch {
            crimes += loadCrimes()
        }
    }

    suspend fun loadCrimes(): List<Crime> {
        val result = mutableListOf<Crime>()
        delay(5000)
        for (i in 0 until 100) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title ="Crime #$i",
                date = DateFormat.getDateInstance(DateFormat.FULL).format(Date()),
                isSolved = i % 2 == 0,
                requiresPolice = i % 2 == 1
            )
            result += crime
        }

        return result
    }
}