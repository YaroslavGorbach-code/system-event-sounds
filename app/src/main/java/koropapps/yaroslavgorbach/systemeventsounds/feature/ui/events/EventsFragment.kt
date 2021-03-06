package koropapps.yaroslavgorbach.systemeventsounds.feature.ui.events

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.ChangeActiveEventStatusUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.GetEventsUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.StartServiceIsAllowUseCase
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.UpdateEventUseCase
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.FragmentEventsBinding
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MainService
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MediaPlayerService
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.TextToSpeechService
import koropapps.yaroslavgorbach.systemeventsounds.feature.ui.update.UpdateEventDialog
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsFragment : Fragment(R.layout.fragment_events), UpdateEventDialog.Host {

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val changeActiveEventStatusUseCase = ChangeActiveEventStatusUseCase(getRepo())
        val startServiceIsAllowUseCase = StartServiceIsAllowUseCase(getRepo())

        // init view
        val v = EventsView(FragmentEventsBinding.bind(view), object : EventsView.Callback {
            override fun onSwitch(event: SystemEvent, isChecked: Boolean) {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        changeActiveEventStatusUseCase(event.name, isChecked)
                        if (!isChecked) context?.stopMediaServices()
                        if (startServiceIsAllowUseCase()) {
                            ContextCompat.startForegroundService(
                                requireContext(),
                                Intent(context, MainService::class.java)
                            )
                        } else {
                            context?.stopService(Intent(context, MainService::class.java))
                        }
                    }
                }

            }

            override fun onEvent(event: SystemEvent) {
                UpdateEventDialog().apply {
                    arguments = UpdateEventDialog.argsOf(event)
                }.show(childFragmentManager, null)
            }
        })
        GetEventsUseCase(getRepo())().observe(viewLifecycleOwner, v::setEvents)
    }

    override fun onUpdated(systemEvent: SystemEvent) {
        lifecycleScope.launch {
            UpdateEventUseCase(getRepo())(systemEvent)
        }
    }

    @InternalCoroutinesApi
    private fun Context.stopMediaServices() {
        stopService(Intent(context, MediaPlayerService::class.java))
        stopService(Intent(context, TextToSpeechService::class.java))
    }
}