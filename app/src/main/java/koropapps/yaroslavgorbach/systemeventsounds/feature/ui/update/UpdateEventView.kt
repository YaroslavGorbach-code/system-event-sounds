package koropapps.yaroslavgorbach.systemeventsounds.feature.ui.update

import android.net.Uri
import android.view.View
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.DialogUpdateEventBinding
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getName

class UpdateEventView(
    private val binding: DialogUpdateEventBinding,
    private val callback: Callback,
    private var event: SystemEvent
) {
    private var uri: Uri? = null

    interface Callback {
        fun onPickFile()
        fun onUpdate(systemEvent: SystemEvent)
    }

    init {
        binding.radioText.setOnClickListener {
            binding.showPickText()
        }
        binding.radioUri.setOnClickListener {
            binding.showPickUri()
        }
        binding.pickFile.setOnClickListener {
            callback.onPickFile()
        }

        binding.update.setOnClickListener {
            if (binding.radioText.isChecked) {
                event.textToSpeech = binding.text.text.toString()
                event.fileUri = null
            }
            if (binding.radioUri.isChecked && uri != null) {
                event.fileUri = uri
                event.textToSpeech = null
            }
            callback.onUpdate(event)
        }

        binding.fileName.text = event.fileUri?.getName(binding.root.context)
            ?: binding.root.context.getString(R.string.file_name)

        event.textToSpeech?.let {
            binding.radioText.isChecked = true
            binding.text.setText(it)
            binding.showPickText()
        }

        event.fileUri?.let {
            binding.radioUri.isChecked = true
            binding.showPickUri()
        }
    }

    fun getRoot() = binding.root

    private fun DialogUpdateEventBinding.showPickUri() {
        textInputLayout.visibility = View.GONE
        pickFileLayout.visibility = View.VISIBLE
    }

    private fun DialogUpdateEventBinding.showPickText() {
        textInputLayout.visibility = View.VISIBLE
        pickFileLayout.visibility = View.GONE
    }

    fun setUri(uri: Uri?) {
        binding.fileName.text =
            uri?.getName(binding.root.context) ?: binding.root.context.getString(R.string.file_name)
        this.uri = uri
    }

}