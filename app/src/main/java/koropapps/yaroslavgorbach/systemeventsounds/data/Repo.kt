package koropapps.yaroslavgorbach.systemeventsounds.data

import android.content.BroadcastReceiver
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import koropapps.yaroslavgorbach.systemeventsounds.App

interface Repo {
    fun getEvents(): LiveData<List<SystemEvent>>
    fun getEvent(name: EventName): SystemEvent
    fun consumeEvent(eventName: EventName, consume: Boolean)
}

fun Fragment.getRepo(): Repo = (requireActivity().application as App).provideRepo()
fun BroadcastReceiver.getRepo(context: Context?): Repo = RepoImp
