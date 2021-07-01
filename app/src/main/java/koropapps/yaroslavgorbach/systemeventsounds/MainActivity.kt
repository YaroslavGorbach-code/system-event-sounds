package koropapps.yaroslavgorbach.systemeventsounds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import koropapps.yaroslavgorbach.systemeventsounds.feature.ui.events.EventsFragment
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MainService
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                val fragment = EventsFragment()
                add(R.id.main_container, fragment)
                setPrimaryNavigationFragment(fragment)
            }
        }
        ContextCompat.startForegroundService(
            this,
            Intent(this, MainService::class.java)
        )
    }
}