package koropapps.yaroslavgorbach.systemeventsounds.feature.util

import android.content.BroadcastReceiver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.fragment.app.Fragment
import koropapps.yaroslavgorbach.systemeventsounds.App
import koropapps.yaroslavgorbach.systemeventsounds.data.local.repos.RepoImp
import koropapps.yaroslavgorbach.systemeventsounds.bussines.repos.Repo
import koropapps.yaroslavgorbach.systemeventsounds.data.local.room.Db

fun Uri.getName(context: Context): String {
    val returnCursor = context.contentResolver.query(this, null, null, null, null)
    val nameIndex = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor?.moveToFirst()
    val fileName = returnCursor?.getString(nameIndex!!)
    returnCursor?.close()
    return fileName.toString()
}
fun Fragment.getRepo(): Repo = (requireActivity().application as App).provideRepo()
fun Context.getRepo(): Repo = RepoImp.getInstance(Db.getInstance(this))