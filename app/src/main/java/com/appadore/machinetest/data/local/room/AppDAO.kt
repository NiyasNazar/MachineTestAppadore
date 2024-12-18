package com.acube.itms.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.appadore.machinetest.data.model.flags_challenge.Countries
import com.appadore.machinetest.data.model.flags_challenge.Questions
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDAO {


    @Insert(onConflict = REPLACE)
    suspend fun insertQuestions(entityList: Questions)
    @Insert(onConflict = REPLACE)
    suspend fun insertCountries(countries:List<Countries>)

  /*  @Query("DELETE FROM QUESTIONS_LIST_TABLE")
    suspend fun getallQuestions()*/
    @Query("SELECT * FROM QUESTIONS_LIST_TABLE ")
    fun getallQuestions(): Flow<List<Questions>>
    @Query("SELECT * FROM COUNTRIES_LIST_TABLE WHERE questionId = :answerId")
    fun getCountriesForAnswer(answerId: Int): Flow<List<Countries>>
}