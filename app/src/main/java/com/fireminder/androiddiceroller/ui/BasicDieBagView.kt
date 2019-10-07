package com.fireminder.androiddiceroller.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_KEY_EVENT
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.fireminder.androiddiceroller.R

class BasicDieBagView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val d20: View

    init {
        inflate(context, R.layout.basic_die_bag, this)
        d20 = findViewById(R.id.d20)

        d20.setOnClickListener {
            notify("d20")
        }
    }

    private fun notify(string: String) {
        context.sendBroadcast(
                Intent(BaseActivity.ACTION_NUMPAD_KEY_EVENT)
                    .apply {
                        putExtra(EXTRA_KEY_EVENT, string)
                    })
    }
}

