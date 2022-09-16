package org.di.log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val diViewPrinter = DiViewPrinter(this)
        diViewPrinter.viewProvider.showFloatingView()
        DiLogManager.getInstance().addPrinter(diViewPrinter)
    }
}