package ro.pub.cs.systems.eim.colocviu1_1

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import java.util.Locale
import java.text.SimpleDateFormat

class Colocviu1_1Service : Service() {

    private val handler = Handler(Looper.getMainLooper())
    var pressed: String = ""
    var count: Int = 0
    private var runnable: Runnable? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        pressed = intent?.getStringExtra("butt") ?: ""
        count = intent?.getIntExtra("count", 0) ?: 0

        runnable = object : Runnable {
            override fun run() {
                sendBroadcastMessage()
                handler.postDelayed(this, 2000)
            }
        }
        handler.post(runnable!!)

        return START_REDELIVER_INTENT
    }

    private fun sendBroadcastMessage() {
        val now = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ).format(System.currentTimeMillis())

        val broadcastIntent = Intent().apply {
            action = PRIMARY_ACTION
            putExtra("datetime", now)
            putExtra("pressed", pressed)
            putExtra("count", count)
        }

        sendBroadcast(broadcastIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        runnable?.let { handler.removeCallbacks(it) }
    }

    override fun onBind(intent: Intent): IBinder ? {
        return null
    }
}