package com.shchurovsi.carspecapp.presentation.fragments

sealed class State {
    class InputBrandError(val state: Boolean) : State()
    class InputMotorPowerError(val state: Boolean) : State()
    class InputSeatsError(val state: Boolean) : State()
    data object ShouldCloseScreenState : State()
}
