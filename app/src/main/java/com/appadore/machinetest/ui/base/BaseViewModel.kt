package com.appadore.machinetest.ui.base

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.appadore.machinetest.data.model.repository.BaseRepository


abstract class BaseViewModel : ViewModel() {

    protected var TAG: String = javaClass.simpleName



    private val _actionLiveData: MutableLiveData<Int> = MutableLiveData()
    val actionLiveData: LiveData<Int> get() = _actionLiveData
    val sessionLiveData = MutableLiveData<Boolean>()
    private val swipeProgressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val _messagesLiveData: MutableLiveData<String> = MutableLiveData()
    val messagesLiveData: LiveData<String> get() = _messagesLiveData
    val emptyLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val _signatureLiveData: MutableLiveData<String> = MutableLiveData()
    val signatureLiveData: LiveData<String> get() = _signatureLiveData

    abstract fun getRepository(): BaseRepository










}