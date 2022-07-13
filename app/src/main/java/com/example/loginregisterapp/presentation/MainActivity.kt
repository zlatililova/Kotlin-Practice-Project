package com.example.loginregisterapp.presentation

import android.bluetooth.BluetoothAdapter.ERROR
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.ERROR
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.loginregisterapp.BuildConfig
import com.example.loginregisterapp.R
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import java.util.logging.Level

class MainActivity : AppCompatActivity(), ActivityActions {

    lateinit var navController: NavController
    lateinit var frameLayout: FrameLayout
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayout = findViewById(R.id.layout_loading)
        progress = findViewById(R.id.loading_progress)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun showProgress() {
        frameLayout.visibility = View.VISIBLE
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        frameLayout.visibility = View.GONE
        progress.visibility = View.GONE
    }


}