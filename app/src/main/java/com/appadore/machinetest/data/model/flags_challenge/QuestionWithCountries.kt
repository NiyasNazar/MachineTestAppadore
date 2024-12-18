package com.appadore.machinetest.data.model.flags_challenge

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithCountries(
    @Embedded val question: Questions,
    @Relation(
        parentColumn = "answerId",
        entityColumn = "questionId"
    )
    val countries: List<Countries>
)