package com.fireminder.androiddiceroller.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.fireminder.androiddiceroller.R

class ClearRollFavoriteActionsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val clear: View
    private val roll: View
    private val favorite: View

    init {
        inflate(context, R.layout.clear_roll_favorite_actions_view, this)
        clear = findViewById(R.id.clear)
        roll = findViewById(R.id.roll)
        favorite = findViewById(R.id.favorite)

        clear.setOnClickListener {
            notify(DiceBagAction.Clear)
        }

        roll.setOnClickListener {
            notify(DiceBagAction.Roll)
        }

        favorite.setOnClickListener {
            notify(DiceBagAction.Favorite)
        }
    }

    enum class DiceBagAction(id: String) {
        Clear("clear"), Roll("roll"), Favorite("favorite");
        val action: String = "com.fireminder.androiddiceroller.$id"
    }

    private fun notify(action: DiceBagAction) {
        LocalBroadcastManager
            .getInstance(context)
            .sendBroadcastSync(Intent(action.action))
    }
}
