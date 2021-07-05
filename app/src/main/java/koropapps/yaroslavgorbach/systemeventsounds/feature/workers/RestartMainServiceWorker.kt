package koropapps.yaroslavgorbach.systemeventsounds.feature.workers

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import koropapps.yaroslavgorbach.systemeventsounds.bussines.usecases.StartServiceIsAllowUseCase
import koropapps.yaroslavgorbach.systemeventsounds.feature.services.MainService
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@InternalCoroutinesApi
class RestartMainServiceWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        if (StartServiceIsAllowUseCase(applicationContext.getRepo())())
        GlobalScope.launch {
            ContextCompat.startForegroundService(
                applicationContext,
                Intent(applicationContext, MainService::class.java)
            )
        }
        return Result.success()
    }

    companion object {
        fun requestRestart(context: Context) {
            val startServiceRequest =
                PeriodicWorkRequestBuilder<RestartMainServiceWorker>(15, TimeUnit.MINUTES)
                    .build()
            WorkManager.getInstance(context).enqueue(startServiceRequest)
        }
    }
}

