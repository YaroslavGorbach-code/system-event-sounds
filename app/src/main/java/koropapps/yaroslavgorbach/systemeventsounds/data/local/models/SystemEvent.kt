package koropapps.yaroslavgorbach.systemeventsounds.data.local.models

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import koropapps.yaroslavgorbach.systemeventsounds.R

enum class EventName(@StringRes val idRes: Int) {
    WIFI_ON(R.string.wifi_on),
    WIFI_OFF(R.string.wifi_off),
    MOBILE_INET_ON(R.string.mobile_inet_on),
    MOBILE_INET_OFF(R.string.mobile_inet_off),
    AIRPLANE_MOD_ON(R.string.airplane_mod_on),
    AIRPLANE_MOD_OFF(R.string.airplane_mod_off_default_text),
    BLUETOOTH_ON(R.string.bluetooth_on),
    BLUETOOTH_OFF(R.string.bluetooth_off),
    SCREEN_ON(R.string.screen_on),
    SCREEN_OFF(R.string.screen_off),
    USB_ATTACHED(R.string.usb_attached),
    USB_DETACHED(R.string.usb_detached),
    APP_INSTALLED(R.string.app_installed),
    APP_DELETED(R.string.app_deleted),
    HEADPHONES_PLUGGED(R.string.headphones_plugged),
    HEADPHONES_UNPLUGGED(R.string.headphones_unplugged),
}

data class SystemEvent(
    val name: EventName,
    @DrawableRes val imageId: Int,
    var textToSpeech: String?,
    var fileUri: Uri?,
    var active: Boolean = false,
    var consumed: Boolean = false
):Parcelable {
    constructor(parcel: Parcel) : this(
        EventName.valueOf(parcel.readString().toString()),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Uri::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name.name)
        parcel.writeInt(imageId)
        parcel.writeString(textToSpeech)
        parcel.writeParcelable(fileUri, flags)
        parcel.writeByte(if (active) 1 else 0)
        parcel.writeByte(if (consumed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SystemEvent> {
        override fun createFromParcel(parcel: Parcel): SystemEvent {
            return SystemEvent(parcel)
        }

        override fun newArray(size: Int): Array<SystemEvent?> {
            return arrayOfNulls(size)
        }
    }
}