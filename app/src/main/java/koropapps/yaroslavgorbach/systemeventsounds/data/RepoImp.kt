package koropapps.yaroslavgorbach.systemeventsounds.data

object RepoImp : Repo {
    private var events = arrayListOf(
        SystemEvent(EventName.WIFI_ON, null, null),
        SystemEvent(EventName.WIFI_OFF, null, null),
        SystemEvent(EventName.MOBILE_INET_ON, null, null),
        SystemEvent(EventName.MOBILE_INET_OFF, null, null),
        SystemEvent(EventName.AIRPLANE_MOD_ON, null, null),
        SystemEvent(EventName.AIRPLANE_MOD_OFF, null, null),
        SystemEvent(EventName.BLUETOOTH_ON, null, null),
        SystemEvent(EventName.BLUETOOTH_OFF, null, null),
        SystemEvent(EventName.SCREEN_OFF, null, null),
        SystemEvent(EventName.SCREEN_ON, null, null),
        SystemEvent(EventName.USB_ATTACHED, null, null),
        SystemEvent(EventName.USB_DETACHED, null, null),
        SystemEvent(EventName.APP_INSTALLED, null, null),
        SystemEvent(EventName.APP_DELETED, null, null),
        SystemEvent(EventName.HEADPHONES_PLUGGED, null, null),
        SystemEvent(EventName.HEADPHONES_UNPLUGGED, null, null, consumed =  true),
        )

    override fun getEvents(): List<SystemEvent> {
        return events
    }

    override fun getEvent(name: EventName): SystemEvent {
       return events.find { it.name == name }!!
    }

    override fun consumeEvent(eventName: EventName, consume: Boolean) {
        events = events.apply {
            find { it.name == eventName }?.consumed = consume
        }
    }
}