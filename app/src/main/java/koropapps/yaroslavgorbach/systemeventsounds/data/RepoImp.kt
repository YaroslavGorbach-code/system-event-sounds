package koropapps.yaroslavgorbach.systemeventsounds.data

class RepoImp : Repo {
    private val events = arrayListOf(
        SystemEvent(EventName.WIFI_ON, null, null),
        SystemEvent(EventName.WIFI_OFF, null, null),
        SystemEvent(EventName.AIRPLANE_MOD_ON, null, null),
        SystemEvent(EventName.AIRPLANE_MOD_OFF, null, null)
    )


    override fun getEvents(): List<SystemEvent> {
        return events
    }
}