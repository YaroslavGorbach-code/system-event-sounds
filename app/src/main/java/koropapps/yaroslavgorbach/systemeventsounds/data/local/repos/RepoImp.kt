package koropapps.yaroslavgorbach.systemeventsounds.data.local.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.data.local.room.Db

class RepoImp private constructor(private val db: Db) : Repo {

    companion object{
        private var instance: Repo? = null
        fun getInstance(db: Db): Repo {
            if (instance == null){
                instance = RepoImp(db)
            }
            return instance as Repo
        }
    }

    override fun getEvents(): LiveData<List<SystemEvent>> {
        return db.systemEventDao().getEvents()
    }

    override suspend fun getEvent(name: EventName): SystemEvent {
        return db.systemEventDao().getEvent(name)
    }

    override suspend fun updateEvent(event: SystemEvent) {
        db.systemEventDao().update(event)
    }

    override suspend fun getActiveEvents(): List<SystemEvent> {
        return db.systemEventDao().getActive()
    }
}
