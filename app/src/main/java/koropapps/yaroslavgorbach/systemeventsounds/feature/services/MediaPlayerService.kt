package koropapps.yaroslavgorbach.systemeventsounds.feature.services

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getServiceNotification
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MediaPlayerService : Service() {
    private val mediaPlayer = MediaPlayer()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val uri: Uri = intent?.data!!
        mediaPlayer.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            if (!mediaPlayer.isPlaying) {
                setDataSource(applicationContext, uri)
                prepareAsync()
            }
            setOnPreparedListener {
                start()
            }
            setOnCompletionListener {
                stopSelf()
            }
        }

        startForeground(1, this.getServiceNotification())
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}