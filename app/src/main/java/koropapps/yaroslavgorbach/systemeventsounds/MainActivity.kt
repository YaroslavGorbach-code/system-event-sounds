package koropapps.yaroslavgorbach.systemeventsounds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import koropapps.yaroslavgorbach.systemeventsounds.feature.ui.events.EventsFragment
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MainService
import koropapps.yaroslavgorbach.systemeventsounds.feature.workers.RestartMainServiceWorker
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                val fragment = EventsFragment()
                add(R.id.main_container, fragment)
                setPrimaryNavigationFragment(fragment)
            }
            RestartMainServiceWorker.requestRestart(this)
        }
    }
}