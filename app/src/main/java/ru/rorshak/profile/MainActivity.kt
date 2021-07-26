package ru.rorshak.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import ru.rorshak.profile.adapter.ItemsAdapter
import ru.rorshak.profile.databinding.ActivityMainBinding
import ru.rorshak.profile.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var itemsAdapter: ItemsAdapter
    var vsbl : MutableList<Boolean> = mutableListOf(true, true, true)
    val less_one_age = listOf("Kotlin", "Assembler")
    val two_age = listOf("C++")
    val three_age = listOf("Python")
    var state = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        itemsAdapter = ItemsAdapter()
        with(viewBinding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
        var lst : MutableList<Item> = mutableListOf(Item.Photo(""), Item.Desc(""), Item.Header("Навыки"))
        if(vsbl[0])
            for (name in less_one_age)
                lst.add(Item.Skill(name, "<1 года"))
        if(vsbl[1])
            for (name in two_age)
                lst.add(Item.Skill(name, "2 года"))
        if(vsbl[2])
            for (name in three_age)
                lst.add(Item.Skill(name, "3 года"))
        itemsAdapter.items = lst
        itemsAdapter.listener = FilterClickListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        vsbl[0] = data?.getBooleanExtra("less_one", true) ?: true
        vsbl[1] = data?.getBooleanExtra("two", true) ?: true
        vsbl[2] = data?.getBooleanExtra("three", true) ?: true
        var lst : MutableList<Item> = mutableListOf(Item.Photo(""), Item.Desc(""), Item.Header("Навыки"))
        if(vsbl[0])
            for (name in less_one_age)
                lst.add(Item.Skill(name, "<1 года"))
        if(vsbl[1])
            for (name in two_age)
                lst.add(Item.Skill(name, "2 года"))
        if(vsbl[2])
            for (name in three_age)
                lst.add(Item.Skill(name, "3 года"))
        if(vsbl[0] && vsbl[1] && vsbl[2])
            itemsAdapter.headerBinding.filterBtn.setImageResource(R.drawable.ic_filter)
        else
            itemsAdapter.headerBinding.filterBtn.setImageResource(R.drawable.ic_filter_used)
        itemsAdapter.items = lst
        super.onActivityResult(requestCode, resultCode, data)
    }

    inner class FilterClickListener : View.OnClickListener
    {
        override fun onClick(p0: View?) {
            var intent = Intent(this@MainActivity, FilterActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
}
