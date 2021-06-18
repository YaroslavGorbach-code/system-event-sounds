package koropapps.yaroslavgorbach.systemeventsounds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import koropapps.yaroslavgorbach.systemeventsounds.screen.EventsFragment

class MainActivity : AppCompatActivity() {
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
    }
}