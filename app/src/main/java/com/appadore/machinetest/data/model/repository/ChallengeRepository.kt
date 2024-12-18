package com.appadore.machinetest.data.model.repository

import com.acube.itms.data.local.room.AppDAO


import com.appadore.machinetest.data.local.DataStoreHelper
import com.appadore.machinetest.data.model.flags_challenge.Countries
import com.appadore.machinetest.data.model.flags_challenge.QuestionWithCountries
import com.appadore.machinetest.data.model.flags_challenge.Questions
import com.appadore.machinetest.utils.CountriesConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChallengeRepository @Inject constructor(

    private val _dataStoreHelper: DataStoreHelper,
    private val _appDao: AppDAO,
) : BaseRepository() {


    fun saveQuestionList(dataSet: Questions) {
        CoroutineScope(Dispatchers.IO).launch {
            //_appDao.deleteAllQuestions()
            _appDao.insertQuestions(dataSet)

        }
    }

    fun saveCountriesList(dataSet: List<Countries>) {
        CoroutineScope(Dispatchers.IO).launch {
            // _appDao.deleteAllQuestions()
            _appDao.insertCountries(dataSet)

        }
    }
    fun getallQuestions(): Flow<List<Questions>> {
        return _appDao.getallQuestions()
    }

    fun getCountriesForAnswer(questionID:Int): Flow<List<Countries>> {
        return _appDao.getCountriesForAnswer(questionID)
    }
    override fun getDataStoreHelper() = _dataStoreHelper
    override fun getRoomDao() = _appDao

}