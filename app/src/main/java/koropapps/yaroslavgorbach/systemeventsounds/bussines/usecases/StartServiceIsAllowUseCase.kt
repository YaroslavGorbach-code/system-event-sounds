package koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases

import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo

class StartServiceIsAllowUseCase(private val repo: Repo) {
    suspend operator fun invoke(): Boolean {
        return repo.getActiveEvents().isNotEmpty()
    }
}