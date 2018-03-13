package org.usfirst.frc.team1775.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	private IntBuffer xBuf;
	private static Logger logger;
	ArrayList<Integer> xList;
	
	private static int YELLOW_CUBE = 1;
	private static int RED_BOTTLE = 2;
	private static int WATCHED_SIG = YELLOW_CUBE;
	private static int BUFF_CAP = 100;
	
	public VisionProcessor() {
		xList = new ArrayList<Integer>();
		xBuf = IntBuffer.allocate(BUFF_CAP);
		
		LogManager.getLogManager().reset();
		logger = Logger.getLogger(PixySPI.class.getName());
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SingleLineFormatter());
		logger.addHandler(handler);
		
		// Open a pipeline to a Pixy camera.
		pixy = new PixySPI(new SPI(port), new PixyException(print));
		logger.info("Pixy Vision here, y'all.");
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
			int widthMax = -1;
			
			try {
				xBuf.put(	
						signatureSet
						.stream()
						.mapToInt(p -> p.Width).toArray());
				
				widthMax = Arrays.stream(xBuf.array()).max().getAsInt();
			}
			catch (Exception e) {
				logger.info(e.getMessage());
			}

			if (widthMax > 0) {
				List<Integer> xList = new ArrayList<Integer>();
//				List<Integer> modes = getModes(xArrays.stream(xBuf.array()).boxed().toArray());
//				logger.log(Level.INFO, "modes: " + modes);
				logger.log(Level.INFO, "max width: " + widthMax);
			}
		}
	}
	
	public static List<Integer> getModes(final List<Integer> numbers) {
		// https://stackoverflow.com/a/4191729
	    final Map<Integer, Long> countFrequencies = numbers.stream()
	            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

	    final long maxFrequency = countFrequencies.values().stream()
	            .mapToLong(count -> count)
	            .max().orElse(-1);

	    return countFrequencies.entrySet().stream()
	            .filter(tuple -> tuple.getValue() == maxFrequency)
	            .map(Map.Entry::getKey)
	            .collect(Collectors.toList());
	}
}

