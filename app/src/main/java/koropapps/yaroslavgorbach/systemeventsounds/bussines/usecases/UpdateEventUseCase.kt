package koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases

import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

class UpdateEventUseCase(private val repo: Repo) {
    suspend operator fun invoke(event: SystemEvent) {
        repo.updateEvent(event)
    }
}