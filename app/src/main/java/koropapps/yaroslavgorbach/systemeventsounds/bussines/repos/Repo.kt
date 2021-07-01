package koropapps.yaroslavgorbach.systemeventsounds.bussines.repos

import androidx.lifecycle.LiveData
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

interface Repo {
    fun getEvents(): LiveData<List<SystemEvent>>
    fun getEvent(name: EventName): SystemEvent
    fun updateEvent(event: SystemEvent)
}


