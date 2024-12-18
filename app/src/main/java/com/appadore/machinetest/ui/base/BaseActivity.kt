package com.appadore.machinetest.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VM : ViewModel, B : ViewDataBinding> : AppCompatActivity() {
    protected var TAG: String = javaClass.simpleName
    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        viewModel = ViewModelProvider(this).get(getViewModelType())

        bindViews()
    }
    abstract fun getLayout(): Int

    abstract fun getViewModelType(): Class<VM>

    abstract fun bindViews()
}