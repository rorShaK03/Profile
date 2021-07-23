package ru.rorshak.profile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import ru.rorshak.profile.adapter.ItemsAdapter
import ru.rorshak.profile.databinding.ActivityMainBinding
import ru.rorshak.profile.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var itemsAdapter : ItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        itemsAdapter = ItemsAdapter()
        with(viewBinding.recycler)
        {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
        itemsAdapter.items = listOf(Item.Photo(""), Item.Desc(""), Item.Header("Навыки"), Item.Skill("Kotlin", "<1 года"),
        Item.Skill("C++", "2 года"), Item.Skill("Python", "3 года"), Item.Skill("Assembler", "менее  1 года"))
    }
}