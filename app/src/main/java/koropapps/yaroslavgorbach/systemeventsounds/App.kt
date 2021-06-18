package koropapps.yaroslavgorbach.systemeventsounds

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import koropapps.yaroslavgorbach.systemeventsounds.data.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.RepoImp
import koropapps.yaroslavgorbach.systemeventsounds.data.RepoProvider

class App: Application(), RepoProvider {
    private val repo: Repo by lazy { RepoImp() }
    override fun provideRepo(): Repo {
        return repo
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "1",
                "Service notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = this.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

}