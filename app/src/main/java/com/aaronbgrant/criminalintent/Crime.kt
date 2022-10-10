package com.aaronbgrant.criminalintent

import java.text.DateFormat
import java.util.*

data class Crime(
    val id: UUID,
    val title: String,
    val date: String,
    val isSolved: Boolean,
    val requiresPolice: Boolean
    )