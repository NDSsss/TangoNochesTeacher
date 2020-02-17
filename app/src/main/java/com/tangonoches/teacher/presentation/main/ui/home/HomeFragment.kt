package com.tangonoches.teacher.presentation.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseVmFragment

class HomeFragment : BaseVmFragment<HomeVm>() {
    override val layoutId: Int = R.layout.fragment_home

    override fun getVmClass(): Class<HomeVm> =
        HomeVm::class.java

}