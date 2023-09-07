package uz.gita.HK_dictionary.presenter.adapter

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity
import uz.gita.HK_dictionary.databinding.UzEnItemLayoutBinding

class MyUzEnAdapter : RecyclerView.Adapter<MyUzEnAdapter.MyViewHolder>() {
    private var cursor: Cursor? = null
    private var isValid = false

    @SuppressLint("NotifyDataSetChanged")
    fun submitCursor(_cursor: Cursor?) {
        cursor = _cursor
        notifyDataSetChanged()
    }

    private val dataSetObservable = object : DataSetObserver() {
        @SuppressLint("NotifyDataSetChanged")
        override fun onChanged() {
            isValid = true
            notifyDataSetChanged()
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onInvalidated() {
            isValid = false
            notifyDataSetChanged()
        }
    }

    init {
        cursor?.registerDataSetObserver(dataSetObservable)
        isValid = true
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCursor(newCursor: Cursor) {
        isValid = false
        cursor?.unregisterDataSetObserver(dataSetObservable)
        cursor?.close()

        newCursor.registerDataSetObserver(dataSetObservable)
        cursor = newCursor
        isValid = true
        notifyDataSetChanged()
    }

    private var showDialogClickListener: ((DictionaryEntity) -> Unit)? = null

    fun showDialogClickListener(function: (DictionaryEntity) -> Unit) {
        showDialogClickListener = function
    }

    inner class MyViewHolder(private val binding: UzEnItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.constraint.setOnClickListener {
                cursor?.let {
                    it.moveToPosition(absoluteAdapterPosition)
                    val dictionary = DictionaryEntity(
                        id = it.getString(0),
                        english = it.getString(1),
                        type = it.getString(2),
                        transcript = it.getString(3),
                        uzbek = it.getString(4),
                        countable = it.getString(5),
                        favourite = it.getInt(6)
                    )
                    showDialogClickListener?.invoke(dictionary)
                }
            }
        }

        fun bind() {
            binding.apply {
                cursor?.let {
                    it.moveToPosition(absoluteAdapterPosition)
                    textUzbek.text = it.getString(4)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            UzEnItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = cursor?.count ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }
}