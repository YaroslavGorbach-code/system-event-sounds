package koropapps.yaroslavgorbach.systemeventsounds.util

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.renderscript.ScriptGroup
import androidx.annotation.StringRes
import koropapps.yaroslavgorbach.systemeventsounds.databinding.FragmentEventsBinding
import koropapps.yaroslavgorbach.systemeventsounds.databinding.ItemEventBinding

fun Uri.getName(context: Context): String {
    val returnCursor = context.contentResolver.query(this, null, null, null, null)
    val nameIndex = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor?.moveToFirst()
    val fileName = returnCursor?.getString(nameIndex!!)
    returnCursor?.close()
    return fileName.toString()
}

fun ItemEventBinding.getString(@StringRes id: Int): String = root.context.getString(id)
