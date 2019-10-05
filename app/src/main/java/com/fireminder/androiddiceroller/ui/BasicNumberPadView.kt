package com.fireminder.androiddiceroller.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_KEY_EVENT
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.fireminder.androiddiceroller.R

class BasicNumberPadView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val one: View
    private val two: View
    private val plus: View

    init {
        inflate(context, R.layout.basic_number_pad, this)
        one = findViewById(R.id.number_pad_1)
        two = findViewById(R.id.number_pad_2)
        plus = findViewById(R.id.plus)

        one.setOnClickListener { notify("1") }
        two.setOnClickListener { notify("2") }
        plus.setOnClickListener { notify("+") }
    }

    private fun notify(string: String) {
        LocalBroadcastManager
            .getInstance(context)
            .sendBroadcastSync(
                Intent(BaseActivity.ACTION_NUMPAD_KEY_EVENT)
                    .apply {
                        putExtra(EXTRA_KEY_EVENT, string)
                    })
    }
}