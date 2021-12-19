package com.example.phonestateandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi

class PhoneStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        try {
            var telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            telephonyManager.listen(object : PhoneStateListener() {
                @RequiresApi(Build.VERSION_CODES.P)
                override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                    super.onCallStateChanged(state, phoneNumber)

                    var mData: String = "";
                    when (state) {
                        TelephonyManager.CALL_STATE_RINGING -> {
                            mData = "PHONE_RINGING";
                        }
                        TelephonyManager.CALL_STATE_IDLE -> {
                            mData = "PHONE_IDLE";
                        }
                        TelephonyManager.CALL_STATE_OFFHOOK -> {
                            mData = "PHONE_IN_CALL";
                        }
                    }
                    Log.d("TAG", "$mData");
                    Toast.makeText(context.applicationContext, "$mData", Toast.LENGTH_SHORT)
                        .show();
                }
            }, PhoneStateListener.LISTEN_CALL_STATE)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}