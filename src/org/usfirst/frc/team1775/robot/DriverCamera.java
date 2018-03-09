package org.usfirst.frc.team1775.robot;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;

public class DriverCamera {
	
	private static final PixelFormat PIXEL_FORMAT = PixelFormat.kMJPEG;
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 180;
	private static final int FPS = 15;
	
	private Thread cameraThread;

    public void init() {
    	CvSource imageSource = CameraServer.getInstance().putVideo("Camera Viewer", IMG_WIDTH, IMG_HEIGHT);
		NetworkTable.getTable("").putString("CameraSelection", "Camera Viewer");
		
		cameraThread = new Thread() -> {
			try {
				UsbCamera mainCamera = initMainCamera();
			}catch{
				
			}
		}
    }
    
    private UsbCamera initMainCamera() {
    	
		UsbCamera shooterCamera = new UsbCamera("Camera", getMainCameraDevice());
		shooterCamera.setPixelFormat(PIXEL_FORMAT);
		shooterCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		shooterCamera.setFPS(FPS);
		shooterCamera.setExposureManual(0); // SHOOTER_CAMERA_EXPOSURE
		shooterCamera.getProperty("brightness").set(40); // SHOOTER_CAMERA_BRIGHTNESS
		shooterCamera.getProperty("white_balance_temperature_auto").set(0); // SHOOTER_CAMERA_WHITE_BALANCE_TEMP_AUTO
		shooterCamera.getProperty("white_balance_temperature").set(4000); // SHOOTER_CAMERA_WHITE_BALANCE_TEMP
		shooterCamera.getProperty("focus_auto").set(0); // SHOOTER_CAMERA_FOCUS_AUTO
		shooterCamera.getProperty("focus_absolute").set(0); // SHOOTER_CAMERA_FOCUS_ABSOLUTE
			
		return shooterCamera;
	}
    
    private int getMainCameraDevice() {
    	// The 1 was SHOOTER_CAMERA_DEVICE in 2017 robot code. I put 1 because that's what it was
    	// equivalent to. I actually don't know what this does.
		return Preferences.getInstance().getInt("Cameras.shooter", 1);
	}
}