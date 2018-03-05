package org.usfirst.frc.team1775.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.usfirst.frc.team1775.robot.SingleLineFormatter;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;


public class VisionProcessor extends Subsystem {


	// These values are the default if you instantiate a PixySPI without arguments.
	// To create multiple PixySPI objects and thus use multiple Pixy cameras via SPI
	// Copy the items below, change variable names as needed and especially change
	// the SPI port used eg; Port.kOnboardCS[0-3] or Port.kMXP
	public PixySPI pixy;
	Port port = Port.kOnboardCS0;
	String print;
	private List<Integer> xVals;
	private static Logger logger;
	ArrayList<PixyPacket> packetList;
	private static int WATCHED_SIG = 3;
	
	public VisionProcessor() {
		packetList = new ArrayList<PixyPacket>();
		
		LogManager.getLogManager().reset();
		logger = Logger.getLogger(PixySPI.class.getName());
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SingleLineFormatter());
		logger.addHandler(handler);
		
		// Open a pipeline to a Pixy camera.
		pixy = new PixySPI(new SPI(port), new PixyException(print));
		logger.info("Pixy Vision here, y'all.");
		
		xVals = new ArrayList<Integer>();
	}


	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void readPixy(){

		HashMap<Integer, ArrayList<PixyPacket>> packets = null;

		// Get the packets from the pixy.
		try {
			packets = pixy.readPackets();
		} catch (PixyException e) {
			logger.info("No packets from Pixy!");
			e.printStackTrace();
		}
		
		List<PixyPacket> signatureSet = packets.get(WATCHED_SIG);
		if (signatureSet != null) {
			
			try {
				int maxWidth = signatureSet
						.stream()
						.map(p -> p.Width)
						.mapToInt(i->i)
						.max()
						.getAsInt();
			logger.log(Level.INFO, "max width: " + maxWidth);
			}
			catch (Exception e) {
//				logger.info(e.getMessage());
			}
		}
	}
}

