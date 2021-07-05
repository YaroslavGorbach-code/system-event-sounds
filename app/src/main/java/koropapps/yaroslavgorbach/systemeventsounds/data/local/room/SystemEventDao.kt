package koropapps.yaroslavgorbach.systemeventsounds.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

@Dao
interface SystemEventDao {

    @Query("SELECT * FROM system_event")
    fun getEvents(): LiveData<List<SystemEvent>>

    @Query("SELECT * FROM system_event WHERE name =:name")
    suspend fun getEvent(name: EventName): SystemEvent

    @Update
    suspend fun update(event: SystemEvent)

    @Query("SELECT * FROM system_event WHERE active = 1 ")
    fun getActive(): List<SystemEvent>

}
