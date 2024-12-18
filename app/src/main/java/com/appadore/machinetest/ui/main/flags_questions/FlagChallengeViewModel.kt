package com.appadore.machinetest.ui.main.flags_questions



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appadore.machinetest.data.model.flags_challenge.Countries
import com.appadore.machinetest.data.model.flags_challenge.QuestionWithCountries
import com.appadore.machinetest.data.model.flags_challenge.Questions
import com.appadore.machinetest.data.model.repository.ChallengeRepository
import com.appadore.machinetest.ui.base.BaseViewModel
import com.appadore.machinetest.utils.CountriesConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlagChallengeViewModel @Inject constructor(private val _repository: ChallengeRepository) :
    BaseViewModel() {
    override fun getRepository() = _repository
    val questionsLiveData: LiveData<List<Questions>?>
        get() = _questionsLiveData
    private val _questionsLiveData = MutableLiveData<List<Questions>?>()

    val countriesLiveData: LiveData<List<Countries>?>
        get() = _countriesLiveData
    private val _countriesLiveData = MutableLiveData<List<Countries>?>()


    fun fetchQuestions() {
        viewModelScope.launch {
            _repository.getallQuestions().collect {
                _questionsLiveData.value = it
            }
        }
    }
    fun getCountriesForAnswer(id:Int) {
        viewModelScope.launch {
            _repository.getCountriesForAnswer(id).collect {
                _countriesLiveData.value = it
            }
        }
    }

}