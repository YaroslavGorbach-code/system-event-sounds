package koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases

import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName

class ConsumeEventUseCase(private val repo: Repo) {
    suspend operator fun invoke(eventName: EventName, consume: Boolean) {
        val event = repo.getEvent(eventName)
        event.consumed = consume
        repo.updateEvent(event)
    }
}