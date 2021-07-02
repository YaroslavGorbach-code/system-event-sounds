package koropapps.yaroslavgorbach.systemeventsounds.feature.ui.events

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.FragmentEventsBinding
import koropapps.yaroslavgorbach.systemeventsounds.feature.common.LineDecorator

class EventsView(binding: FragmentEventsBinding, callback: Callback) {
    interface Callback{
        fun onSwitch(event: SystemEvent, isChecked: Boolean)
        fun onEvent(event: SystemEvent)
    }
    private val adapter: EventsAdapter = EventsAdapter(object : EventsAdapter.Callback{
        override fun onSwitch(item: SystemEvent, isChecked: Boolean) {
            callback.onSwitch(item, isChecked)
        }

        override fun onEvent(item: SystemEvent) {
            callback.onEvent(item)
        }
    })

    init {
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(binding.root.context)
        binding.list.addItemDecoration(LineDecorator(binding.root.context, R.drawable.line_devider))
    }

    fun setEvents(events: List<SystemEvent>){
        adapter.setData(events)
        Log.v("event", "set event view")
    }
}