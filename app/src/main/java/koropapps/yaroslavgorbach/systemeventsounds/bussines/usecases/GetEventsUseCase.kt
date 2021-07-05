package koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases

import androidx.lifecycle.LiveData
import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

class GetEventsUseCase(private val repo: Repo) {
    operator fun invoke(): LiveData<List<SystemEvent>> {
        return repo.getEvents()
    }
}