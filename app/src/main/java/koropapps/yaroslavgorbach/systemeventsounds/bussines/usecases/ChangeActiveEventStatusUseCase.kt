package koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases

import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName

class ChangeActiveEventStatusUseCase(private val repo: Repo) {
    operator fun invoke(eventName: EventName, isActive: Boolean){
        val event = repo.getEvent(eventName)
        event.active = isActive
        repo.updateEvent(event)
    }
}