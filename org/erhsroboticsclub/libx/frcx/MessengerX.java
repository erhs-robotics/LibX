import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Michael Stevens
 */

public class MessengerX {
    static {
        SmartDashboard.putString("dashboard", "");
    }
    public static void println(String str) {        
        String data = SmartDashboard.getString("dashboard");
        String time = "[" + (int)Timer.getFPGATimestamp() + "]: ";
        SmartDashboard.putString("dashboard", data + time + str + "\n");
    }
}
