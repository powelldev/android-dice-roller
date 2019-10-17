package com.fireminder.androiddiceroller.ui.basic

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_KEY_EVENT
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.fireminder.androiddiceroller.R
import com.fireminder.androiddiceroller.ui.BaseActivity

class BasicNumberPadView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val one: View
    private val two: View
    private val three: View
    private val four: View
    private val five: View
    private val six: View
    private val seven: View
    private val eight: View
    private val nine: View
    private val zero: View

    private val plus: View
    private val minus: View

    init {
        inflate(context, R.layout.basic_number_pad, this)
        one = findViewById(R.id.number_pad_1)
        two = findViewById(R.id.number_pad_2)
        three = findViewById(R.id.number_pad_3)
        four = findViewById(R.id.number_pad_4)
        five = findViewById(R.id.number_pad_5)
        six = findViewById(R.id.number_pad_6)
        seven = findViewById(R.id.number_pad_7)
        eight = findViewById(R.id.number_pad_8)
        nine = findViewById(R.id.number_pad_9)
        zero = findViewById(R.id.number_pad_0)

        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)

        one.setOnClickListener { notify("1") }
        two.setOnClickListener { notify("2") }
        three.setOnClickListener { notify("3") }
        four.setOnClickListener { notify("4") }
        five.setOnClickListener { notify("5") }
        six.setOnClickListener { notify("6") }
        seven.setOnClickListener { notify("7") }
        eight.setOnClickListener { notify("8") }
        nine.setOnClickListener { notify("9") }
        zero.setOnClickListener { notify("0") }

        plus.setOnClickListener { notify("+") }
        minus.setOnClickListener { notify("-") }
    }

    private fun notify(string: String) {
        context.sendBroadcast(
          Intent(BaseActivity.ACTION_NUMPAD_KEY_EVENT)
            .apply { putExtra(EXTRA_KEY_EVENT, string) });
    }
}