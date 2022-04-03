// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.ClimbConstants.*;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  private float position;
  
  private DoubleSolenoid climbPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, kClimbPistonPort[0], kClimbPistonPort[1]);

  private DigitalInput leftLimit = new DigitalInput(kLimitPort[0]);
  private DigitalInput rightLimit = new DigitalInput(kLimitPort[1]);

  private CANSparkMax[] motors = new CANSparkMax[]{
      new CANSparkMax(kClimbPort[0], MotorType.kBrushless),
      new CANSparkMax(kClimbPort[1], MotorType.kBrushless)
  };

  private SparkMaxPIDController[] controllers = new SparkMaxPIDController[]{
      motors[0].getPIDController(),
      motors[1].getPIDController()
  };

  //private RelativeEncoder encoder = motors[0].getEncoder();
  //private SparkMaxPIDController controller = motors[0].getPIDController();
  
  /** Creates a new ClimbSubsystem. */
  public ClimbSubsystem() {
    climbPiston.set(kReverse);
    
    for (CANSparkMax m : motors) {
      m.setIdleMode(IdleMode.kBrake);
    }
    controllers[0].setFeedbackDevice(motors[0].getEncoder());
    controllers[1].setFeedbackDevice(motors[1].getEncoder());
    position = 0;
    updateConstants();
  }

  private void updateConstants() {
    for (SparkMaxPIDController controller : controllers) {
      controller.setOutputRange(-1, 1);
      controller.setP(kP);
      controller.setI(kI);
      controller.setD(kD);
      controller.setFF(kF);
    }
  }

  public void testMotor(double speed) {
    motors[0].set(speed);
  }

  public void move(double speed) {
    // for(CANSparkMax m : motors) {
    //   m.set(speed);
    // }
    //look over this: (if approved, delete this comment)
    //notes: be sure to enter ports for limit switches in constants
    if(speed < 0){
      if(!leftLimit.get()){
        motors[0].set(0);
        System.out.println("LEFT Limit switch pressed");
    
      }else{
        motors[0].set(speed);
      }

      if(!rightLimit.get()){
        motors[1].set(0);
        System.out.println("RIGHT Limit switch pressed");
      }else{
        motors[1].set(-speed);
        //idk which motor is which index, and idk if positive speed is up or down, so check it over
        //also the overloaded methods of move() set different motors to be negative
      }
      
    }else{
      motors[0].set(speed);
      motors[1].set(-speed);
    }
    
    // motors[0].set(speed);
    // motors[1].set(-speed);

    
  }

  //PID version of move()
  public void climb(double speed) {
    position += speed * kClimbSpeed;
    if (position < 0) {
      position = 0;
    } else if (position > kClimbMax) {
      position = kClimbMax;
    }
    

    // System.out.println(motors[0].getEncoder().getPosition() + " " + motors[1].getEncoder().getPosition());
  }

  public void move(int id, double speed) {
    if(id == 0) {
      speed *= -1;
    }
    motors[id].set(speed * kClimbSpeed);
  }
  
  public void toggleClimbPiston(){
    climbPiston.toggle();
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // System.out.println(!leftLimit.get() + " " + !rightLimit.get());
    //limits
    if (!leftLimit.get()) motors[0].getEncoder().setPosition(0);
    if (!rightLimit.get()) motors[1].getEncoder().setPosition(0);
    controllers[0].setReference(position, ControlType.kPosition);
    controllers[1].setReference(-position, ControlType.kPosition);
    System.out.println(motors[0].getEncoder().getPosition() + " " + motors[1].getEncoder().getPosition() + " " + position);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
