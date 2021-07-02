package koropapps.yaroslavgorbach.systemeventsounds.feature.ui.update

import android.Manifest
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.DialogUpdateEventBinding

class UpdateEventDialog : DialogFragment() {

    interface Host {
        fun onUpdated(systemEvent: SystemEvent)
    }

    companion object {
        fun argsOf(event: SystemEvent) = bundleOf("event" to event)
        private val UpdateEventDialog.event: SystemEvent?
            get() = arguments?.let {
                it["event"] as SystemEvent
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var v: UpdateEventView? = null

        val getUri =
            registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
                v?.setUri(uri)
            }

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    getUri.launch(arrayOf("audio/*"))
                }
            }

        // init view
        v = event?.let { event ->
            UpdateEventView(
                DialogUpdateEventBinding.inflate(LayoutInflater.from(requireContext())),
                object : UpdateEventView.Callback {
                    override fun onPickFile() {
                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }

                    override fun onUpdate(systemEvent: SystemEvent) {
                        (parentFragment as Host).onUpdated(systemEvent)
                        dialog?.dismiss()
                    }
                }, event
            )
        }

        return MaterialAlertDialogBuilder(requireContext())
            .setView(v?.getRoot())
            .setTitle(R.string.updateEvent)
            .create()
    }
}


