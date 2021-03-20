package com.tangonoches.teacher.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.IActivityInterface
import kotlinx.android.synthetic.main.content_main_drawer.*
import ru.nds.core.presentation.base.BaseVmActivity

class MainDrawerActivity : BaseVmActivity<MainDrawerVm>(MainDrawerVm::class), IActivityInterface {
    override val layoutId: Int = R.layout.activity_main_drawer

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var tvEmail: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val tvEmail = ((navView.getHeaderView(0) as ViewGroup).getChildAt(0) as AppCompatTextView)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_lessons_all,
                R.id.nav_tickets_all,
                R.id.nav_students_all
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        vm.email.observe(this, Observer {
            tvEmail.text = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun startLoading() {
        Log.d("lifecycle", "${this::class.java.simpleName} startLoading")
        nav_host_loading.visibility = View.VISIBLE
    }

    override fun completeLiading() {
        Log.d("lifecycle", "${this::class.java.simpleName} startLoading")
        nav_host_loading.visibility = View.GONE
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
