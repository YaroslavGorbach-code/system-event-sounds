package koropapps.yaroslavgorbach.systemeventsounds.data.local.room

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.EventName
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent

@Database(entities = [SystemEvent::class], version = 1)
@TypeConverters(Db.Converters::class)

abstract class Db : RoomDatabase() {
    abstract fun systemEventDao(): SystemEventDao

    companion object {
        fun getInstance(context: Context): Db {
            return Room.databaseBuilder(
                context,
                Db::class.java, "db"
            ).addCallback(object :RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    val cv = ContentValues()
                    cv.put("name", EventName.WIFI_ON.name)
                    cv.put("imageId", R.drawable.ic_wifi)
                    cv.put("textToSpeech", context.getString(R.string.wifi_on_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.WIFI_OFF.name)
                    cv.put("imageId", R.drawable.ic_wifi_off)
                    cv.put("textToSpeech", context.getString(R.string.wifi_off_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.MOBILE_INET_ON.name)
                    cv.put("imageId", R.drawable.ic_mobiledata)
                    cv.put("textToSpeech", context.getString(R.string.mobile_inet_on_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.MOBILE_INET_OFF.name)
                    cv.put("imageId", R.drawable.ic_mobiledata_off)
                    cv.put("textToSpeech", context.getString(R.string.mobile_inet_off_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.AIRPLANE_MOD_ON.name)
                    cv.put("imageId", R.drawable.ic_airplanemode)
                    cv.put("textToSpeech", context.getString(R.string.airplane_mod_on_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.AIRPLANE_MOD_OFF.name)
                    cv.put("imageId", R.drawable.ic_airplanemode_inactive)
                    cv.put("textToSpeech", context.getString(R.string.airplane_mod_off_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.BLUETOOTH_ON.name)
                    cv.put("imageId", R.drawable.ic_bluetooth)
                    cv.put("textToSpeech", context.getString(R.string.bluetooth_on_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.BLUETOOTH_OFF.name)
                    cv.put("imageId", R.drawable.ic_bluetooth_disabled)
                    cv.put("textToSpeech", context.getString(R.string.bluetooth_off_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.SCREEN_OFF.name)
                    cv.put("imageId", R.drawable.ic_screen_off)
                    cv.put("textToSpeech", context.getString(R.string.screen_off_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.SCREEN_ON.name)
                    cv.put("imageId", R.drawable.ic_screen_on)
                    cv.put("textToSpeech", context.getString(R.string.screen_on_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.USB_ATTACHED.name)
                    cv.put("imageId", R.drawable.ic_usb)
                    cv.put("textToSpeech", context.getString(R.string.usb_plugged_in_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.USB_DETACHED.name)
                    cv.put("imageId", R.drawable.ic_usb_off)
                    cv.put("textToSpeech", context.getString(R.string.usb_plugged_out_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.APP_INSTALLED.name)
                    cv.put("imageId", R.drawable.ic_instoll_app)
                    cv.put("textToSpeech", context.getString(R.string.app_installed_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.APP_DELETED.name)
                    cv.put("imageId", R.drawable.ic_delete_app)
                    cv.put("textToSpeech", context.getString(R.string.app_deleted_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.HEADPHONES_PLUGGED.name)
                    cv.put("imageId", R.drawable.ic_headphones)
                    cv.put("textToSpeech", context.getString(R.string.headphones_plugged_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()

                    cv.put("name", EventName.HEADPHONES_UNPLUGGED.name)
                    cv.put("imageId", R.drawable.ic_headset_off)
                    cv.put("textToSpeech", context.getString(R.string.headphones_unplugged_default_text))
                    cv.put("active", false)
                    cv.put("consumed", false)
                    db.insert("system_event", SQLiteDatabase.CONFLICT_REPLACE, cv)
                    cv.clear()
                    super.onCreate(db)
                }
            }).build()
        }
    }

    class Converters {
        @TypeConverter
        fun formStringToUri(value: String?): Uri? {
            return if (value == null) null else Uri.parse(value)
        }

        @TypeConverter
        fun fromUriToString(uri: Uri?): String? {
            return uri?.toString()
        }

        @TypeConverter
        fun toEventName(value: String) = enumValueOf<EventName>(value)

        @TypeConverter
        fun fromEventName(value: EventName) = value.name
    }
}
