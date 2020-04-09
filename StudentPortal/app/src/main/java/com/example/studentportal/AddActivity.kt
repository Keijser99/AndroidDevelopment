package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_add.*

const val EXTRA_TITLE = "EXTRA_TITLE"
const val EXTRA_LINK = "EXTRA_LINK"
class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews(){
        fab.setOnClickListener{
            onSaveClick()
        }

    }

    private fun onSaveClick() {
        if(etAddLinkTitle.text.toString().isNotBlank()){
            val link = Link(etAddLinkTitle.text.toString(), etAddLink.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_TITLE, link)
            resultIntent.putExtra(EXTRA_LINK, link)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }else{
            Toast.makeText(this, "Cannot be empty!", Toast.LENGTH_SHORT).show()

        }
    }
}


