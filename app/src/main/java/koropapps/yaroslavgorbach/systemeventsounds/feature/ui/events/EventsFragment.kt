package koropapps.yaroslavgorbach.systemeventsounds.feature.ui.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventsUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.FragmentEventsBinding
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo

class EventsFragment : Fragment(R.layout.fragment_events) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init view
        val v = EventsView(FragmentEventsBinding.bind(view), object : EventsView.Callback {
            override fun onSwitch(item: SystemEvent, isChecked: Boolean) {

            }

            override fun onEvent(item: SystemEvent) {

            }
        })
        GetEventsUseCase(getRepo())().observe(viewLifecycleOwner, v::setEvents)
    }
}