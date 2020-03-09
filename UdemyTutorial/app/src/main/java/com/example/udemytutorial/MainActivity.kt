package com.example.udemytutorial

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    internal var textView: TextView? = null
    internal var button: Button? = null

    protected fun OnCreate(SavedInstanceState: Bundle) {
        super.onCreate(SavedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
