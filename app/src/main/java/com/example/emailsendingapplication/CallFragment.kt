package com.example.emailsendingapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Suppress("DEPRECATION")
class CallFragment : Fragment() {
    lateinit var phone: EditText
    lateinit var callBtn: Button
    var userNumber: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_call, container, false)

        phone = view.findViewById(R.id.editTextPhone)
        callBtn = view.findViewById(R.id.buttonCall)

        callBtn.setOnClickListener {
            userNumber = phone.text.toString()
            callToUser(userNumber)
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun callToUser(number: String) {
        if(activity?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.CALL_PHONE) } != PackageManager.PERMISSION_GRANTED) {
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.CALL_PHONE), 100) }
        }else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$userNumber")
            startActivity(intent)
        }
    }
}