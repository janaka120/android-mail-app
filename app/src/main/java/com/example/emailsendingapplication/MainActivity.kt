package com.example.emailsendingapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emailsendingapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)

        mainBinding.buttonSend.setOnClickListener {
            val userSubject = mainBinding.editTextSubject.text.toString()
            val userAddress = mainBinding.editTextAddress.text.toString()
            val mail = mainBinding.editTextMail.text.toString()

            sendMail(userSubject, userAddress, mail)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun sendMail(subject: String, address: String, content: String) {
        val emailAddresses = arrayOf(address)

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, content)

        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Choose a app"))
        }
    }
}