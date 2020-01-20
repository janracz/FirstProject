package com.raczynski.firstproject

import android.Manifest.permission.SEND_SMS
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest
import android.content.pm.PackageManager


class MainActivity : AppCompatActivity() {

    companion object {
        const val SENT = "SMS_SENT"
        const val DELIVERED = "SMS_DELIVERED"
    }


    private lateinit var sentPI: PendingIntent
    private lateinit var deliveredPI: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sentPI = PendingIntent.getBroadcast(this, 0, Intent(SENT), 0)
        deliveredPI = PendingIntent.getBroadcast(this, 0, Intent(DELIVERED), 0)

        hour.text = "0:00"
        val sendButton = findViewById(R.id.sendButton) as Button

        //set hourSeekBar listener
        hourOfTexting.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                hour.text = "$progress:00"

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(applicationContext, "start", Toast.LENGTH_SHORT)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(applicationContext, "stop", Toast.LENGTH_SHORT)
//                sendSMS()
            }

        })

        sendButton.setOnClickListener {

//            sendSMS()
            try {
                val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage("+48 698491139", null, "test", sentPI, deliveredPI)
                    Toast.makeText(applicationContext, "GIT", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }

    }


//    fun sendSMS()
//    {
//        val uri = Uri.parse("smsto:601428333")
//        val intent = Intent(Intent.ACTION_SENDTO, uri)
//        intent.putExtra("sms_body", "Test")
//        startActivity(intent)
//    }
}
