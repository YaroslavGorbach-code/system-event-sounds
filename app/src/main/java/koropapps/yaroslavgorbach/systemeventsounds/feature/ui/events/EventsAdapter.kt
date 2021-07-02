package koropapps.yaroslavgorbach.systemeventsounds.feature.ui.events

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import koropapps.yaroslavgorbach.systemeventsounds.R
import koropapps.yaroslavgorbach.systemeventsounds.data.local.models.SystemEvent
import koropapps.yaroslavgorbach.systemeventsounds.databinding.ItemEventBinding
import koropapps.yaroslavgorbach.systemeventsounds.feature.util.getName
import java.util.ArrayList

class EventsAdapter(private val callback: Callback) : RecyclerView.Adapter<EventsAdapter.Vh>() {
    interface Callback {
        fun onSwitch(item: SystemEvent, isChecked: Boolean)
        fun onEvent(item: SystemEvent)
    }

    init {
        setHasStableIds(true)
    }

    private var items: List<SystemEvent> = ArrayList<SystemEvent>()

    fun setData(list: List<SystemEvent>){
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            callback
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = position.toLong()

    inner class Vh(private val binding: ItemEventBinding, private val callback: Callback) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                callback.onEvent(items[adapterPosition])
            }
        }

        fun bind(item: SystemEvent) {
            binding.eventName.setText(item.name.idRes)
            binding.icEvent.setImageDrawable(ContextCompat.getDrawable(binding.root.context, item.imageId))
            if (!item.textToSpeech.isNullOrEmpty()) binding.eventTextFileName.text = item.textToSpeech
            if (item.fileUri != null) binding.eventTextFileName.text = item.fileUri!!.getName(itemView.context)

            if (item.active) {
                binding.ovalActive.drawable.setTint(Color.GREEN)
                binding.textActive.setText(R.string.active)
                binding.textActive.setTextColor(Color.GRAY)
            } else {
                binding.ovalActive.drawable.setTint(Color.LTGRAY)
                binding.textActive.setText(R.string.not_active)
                binding.textActive.setTextColor(Color.LTGRAY)
            }
            // workaround because we can't change switch isChecked state with active listener
            binding.start.setOnCheckedChangeListener(null)
            binding.start.isChecked = item.active
            binding.start.setOnCheckedChangeListener { _, isChecked ->
                callback.onSwitch(item, isChecked)
            }
        }

    }

}