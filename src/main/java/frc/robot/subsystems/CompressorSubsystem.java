package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class CompressorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  
  private final Compressor compressor = new Compressor (PneumaticsModuleType.CTREPCM);
  private final PneumaticsControlModule module = new PneumaticsControlModule();
  private final AnalogInput pressure = new AnalogInput(0);
  
  public CompressorSubsystem() {
    // compressor.enableHybrid(0, 40);
  }

  public void update() {
    // compressor.disable();
    // System.out.println(compressor.getPressure() + " " + pressure.getValue());
    
  }

}
  
