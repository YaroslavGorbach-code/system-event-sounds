package koropapps.yaroslavgorbach.systemeventsounds.data

import android.net.Uri
import androidx.annotation.StringRes
import koropapps.yaroslavgorbach.systemeventsounds.R

enum class EventName(@StringRes val idRes: Int) {
    WIFI_ON(R.string.wifi_on),
    WIFI_OFF(R.string.wifi_off),
    AIRPLANE_MOD_ON(R.string.airplane_mod_on),
    AIRPLANE_MOD_OFF(R.string.airplane_mod_off)
}

data class SystemEvent(
    val name: EventName,
    val textToSpeech: String?,
    val fileUri: Uri?,
    val active: Boolean = false
)