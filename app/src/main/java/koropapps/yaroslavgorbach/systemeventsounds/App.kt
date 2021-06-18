package koropapps.yaroslavgorbach.systemeventsounds

import android.app.Application
import koropapps.yaroslavgorbach.systemeventsounds.data.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.RepoImp
import koropapps.yaroslavgorbach.systemeventsounds.data.RepoProvider

class App: Application(), RepoProvider {
    private val repo: Repo by lazy { RepoImp() }
    override fun provideRepo(): Repo {
        return repo
    }

}