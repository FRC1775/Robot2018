package org.usfirst.frc.team1775.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;

public class TestSPI {
	private SPI pixy;
	
	public TestSPI() {
		pixy =  new SPI(SPI.Port.kOnboardCS0);
		pixy.setMSBFirst();
		pixy.setChipSelectActiveLow();
		pixy.setClockRate(1000);
		pixy.setSampleDataOnFalling();
	    pixy.setClockActiveLow();
	}
	
	public void doSomething() {
		byte[] arr = new byte[2];
		pixy.transaction(new byte[] { 0x00, 0x5a },  arr, 2);
		System.out.println(arr[0] + " " + arr[1]);
	}
}
