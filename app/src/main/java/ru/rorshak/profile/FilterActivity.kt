package ru.rorshak.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import ru.rorshak.profile.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityFilterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle(R.string.filter_title)
        viewBinding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.applyBtn.setOnClickListener {
            intent = Intent()
            intent.putExtra("less_one", viewBinding.lessoneChbox.isChecked())
            intent.putExtra("two", viewBinding.twoChbox.isChecked())
            intent.putExtra("three", viewBinding.threeChbox.isChecked())
            setResult(RESULT_OK, intent)
            finish()
        }
        viewBinding.lessoneChbox.setOnClickListener {
            if(viewBinding.lessoneChbox.isChecked() == false)
                viewBinding.allChbox.setChecked(false)
            if(viewBinding.lessoneChbox.isChecked() && viewBinding.twoChbox.isChecked() && viewBinding.threeChbox.isChecked())
                viewBinding.allChbox.setChecked(true)
        }
        viewBinding.twoChbox.setOnClickListener {
            if(viewBinding.twoChbox.isChecked() == false)
                viewBinding.allChbox.setChecked(false)
            if(viewBinding.lessoneChbox.isChecked() && viewBinding.twoChbox.isChecked() && viewBinding.threeChbox.isChecked())
                viewBinding.allChbox.setChecked(true)
        }
        viewBinding.threeChbox.setOnClickListener {
            if(viewBinding.threeChbox.isChecked() == false)
                viewBinding.allChbox.setChecked(false)
            if(viewBinding.lessoneChbox.isChecked() && viewBinding.twoChbox.isChecked() && viewBinding.threeChbox.isChecked())
                viewBinding.allChbox.setChecked(true)
        }
        viewBinding.allChbox.setOnClickListener {
            if(viewBinding.allChbox.isChecked())
            {
                viewBinding.lessoneChbox.setChecked(true)
                viewBinding.twoChbox.setChecked(true)
                viewBinding.threeChbox.setChecked(true)
            }
            else
            {
                viewBinding.lessoneChbox.setChecked(false)
                viewBinding.twoChbox.setChecked(false)
                viewBinding.threeChbox.setChecked(false)
            }
        }
    }
}