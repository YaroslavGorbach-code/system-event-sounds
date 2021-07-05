package koropapps.yaroslavgorbach.systemeventsounds.bussines.repos

import androidx.lifecycle.LiveData
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

interface Repo {
    fun getEvents(): LiveData<List<SystemEvent>>
    suspend fun getEvent(name: EventName): SystemEvent
    suspend fun updateEvent(event: SystemEvent)
    suspend fun getActiveEvents(): List<SystemEvent>
}


