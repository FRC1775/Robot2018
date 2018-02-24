package org.usfirst.frc.team1775.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;


public class VisionProcessor extends Subsystem {


	// These values are the default if you instantiate a PixySPI without arguments.
	// To create multiple PixySPI objects and thus use multiple Pixy cameras via SPI
	// Copy the items below, change variable names as needed and especially change
	// the SPI port used eg; Port.kOnboardCS[0-3] or Port.kMXP
	public PixySPI pixy1;
	Port port = Port.kOnboardCS0;
	String print;
	public HashMap<Integer, ArrayList<PixyPacket>> packets = new HashMap<Integer, ArrayList<PixyPacket>>();
	private HashMap<String, ArrayList<Integer>> pPackets;
	
	
	public VisionProcessor() {
		// Open a pipeline to a Pixy camera.
		pixy1 = new PixySPI(new SPI(port), packets, new PixyException(print));
		System.out.println("Pixy Vision here, y'all.");
		
		pPackets = new HashMap<String, ArrayList<Integer>>();
		pPackets.put("x", new ArrayList<Integer>());
		pPackets.put("y", new ArrayList<Integer>());
		pPackets.put("w", new ArrayList<Integer>());
		pPackets.put("h", new ArrayList<Integer>());
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void readPixy(){
		int ret = -1;
		// Get the packets from the pixy.
		try {
			ret = pixy1.readPackets();
		} catch (PixyException e) {
			// TODO Auto-generated catch block
			System.out.println("No packets from Pixy!");
			e.printStackTrace();
		}
		if (ret == -1) { return; }
		
		for (int i = 0; i < packets.keySet().size(); i++) {
			
			ArrayList<PixyPacket> signatureSet = packets.get(i);
			if (signatureSet == null) { return; }
			pPackets = pivot(signatureSet);	
			double maxX = getMax(pPackets.get("x"));
			System.out.println(maxX);
		}	
	}
	
	private HashMap<String, ArrayList<Integer>> pivot(ArrayList<PixyPacket> sSet) {
		ArrayList<Integer> Xs = new ArrayList<Integer>();
		ArrayList<Integer> Ys = new ArrayList<Integer>();
		ArrayList<Integer> Ws = new ArrayList<Integer>();
		ArrayList<Integer> Hs = new ArrayList<Integer>();
		
		for (PixyPacket p: sSet) {
			Xs.add(p.X);
			Ys.add(p.Y);
			Ws.add(p.Width);
			Hs.add(p.Height);
		}
			
		pPackets.put("x", Xs);
		pPackets.put("y", Ys);
		pPackets.put("w", Ws);
		pPackets.put("h", Hs);
		
		return pPackets;
	}
	
	
	
	private double getMax(ArrayList<Integer> intSet) {
		int currentMax = 0;
		for (Integer i: intSet)
			if (i > currentMax) { currentMax = i; }
		return currentMax;
	}
	
}

