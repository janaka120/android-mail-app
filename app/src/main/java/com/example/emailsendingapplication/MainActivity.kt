package com.example.emailsendingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var sendMailBtn: Button
    lateinit var callBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendMailBtn = findViewById(R.id.buttonSendView)
        callBtn = findViewById(R.id.buttonCallView)
        val fragmentManager: FragmentManager = supportFragmentManager

        sendMailBtn.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val mailFragment = MailSendFragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, mailFragment)
            fragmentTransaction.commit()
        }

        callBtn.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val callFragment = CallFragment()
            fragmentTransaction.replace(R.id.fragmentContainerView, callFragment)
            fragmentTransaction.commit()
        }
    }
}