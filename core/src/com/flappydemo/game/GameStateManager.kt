package com.flappydemo.game

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappydemo.game.states.BaseState
import java.util.*

class GameStateManager {
    private val states = Stack<BaseState>()

    fun push(state: BaseState) {
        states.push(state)
    }

    fun pop() {
        states.pop().dispose()
    }

    fun set(state: BaseState) {
        pop()
        push(state)
    }

    fun update(delta: Float) {
        val state = states.peek()
        state.handleInput()
        state.update(delta)
    }

    fun render(batch: SpriteBatch) {
        states.peek().render(batch)
    }
}
