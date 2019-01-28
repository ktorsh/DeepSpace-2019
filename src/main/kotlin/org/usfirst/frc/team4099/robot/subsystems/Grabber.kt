package org.usfirst.frc.team4099.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.Talon
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team4099.robot.Constants
import org.usfirst.frc.team4099.robot.loops.BrownoutDefender
import org.usfirst.frc.team4099.robot.loops.Loop

class Grabber private constructor() : Subsystem{

    private val pneumaticShifter: DoubleSolenoid = DoubleSolenoid(Constants.Grabber.SHIFTER_FORWARD_ID, Constants.Grabber.SHIFTER_REVERSE_ID)
    private val talonSRX : Talon = Talon(Constants.Grabber.TALON_ID)
    private var pushStartTime = 0.0
    var intakeState = IntakeState.NEUTRAL
    var push = false
        set (wantsPush) {
            if (wantsPush) {
                pushStartTime = (System.currentTimeMillis()).toDouble()
            } else {
            }
            field = wantsPush
        }

    enum class IntakeState {
        IN, OUT, NEUTRAL
    }

    override fun outputToSmartDashboard() {
        SmartDashboard.putBoolean("intake/isPushed", push)
        SmartDashboard.putNumber("intake/current", BrownoutDefender.instance.getCurrent(7))
    }

    @Synchronized override fun stop() {
        pneumaticShifter.set(DoubleSolenoid.Value.kOff)
        setIntakePower(0.0)
    }

    private fun setIntakePower(power : Double){
        talonSRX.set(power)
    }

    val loop: Loop = object : Loop {
        override fun onStart() {
            push = false
        }

        override fun onLoop() {
            synchronized(this@Grabber) {
                if(push == true && System.currentTimeMillis() + 100 > pushStartTime){
                    push = false
                }
                when(intakeState){
                    IntakeState.IN -> setIntakePower(-1.0)
                    IntakeState.OUT -> setIntakePower(1.0)
                    IntakeState.NEUTRAL -> setIntakePower(0.0)
                }
            }
        }

        override fun onStop() = stop()
    }

    companion object {
        val instance = Grabber()
    }

    override fun zeroSensors() { }
}