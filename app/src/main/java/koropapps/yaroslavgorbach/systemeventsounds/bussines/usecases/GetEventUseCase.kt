package koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases

import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

class GetEventUseCase(private val repo: Repo) {
    suspend operator fun invoke(eventName: EventName): SystemEvent {
        return repo.getEvent(eventName)
    }
}