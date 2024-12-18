package com.appadore.machinetest.ui.main.challenge_setup


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appadore.machinetest.data.model.flags_challenge.Countries
import com.appadore.machinetest.data.model.flags_challenge.FlagsChallengeResp
import com.appadore.machinetest.data.model.flags_challenge.Questions
import com.appadore.machinetest.data.model.repository.ChallengeRepository
import com.appadore.machinetest.ui.base.BaseViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ChallengeSetupViewModel @Inject constructor(private val _repository: ChallengeRepository) :
    BaseViewModel() {
    val successLiveData: LiveData<Boolean>
        get() = _successLiveData
    private val _successLiveData = MutableLiveData<Boolean>()
    val jsonString = "{\"questions\":[{\"answer_id\":160,\"countries\":[{\"country_name\":\"BosniaandHerzegovina\",\"id\":29},{\"country_name\":\"Mauritania\",\"id\":142},{\"country_name\":\"Chile\",\"id\":45},{\"country_name\":\"NewZealand\",\"id\":160}],\"country_code\":\"NZ\"},{\"answer_id\":13,\"countries\":[{\"country_name\":\"Aruba\",\"id\":13},{\"country_name\":\"Serbia\",\"id\":184},{\"country_name\":\"Montenegro\",\"id\":150},{\"country_name\":\"Moldova\",\"id\":147}],\"country_code\":\"AW\"},{\"answer_id\":66,\"countries\":[{\"country_name\":\"Kenya\",\"id\":117},{\"country_name\":\"Montenegro\",\"id\":150},{\"country_name\":\"Ecuador\",\"id\":66},{\"country_name\":\"Bhutan\",\"id\":26}],\"country_code\":\"EC\"},{\"answer_id\":174,\"countries\":[{\"country_name\":\"Niue\",\"id\":164},{\"country_name\":\"Paraguay\",\"id\":174},{\"country_name\":\"Tuvalu\",\"id\":232},{\"country_name\":\"Indonesia\",\"id\":105}],\"country_code\":\"PY\"},{\"answer_id\":122,\"countries\":[{\"country_name\":\"Kyrgyzstan\",\"id\":122},{\"country_name\":\"Zimbabwe\",\"id\":250},{\"country_name\":\"SaintLucia\",\"id\":190},{\"country_name\":\"Ireland\",\"id\":108}],\"country_code\":\"KG\"},{\"answer_id\":192,\"countries\":[{\"country_name\":\"SaintPierreandMiquelon\",\"id\":192},{\"country_name\":\"Namibia\",\"id\":155},{\"country_name\":\"Greece\",\"id\":87},{\"country_name\":\"Anguilla\",\"id\":8}],\"country_code\":\"PM\"},{\"answer_id\":113,\"countries\":[{\"country_name\":\"Belarus\",\"id\":21},{\"country_name\":\"FalklandIslands\",\"id\":73},{\"country_name\":\"Japan\",\"id\":113},{\"country_name\":\"Iraq\",\"id\":107}],\"country_code\":\"JP\"},{\"answer_id\":230,\"countries\":[{\"country_name\":\"Barbados\",\"id\":20},{\"country_name\":\"Italy\",\"id\":111},{\"country_name\":\"Turkmenistan\",\"id\":230},{\"country_name\":\"CocosIsland\",\"id\":48}],\"country_code\":\"TM\"},{\"answer_id\":81,\"countries\":[{\"country_name\":\"Maldives\",\"id\":137},{\"country_name\":\"Aruba\",\"id\":13},{\"country_name\":\"Monaco\",\"id\":148},{\"country_name\":\"Gabon\",\"id\":81}],\"country_code\":\"GA\"},{\"answer_id\":141,\"countries\":[{\"country_name\":\"Martinique\",\"id\":141},{\"country_name\":\"Montenegro\",\"id\":150},{\"country_name\":\"Barbados\",\"id\":20},{\"country_name\":\"Monaco\",\"id\":148}],\"country_code\":\"MQ\"},{\"answer_id\":23,\"countries\":[{\"country_name\":\"Germany\",\"id\":84},{\"country_name\":\"Dominica\",\"id\":63},{\"country_name\":\"Belize\",\"id\":23},{\"country_name\":\"Tuvalu\",\"id\":232}],\"country_code\":\"BZ\"},{\"answer_id\":60,\"countries\":[{\"country_name\":\"FalklandIslands\",\"id\":73},{\"country_name\":\"CzechRepublic\",\"id\":60},{\"country_name\":\"Mauritania\",\"id\":142},{\"country_name\":\"BritishIndianOceanTerritory\",\"id\":33}],\"country_code\":\"CZ\"},{\"answer_id\":235,\"countries\":[{\"country_name\":\"UnitedArabEmirates\",\"id\":235},{\"country_name\":\"UnitedArabEmirates\",\"id\":235},{\"country_name\":\"Macedonia\",\"id\":133},{\"country_name\":\"Guernsey\",\"id\":93}],\"country_code\":\"AE\"},{\"answer_id\":114,\"countries\":[{\"country_name\":\"TurksandCaicosIslands\",\"id\":231},{\"country_name\":\"Myanmar\",\"id\":154},{\"country_name\":\"Jersey\",\"id\":114},{\"country_name\":\"Ethiopia\",\"id\":72}],\"country_code\":\"JE\"},{\"answer_id\":126,\"countries\":[{\"country_name\":\"Malawi\",\"id\":135},{\"country_name\":\"TrinidadandTobago\",\"id\":227},{\"country_name\":\"Nepal\",\"id\":157},{\"country_name\":\"Lesotho\",\"id\":126}],\"country_code\":\"LS\"}]}"



    override fun getRepository() = _repository

    fun actionSave() {
        viewModelScope.launch {
            insertQuestions()
            Log.d("actionSave", "actionSave: ")
        }


    }

    private suspend fun insertQuestions() {

        val questionsResponse = Gson().fromJson(jsonString, FlagsChallengeResp::class.java)
        Log.d("insertQuestions", "insertQuestions: "+questionsResponse.questions.get(0).countries.size)

        questionsResponse.questions.forEach { question ->
            val questionEntity = Questions(
                answerId = question.answerId,
                countryCode = question.countryCode
            )
            Log.d("insertQuestions", "insertQuestions: "+question.countryCode)
            _repository.saveQuestionList(questionEntity)
            val countryEntities = question.countries.map { country ->
                Log.d("countryName", "countryName: "+country.countryName)
                Countries(
                    id = country.id,
                    countryName = country.countryName,
                    questionId = question.answerId
                )

            }
            _repository.saveCountriesList(countryEntities)
        }

        _successLiveData.value = true
    }

}