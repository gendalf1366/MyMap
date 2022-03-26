package ru.gendalf13666.mymap.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.gendalf13666.mymap.R
import ru.gendalf13666.mymap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main), Updater {

    private val viewBinding: ActivityMainBinding by viewBinding()
    private val publisher = Publisher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)

        val navView: BottomNavigationView = viewBinding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_map, R.id.navigation_markers
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun getUpdater(): Publisher =
        publisher
}
