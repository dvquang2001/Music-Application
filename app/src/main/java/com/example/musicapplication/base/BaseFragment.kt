package com.example.musicapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<BINDING : ViewDataBinding, VM : ViewModel>(val layoutId: Int) :
    Fragment(), View.OnClickListener {

    protected lateinit var binding: BINDING

    protected val viewModel by lazy { initViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    private fun initViewModel(): VM {
        return ViewModelProvider(this)[getViewModelClass()]
    }

    @Suppress("UNCHECKED_CAST")
    protected open fun getViewModelClass(): Class<VM> {
        var genericSuperClass = javaClass.genericSuperclass
        var parametrizedType: ParameterizedType? = null

        //Try to find generic from current class first, if can not find up into super class to find
        while (parametrizedType == null) {
            if(genericSuperClass is ParameterizedType) {
                parametrizedType = genericSuperClass
            } else {
                genericSuperClass = (genericSuperClass as Class<*>).genericSuperclass
            }
        }
        return parametrizedType.actualTypeArguments[1] as Class<VM>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewObserver()
        initViewListener()
        initData()
    }

    override fun onClick(view: View?) {
        view?.let { onViewClicked(it) }
    }

    open fun onViewClicked(view: View) {}

    abstract fun initView()

    abstract fun initViewObserver()

    abstract fun initViewListener()

    abstract fun initData()

}