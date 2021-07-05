package koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases

import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo

class StartServiceIsAllowUseCase(private val repo: Repo) {
    operator fun invoke(): Boolean {
        return repo.getEvents().value?.find { it.active } != null
    }
}