package ru.rorshak.profile.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import ru.rorshak.profile.R
import ru.rorshak.profile.databinding.*
import ru.rorshak.profile.model.Item
import java.lang.IllegalArgumentException

class ItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    lateinit var prnt: ViewGroup
    var clicked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        prnt = parent
        return when (viewType) {
            VIEW_TYPE_HEADER ->
                ViewHolderHeader(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            VIEW_TYPE_TEXT ->
                ViewHolderText(
                    ItemTextBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            VIEW_TYPE_PHOTO ->
                ViewHolderPhoto(
                    ItemPhotoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            VIEW_TYPE_DESC ->
                ViewHolderDesc(
                    ItemDescBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            VIEW_TYPE_SKILL ->
                ViewHolderSkill(
                    ItemSkillBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw IllegalArgumentException("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is Item.Header -> (holder as? ViewHolderHeader)?.onBind(item)
            is Item.withText -> (holder as? ViewHolderText)?.onBind(item)
            is Item.Skill -> (holder as? ViewHolderSkill)?.onBind(item)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is Item.Header -> VIEW_TYPE_HEADER
            is Item.withText -> VIEW_TYPE_TEXT
            is Item.Photo -> VIEW_TYPE_PHOTO
            is Item.Desc -> VIEW_TYPE_DESC
            is Item.Skill -> VIEW_TYPE_SKILL
        }

    override fun getItemCount(): Int = items.size

    class ViewHolderSkill(
        private val viewBinding: ItemSkillBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.Skill) {
            viewBinding.skill.text = item.name
            viewBinding.time.text = item.time
        }
    }

    class ViewHolderDesc(
        private val viewBinding: ItemDescBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)

    inner class ViewHolderPhoto(
        private val viewBinding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.button.setOnClickListener() {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rorShaK03"))
                prnt.context.startActivity(intent)
            }
        }
    }

    inner class ViewHolderHeader(
        private val viewBinding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.filterBtn.setOnClickListener() {
                if (clicked) {
                    viewBinding.filterBtn.setImageResource(R.drawable.ic_filter)
                    clicked = false
                } else {
                    viewBinding.filterBtn.setImageResource(R.drawable.ic_filter_used)
                    clicked = true
                }
            }
        }
        fun onBind(item: Item.Header) {
            viewBinding.header.text = item.title
        }
    }

    class ViewHolderText(
        private val viewBinding: ItemTextBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.withText) {
            viewBinding.text.text = item.text
        }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_TEXT = 2
        private const val VIEW_TYPE_PHOTO = 3
        private const val VIEW_TYPE_DESC = 4
        private const val VIEW_TYPE_SKILL = 5
    }
}
