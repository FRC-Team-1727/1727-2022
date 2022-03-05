package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class CompressorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  
  private Compressor compressor = new Compressor (PneumaticsModuleType.CTREPCM);
  
  public CompressorSubsystem() {
    compressor.enableHybrid(70, 80);
  }
  
