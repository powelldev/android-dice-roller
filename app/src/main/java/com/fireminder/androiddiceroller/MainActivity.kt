package com.fireminder.androiddiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fireminder.androiddiceroller.implementations.BaseEvaluator
import com.fireminder.androiddiceroller.implementations.BaseParser
import com.fireminder.androiddiceroller.implementations.BaseResultGenerator
import com.fireminder.androiddiceroller.implementations.BaseTower
import com.fireminder.androiddiceroller.interfaces.Rng
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executeButton.setOnClickListener {
            val input = expressionEditText.text.toString()
            resultTextView.text = evaluate(input)
        }
    }

    private fun evaluate(input: String): String {
        val tower = BaseTower(BaseParser(), BaseEvaluator(RealRng()), RealRng(), BaseResultGenerator())
        val result = tower.roll(input)
        return result.prettyPrint()
    }
}

class RealRng : Rng {
    override fun next(lowerBoundInclusive: Int, upperBoundInclusive: Int): Int {
        return (lowerBoundInclusive..upperBoundInclusive).shuffled().first()
    }

}
