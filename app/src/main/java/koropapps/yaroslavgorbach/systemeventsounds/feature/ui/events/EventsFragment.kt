package koropapps.yaroslavgorbach.systemeventsounds.feature.ui.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.ChangeActiveEventStatusUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventsUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.FragmentEventsBinding
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo

class EventsFragment : Fragment(R.layout.fragment_events) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val changeActiveEventStatusUseCase = ChangeActiveEventStatusUseCase(getRepo())

        // init view
        val v = EventsView(FragmentEventsBinding.bind(view), object : EventsView.Callback {
            override fun onSwitch(event: SystemEvent, isChecked: Boolean) {
                changeActiveEventStatusUseCase(event.name, isChecked)
            }

            override fun onEvent(event: SystemEvent) {

            }
        })
        GetEventsUseCase(getRepo())().observe(viewLifecycleOwner, v::setEvents)
    }
}