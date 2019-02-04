package org.usfirst.frc.team4099.robot

import org.usfirst.frc.team4099.lib.joystick.Gamepad
import org.usfirst.frc.team4099.lib.joystick.JoystickUtils
import org.usfirst.frc.team4099.lib.joystick.XboxOneGamepad

class ControlBoard private constructor() {
    private val driver: Gamepad = XboxOneGamepad(Constants.Joysticks.DRIVER_PORT)
    private val operator: Gamepad = XboxOneGamepad(Constants.Joysticks.SHOTGUN_PORT)

//    val raiseIntake: Boolean
//        get() = operator.dPadLeft
//
//    val lowerIntake: Boolean
//        get() = operator.dPadRight
//
//    val reverseIntakeSlow: Boolean
//        get() = operator.bButton
//
//    val reverseIntakeFast: Boolean
//        get() = operator.yButton
//
//    val runIntake: Boolean
//        get() = operator.aButton
//
//    val moveUp: Boolean
//        get() = operator.dPadUp
//
//    val moveDown: Boolean
//        get() = operator.dPadDown
//
//    val toggle: Boolean
//        get() = operator.rightShoulderButton


    companion object {
        val instance = ControlBoard()
    }

}
