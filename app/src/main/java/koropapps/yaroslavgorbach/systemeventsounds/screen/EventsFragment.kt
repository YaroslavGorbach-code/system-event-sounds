package koropapps.yaroslavgorbach.systemeventsounds.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.data.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.data.getRepo
import koropapps.yaroslavgorbach.systemeventsounds.databinding.FragmentEventsBinding

class EventsFragment: Fragment(R.layout.fragment_events) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init view
        val v = EventsView(FragmentEventsBinding.bind(view), object : EventsView.Callback{
            override fun onSwitch(item: SystemEvent, isChecked: Boolean) {

            }

            override fun onEvent(item: SystemEvent) {

            }
        })
        v.setEvents(getRepo().getEvents())
    }
}