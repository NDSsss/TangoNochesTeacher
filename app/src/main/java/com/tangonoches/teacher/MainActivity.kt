package com.tangonoches.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tangonoches.teacher.presentation.base.IActivityInterface

class MainActivity : AppCompatActivity(), IActivityInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun startLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun completeLiading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
