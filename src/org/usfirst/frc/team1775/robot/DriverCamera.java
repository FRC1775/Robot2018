
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class DriverCamera extends IterativeRobot {
    
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
    }
}