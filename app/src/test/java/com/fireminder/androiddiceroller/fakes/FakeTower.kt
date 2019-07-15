package com.fireminder.androiddiceroller.fakes

import com.fireminder.androiddiceroller.implementations.BaseEvaluator
import com.fireminder.androiddiceroller.implementations.BaseParser
import com.fireminder.androiddiceroller.implementations.BaseResultGenerator
import com.fireminder.androiddiceroller.implementations.BaseTower

class FakeTower : BaseTower(
    BaseParser(),
    BaseEvaluator(FakeRng()),
    FakeRng(),
    BaseResultGenerator()
)