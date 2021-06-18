package koropapps.yaroslavgorbach.systemeventsounds.screen

import androidx.recyclerview.widget.LinearLayoutManager
import koropapps.yaroslavgorbach.systemeventsounds.data.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.FragmentEventsBinding

class EventsView(binding: FragmentEventsBinding, callback: Callback) {
    interface Callback{
        fun onSwitch(item: SystemEvent, isChecked: Boolean)
        fun onEvent(item: SystemEvent)
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
    }

    fun setEvents(events: List<SystemEvent>){
        adapter.submitList(events)
    }
}