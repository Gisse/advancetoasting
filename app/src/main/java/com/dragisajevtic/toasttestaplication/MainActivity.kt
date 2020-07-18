package com.dragisajevtic.toasttestaplication

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dragisajevtic.advancetoasts.Toasting
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCLickListeners()
    }

    private fun setCLickListeners() {
        val dancingBold = Typeface.createFromAsset(
            assets,
            "font/dancingscriptbold.ttf"
        )
        val dancing = Typeface.createFromAsset(
            assets,
            "font/dancingscriptregular.ttf"
        )
        bottomButton.setOnClickListener {
            Toasting(applicationContext)
                .setMessage("Bottom Message")
                .setMessageTypeface(dancingBold)
                .setToastVerticalPosition(Toasting.ToastPosition.BOTTOM)
                .setToastVerticalOffset(60)
                .setTitleTextColor(resources.getColor(R.color.red, null))
                .setMessageTextColor(resources.getColor(R.color.info_green, null))
                .setRadius(5)
                .setToastColor(resources.getColor(R.color.gold, null))
                .build()
                .show()
        }

        middleButton.setOnClickListener {
            Toasting(applicationContext)
                .setMessage("Middle Message")
                .setMessageTypeface(dancingBold)
                .setToastVerticalPosition(Toasting.ToastPosition.CENTER)
                .setTitleTextColor(resources.getColor(R.color.red, null))
                .setMessageTextColor(resources.getColor(R.color.info_green, null))
                .setRadius(5)
                .setToastColor(resources.getColor(R.color.gold, null))
                .build()
                .show()
        }

        topButton.setOnClickListener {
            Toasting(applicationContext)
                .setTitle("Top Title")
                .setTitleTypeFace(dancingBold)
                .setTitleTextSize(32)
                .setMessage("Top Message")
                .setMessageTypeface(dancing)
                .setToastVerticalPosition(Toasting.ToastPosition.TOP)
                .setTitleTextColor(resources.getColor(R.color.red, null))
                .setMessageTextColor(resources.getColor(R.color.info_green, null))
                .setRadius(10)
                .setToastVerticalOffset(60)
                .setToastColor(resources.getColor(R.color.gold, null))
                .build()
                .show()
        }
    }
}
