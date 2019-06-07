package Game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.*;

import javax.imageio.ImageIO;

public class Game {

	public int fileNum;
	public String endType = "";
	public int tps = 125;
	public double timeStep = tps;
	public double timeMultiplier = 1.0;
	public int pauseTime = 1000/tps;
	public double G = 6.674*Math.pow(10, -11);
	public Picture p = new Picture("Solar System",1920,1080);
	public boolean escActive = false;
	public double screenSizeParameter = 13659489448.14815;
	public int ticks = 0;
	public int trackingNum = 0;
	public boolean tracking = false;
	public ArrayList<Star> star = new ArrayList<>();
	public ArrayList<Planet> planet = new ArrayList<>();
	public ArrayList<Ship> ship = new ArrayList<>();
	public double cameraX = 0;
	public double cameraY = 0;
	public boolean exitAdd = false;
	public Panel panel = new Panel ();
	
	public TextField tName = new TextField("");
	public TextField tMass = new TextField("0");
	public TextField tRadius = new TextField("0");
	public TextField tX = new TextField("0");
	public TextField tY = new TextField("0");
	public TextField tVX = new TextField("0");
	public TextField tVY = new TextField("0");
	public TextField tImageType = new TextField("moon");
	public TextField tRemoveNum = new TextField("0");
	public TextField tRot = new TextField("0");
	public Label lWorked = new Label ("");
	
	public Game (){
		fileNum = 0;
	}

	public Game (int filenum){
		fileNum = filenum;
	}

	public String playGame() throws IOException{ //actual Game ---------------------------------------------------------------------------------------------------------------------------------------------
		p.addKeyListener(new KeycheckerEsc2(this));
		p.addKeyListener(new KeycheckerArrow1(this));
		p.addKeyListener(new KeycheckerArrow2(this));
		p.addKeyListener(new Keycheckeri1 (this));
		p.addKeyListener(new Keycheckert1 (this));
		p.addKeyListener(new KeycheckerUp1 (this));
		p.addKeyListener(new KeycheckerDown1 (this));
		p.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		
		Figure mid = new Figure (960,540);
		mid.draw(960, 0);
		mid.setColor(new Color (255,255,255));
		
		Figure background = new Figure (0,0);
		background.draw(1940, 0);
		background.draw(0, 1090);
		background.draw(-1940, 0);
		background.close();
		background.setFilled(true);
		p.add(background);
		double secondsPassed = 0;
		double minutesPassed = 0;
		double hoursPassed = 0;
		double daysPassed = 0;
		double yearsPassed = 0;
		
		p.add(mid);
		

		//System.out.println(s);
		//test adds

		star.add(new Star (0.0,0.0,1.9891*Math.pow(10,30),ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/sun.png"))));
		
		

		planet.add(new Planet ("Earth",6378.1*1000.0,149600000000.0,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/149600000000.0-1.0/149600000000.0)),5.9736*Math.pow(10, 24),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/earth.png"))));//earth

		planet.add(new Planet("Mercury",2439.7*1000.0, 57910000000.0,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/57910000000.0-1.0/57910000000.0)),3.285*Math.pow(10, 23),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/mercury.png"))));//mercury
		
		planet.add(new Planet ("Luna" ,1738.1*1000.0,149600000000.0+360485000.0,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/(149600000000.0+360485000.0)-1.0/(149600000000.0+360485000.0)))-Math.sqrt(G*planet.get(0).getMass()*(2.0/(360485000.0)-1.0/(360485000.0))),7.34767309*Math.pow(10, 22),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/moon.png"))));//moon
		
		planet.add(new Planet ("Venus",6051.8*1000.0,109000000000.0,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/109000000000.0-1.0/109000000000.0)),4.86732*Math.pow(10, 24),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/venus.png"))));//venus
		
		double tempDist = 8.157*Math.pow(10, 11);
		planet.add(new Planet ("Jupiter",71492*1000.0,tempDist,0.0,0.0,0,1.89813*Math.pow(10, 27),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/jupiter.png"))));//jupiter
		double moonDist  = 421700000.0; //Math.sqrt(G*star.get(0).getMass()*(2.0/tempDist-1.0/tempDist))
		planet.add(new Planet ("Io",1815000.0,tempDist+moonDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/(tempDist+moonDist)-1.0/(tempDist+moonDist)))-Math.sqrt(G*planet.get(4).getMass()*(2.0/(moonDist)-1.0/(moonDist))),8931900.0*Math.pow(10, 16),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/io.png"))));//io
		moonDist  = 1070412000.0;
		planet.add(new Planet ("Ganymede",2634000.0,tempDist+moonDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/(tempDist+moonDist)-1.0/(tempDist+moonDist)))-Math.sqrt(G*planet.get(4).getMass()*(2.0/(moonDist)-1.0/(moonDist))),14819000.0*Math.pow(10, 16),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/ganymede.png"))));//ganymede
		moonDist  = 1882709000.0;
		planet.add(new Planet ("Callisto",2403000.0,tempDist+moonDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/(tempDist+moonDist)-1.0/(tempDist+moonDist)))-Math.sqrt(G*planet.get(4).getMass()*(2.0/(moonDist)-1.0/(moonDist))),10759000.0*Math.pow(10, 16),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/callisto.png"))));//callisto
		moonDist  = 671034000.0;
		planet.add(new Planet ("Europa",1565000.0,tempDist+moonDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/(tempDist+moonDist)-1.0/(tempDist+moonDist)))-Math.sqrt(G*planet.get(4).getMass()*(2.0/(moonDist)-1.0/(moonDist))),4800000.0*Math.pow(10, 16),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/europa.png"))));//europa
		
		
		
		tempDist = 7376124302.0*1000.0;
		double semiMajor = 5906440628.0*1000.0;
		planet.add(new Planet ("Pluto",1195*1000.0,tempDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/tempDist-1.0/semiMajor)),1.30900*Math.pow(10, 22),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/pluto.png"))));//pluto
		
		tempDist = 249232432.0*1000.0;
		semiMajor = (249232432000.0+206655215000.0)/2.0;
		planet.add(new Planet ("Mars",3396.2*1000.0,tempDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/tempDist-1.0/semiMajor)),6.39*Math.pow(10, 23),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/mars.png"))));//mars
		
		tempDist = 1503509229.0*1000.0;
		semiMajor = (tempDist+1349823615000.0)/2.0;
		planet.add(new Planet ("Saturn",60268*1000.0,tempDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/tempDist-1.0/semiMajor)),5.683*Math.pow(10, 26),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/saturn.png"))));//saturn
		
		tempDist = 3006318143.0*1000.0;
		semiMajor = (tempDist+2734998229000.0)/2.0;
		planet.add(new Planet ("Uranus",25559*1000.0,tempDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/tempDist-1.0/semiMajor)),8.681*Math.pow(10, 25),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/uranus.png"))));//uranus
		
		tempDist = 4537039826.0*1000.0;
		semiMajor = (tempDist+4459753056000.0)/2.0;
		planet.add(new Planet ("Neptune",24764*1000.0,tempDist,0.0,0.0,Math.sqrt(G*star.get(0).getMass()*(2.0/tempDist-1.0/semiMajor)),1.024*Math.pow(10, 26),0,ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/neptune.png"))));//neptune
		
		//test astroid to hit earth
		//planet.add(new Planet ("Star of Death",6.957*Math.pow(10, 7)*1708.0,7376124302.0*1000.0+1000.0,0.0,-100000.0,10000.0,2*Math.pow(10, 32),ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/sun.png"))));//callisto
		
		//System.out.println("Earth velocity: "+Math.sqrt(G*star.get(0).getMass()*(2.0/149600000000.0-1.0/149600000000.0)));
		//System.out.println("Mercury velocity: "+Math.sqrt(G*star.get(0).getMass()*(2.0/57910000.0-1.0/57910000.0)));

		// Create a new blank cursor.
		/*Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		// Set the blank cursor to the JFrame.
		p.setCursor(blankCursor);
		//adding images to the Picture*/
		//p.addImageToList(ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/background.png")), 0, 0);
		for(int c = 0; c<star.size();c++){
			p.addImageToList(star.get(c).getImage(), star.get(c).getX()/screenSizeParameter+960.0-(star.get(c).getImage().getWidth(null)/2.0), star.get(c).getY()/screenSizeParameter+540.0-(star.get(c).getImage().getHeight(null)/2.0));
		}
		for(int c = 0; c<planet.size();c++){
			p.addImageToList(planet.get(c).getImage(), planet.get(c).getX()/screenSizeParameter+960.0-(planet.get(c).getImage().getWidth(null)/2.0), planet.get(c).getY()/screenSizeParameter+540.0-(planet.get(c).getImage().getHeight(null)/2.0));
		}
		/*for(int c = 0; c<ShipImages.size();c++){
			p.addImageToList(ShipImages.get(c), sx.get(c)/screenSizeParameter+960.0, sy.get(c)/screenSizeParameter+540.0);
		}*/

		boolean EXIT = false;
		boolean escPressed = false;
		int escTimer=0;
		FPScounter f = new FPScounter();
		String FPS = "0";
		while(endType.equals("")){
			ticks++;
			p.requestFocus();
			secondsPassed+=1*timeMultiplier/timeStep;
			minutesPassed=secondsPassed/60;
			hoursPassed=minutesPassed/60;
			//for test breaking
			//int x23 = (int)minutesPassed/0;
			
			daysPassed=hoursPassed/24;
			yearsPassed = daysPassed/365.25;
			
			
			if(ticks%5==0)
				f.StartCounter();
			p.resetOSB();
			if(trackingNum >= planet.size())
				trackingNum = planet.size()-1;
			if(tracking == true){
				screenSizeParameter = 3486498.1481481481481481481481481;
				if(planet.size()>trackingNum){
					double tempx = planet.get(trackingNum).getX();
					double tempy = planet.get(trackingNum).getY();
					cameraX = tempx / screenSizeParameter*-1.0;
					cameraY = tempy / screenSizeParameter*-1.0;
				}else{
					tracking = false;
				}
				
			}else{
				cameraX = 0;
				cameraY = 0;
			}
			Figure MWF = new Figure (100,100);
			MWF.drawString(FPS, 0, 0);
			MWF.drawString(""+timeMultiplier, 0, 15);
			MWF.drawString("", 0, 30);
			MWF.drawString("Seconds: "+secondsPassed, 0, 45);
			MWF.drawString("Minutes: "+minutesPassed, 0, 60);
			MWF.drawString("Hours: "+hoursPassed, 0, 75);
			MWF.drawString("Days: "+daysPassed, 0, 90);
			MWF.drawString("Years: "+yearsPassed, 0, 105);
			
			MWF.drawString("Name: "+planet.get(trackingNum).getName(), 1600, 0);
			MWF.drawString("Mass: "+planet.get(trackingNum).getMass(), 1600, 15);
			MWF.drawString("Radius: "+planet.get(trackingNum).getRadius(), 1600, 30);
			MWF.drawString("X: "+planet.get(trackingNum).getX(), 1600, 45);
			MWF.drawString("Y: "+planet.get(trackingNum).getY(), 1600, 60);
			MWF.drawString("VX: "+planet.get(trackingNum).getVX(), 1600, 75);
			MWF.drawString("VY: "+planet.get(trackingNum).getVY(), 1600, 90);
			MWF.drawString("VT: "+Math.sqrt(Math.pow(planet.get(trackingNum).getVX(), 2)+Math.pow(planet.get(trackingNum).getVY(), 2)), 1600, 105);
			MWF.setColor(new Color (255,255,255));
			p.add(MWF);
			for(int c = 0; c<star.size();c++){
				p.setAnImage(star.get(c).getImage(),c, star.get(c).getX()/screenSizeParameter+960.0-(star.get(c).getImage().getWidth(null)/2.0)+cameraX, star.get(c).getY()/screenSizeParameter+540.0-(star.get(c).getImage().getHeight(null)/2.0)+cameraY);
			}
			for(int c = 0; c<planet.size();c++){
				p.setAnImage(planet.get(c).getImage(),c+star.size(), planet.get(c).getX()/screenSizeParameter+960.0-(planet.get(c).getImage().getWidth(null)/2.0)+cameraX, planet.get(c).getY()/screenSizeParameter+540.0-(planet.get(c).getImage().getHeight(null)/2.0)+cameraY);
			}
			/*for(int c = 0; c<ShipImages.size();c++){
				p.addImageToList(ShipImages.get(c), sx.get(c)/screenSizeParameter+960.0, sy.get(c)/screenSizeParameter+540.0);
			}*/
			//YOU NEED TO ADD UP ALL THE ACCELERATION AND THEN APPLY IT!
			//NOT ONE AT A TIME
			if(escPressed==true){
				escTimer++;
			}
			if(escTimer==10){
				escPressed=false;
				escTimer=0;
			}
			if(escActive==true&&escTimer==0){
				escPressed=true;
				PauseMenu();
			}
			for( Star S : star){
				for(Planet P: planet){
					deltaV D = calcGrav(S.getX()-P.getX(),S.getY()-P.getY(),S.getMass());
					P.setVX(P.getVX()+D.getDX());
					P.setVY(P.getVY()+D.getDY());
					//System.out.println("\ntick: "+ticks+"\n\nPlanet "+planet.indexOf(P)+"\n Is accelerated by: \n"+D.getDX()+"\n"+D.getDY()+"\nThe planets speed is: "+ Math.sqrt(Math.pow(D.getDX(), 2)+Math.pow(D.getDY(),2)));
				}
			}
			for( Planet P : planet){
				for(Planet P2: planet){
					if(planet.indexOf(P)!=planet.indexOf(P2)){
						deltaV D = calcGrav(P.getX()-P2.getX(),P.getY()-P2.getY(),P.getMass());
						P2.setVX(P2.getVX()+D.getDX());
						P2.setVY(P2.getVY()+D.getDY());
					}
				}
			}

			//move planets

			for(Planet P: planet){
				//System.out.println("tick: "+ticks+"\n\n Planet "+planet.indexOf(P)+":\nX: "+P.getX()+"\nY: "+P.getY()+"\nVX: "+P.getVX()+"\nVY: "+P.getVY());
				P.setX(P.getX()+(P.getVX()/timeStep*timeMultiplier));
				P.setY(P.getY()+(P.getVY()/timeStep*timeMultiplier));
			}
			
			ArrayList<Integer> checked = new ArrayList<>();
			ArrayList<Delete> delete = new ArrayList<>();
			for(Planet P : planet){
				for(Planet P2 : planet){
					if(planet.indexOf(P)!=planet.indexOf(P2) && !isPartOf(checked, planet.indexOf(P2))){
						double tempDist2 = Math.sqrt(Math.pow(P.getX()-P2.getX(), 2)+Math.pow(P.getY()-P2.getY(), 2));
						if(tempDist2<=(P.getRadius()+P2.getRadius())){
							delete.add(new Delete(P,P2));
						}
					}
				}
				checked.add(planet.indexOf(P));
			}
			
			//creating new planet, removing old one
			for(Delete d : delete){
				Planet t1 = d.getPlanet1();
				Planet t2 = d.getPlanet2();
				double newSpeedX = ((t1.getVX()*t1.getMass())+(t2.getVX()*t2.getMass()))/(t1.getMass()+t2.getMass());
				double newSpeedY = ((t1.getVY()*t1.getMass())+(t2.getVY()*t2.getMass()))/(t1.getMass()+t2.getMass());
				double newMass = t1.getMass()+t2.getMass();
				double newX = 0;
				double newY = 0;
				double newRot = 0;
				Image newI = ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/moon.png"));
				if(t1.getMass()>=t2.getMass()){
					newX = t1.getX();
					newY = t1.getY();
					newI = t1.getImage();
					newRot = t1.getRot();
				}else{
					newX = t2.getX();
					newY = t2.getY();
					newI = t2.getImage();
					newRot = t2.getRot();
				}
				String newName = t1.getName()+" and "+t2.getName();
				double newRad = t1.getRadius()+t2.getRadius();
				p.removeImageFromList(planet.indexOf(t1));
				planet.remove(planet.indexOf(t1));
				p.removeImageFromList(planet.indexOf(t2));
				planet.remove(planet.indexOf(t2));
				planet.add(new Planet(newName,newRad,newX,newY,newSpeedX,newSpeedY,newMass,newRot,newI));
				p.addImageToList(planet.get(planet.size()-1).getImage(), 0, 0);
			}

			p.pause(pauseTime);
			p.remove(MWF);
			if(ticks%5==0)
				FPS = ""+f.StopAndPost();
		}
		return endType;
	}

	public  deltaV calcGrav(double x1_minus_x2 ,double y1_minus_y2 ,double mass){
		double angle = Math.atan2((y1_minus_y2*-1.0),(x1_minus_x2*-1.0));
		double deltaX = ((G*(mass/Math.pow(Math.sqrt((Math.pow(x1_minus_x2, 2)+Math.pow(y1_minus_y2, 2))),2) ))*Math.cos(angle))/timeStep*timeMultiplier;
		double deltaY = ((G*(mass/Math.pow(Math.sqrt((Math.pow(x1_minus_x2, 2)+Math.pow(y1_minus_y2, 2))),2) ))*Math.sin(angle))/timeStep*timeMultiplier;
		deltaX*=-1.0;
		deltaY*=-1.0;
		return new deltaV(deltaX, deltaY);
	}
	
	public boolean isPartOf(ArrayList<Integer> checked, int num){
		boolean ans = false;
		for (int i : checked){
			if(i == num){
				ans = true;
			}
		}
		return ans;
	}
	
	
	public void PauseMenu() throws IOException{
		boolean unpressed = false;
		boolean exit = false;
		boolean deactivate = false;
		Button b = new Button ("Quit");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endType = "Quit";
			}
		});
		Button b2 = new Button ("Restart");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endType = "Restart";
			}
		});
		Button b3 = new Button ("Add Planet");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					planet.add(new Planet(tName.getText(),Double.parseDouble(tRadius.getText()),Double.parseDouble(tX.getText()),Double.parseDouble(tY.getText()),Double.parseDouble(tVX.getText()),Double.parseDouble(tVY.getText()),Double.parseDouble(tMass.getText()),Double.parseDouble(tRot.getText()),ImageIO.read(new File("C:/Users/charl/workspace/Z_FreeBody/src/Game/"+tImageType.getText()+".png"))));
					p.addImageToList(planet.get(planet.size()-1).getImage(), 0.0, 0.0);
					lWorked.setText("Added new planet");
				}catch(Exception e2){
					lWorked.setText("Failed to add planet");
				}
			}
		});
		Button b4 = new Button ("Remove Planet");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(Integer.parseInt(tRemoveNum.getText())<planet.size()){
						planet.remove(Integer.parseInt(tRemoveNum.getText()));
						p.removeImageFromList(Integer.parseInt(tRemoveNum.getText()));
					}
				}catch (Exception e2){
				}
			}
		});
		Button b5 = new Button ("Resume");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escActive = true;
			}
		});
		
		Label lName  = new Label("Name:");
		Label lMass  = new Label("Mass (Kg):");
		Label lRadius  = new Label("Radius (m)");
		Label lX  = new Label("X position (m)");
		Label lY  = new Label("Y position (m)");
		Label lVX  = new Label("X velocity (m/s)");
		Label lVY  = new Label("Y velocity (m/s)");
		Label lImageType  = new Label("Input the Image type (a non capitalized name of an implemented planet");
		Label lRot = new Label ("Rotation per 1/125 seconds in radians: ");
		
		Label lRemoveNum = new Label("Input the index of the planet you would like to remove (The last planet's number is : "+(planet.size()-1)+"): ");
		GridLayout i = new GridLayout();
		i.setColumns(3);
		i.setRows(20);
		panel.setLayout(i);
		panel.add(b);
		panel.add(b2);
		panel.add(b5);
		panel.add(new Label(""));
		panel.add(new Label(""));
		panel.add(new Label(""));
		
		//add things for the planet adder
		panel.add(lName);
		panel.add(tName);
		panel.add(lMass);
		panel.add(tMass);
		panel.add(lRadius);
		panel.add(tRadius);
		panel.add(lX);
		panel.add(tX);
		panel.add(lY);
		panel.add(tY);
		panel.add(lVX);
		panel.add(tVX);
		panel.add(lVY);
		panel.add(tVY);
		panel.add(lRot);
		panel.add(tRot);
		panel.add(lImageType);
		panel.add(tImageType);
		panel.add(b3);
		panel.add(new Label(""));
		panel.add(lWorked);
		panel.add(new Label(""));
		
		//for planet removal
		panel.add(lRemoveNum);
		panel.add(tRemoveNum);
		panel.add(b4);
		b.setForeground(new Color(200,0,0));
		b2.setForeground(new Color(200,0,0));
		b3.setForeground(new Color(200,0,0));
		b4.setForeground(new Color(200,0,0));
		b5.setForeground(new Color(200,0,0));
		//ImageIcon cursorAim = new ImageIcon("C:/Users/charl/workspace/SaberGame/src/Cursor1.png");
		panel.setBackground(new Color(100,100,100));
		p.add(panel);
		p.setVisible(true);
		while (exit==false){
			lRemoveNum.setText("Input the index of the planet you would like to remove (The last planet's number is : "+(planet.size()-1)+"): ");
			if(deactivate==false&&escActive==false){
				unpressed=true;
				deactivate = true;
			}
			if((escActive==true&&unpressed==true)||!endType.equals("")){
				exit =true;
				p.remove(panel);
				panel.removeAll();
				escActive = false;
			}
			p.pause(50);
		}
		tName.setText("");
		tRadius.setText("0");
		tMass.setText("0");
		tX.setText("0");
		tY.setText("0");
		tVX.setText("0");
		tVY.setText("0");
		tImageType.setText("moon");
		tRemoveNum.setText("0");
		lWorked.setText("");
	}

	public void change_escActive (boolean ch){
		escActive= ch;
	}
	public void increaseTime(){
		if(timeMultiplier<1*Math.pow(10, 6)){
			timeMultiplier*=10.0;
		}
	}
	public void decreaseTime(){
		if(timeMultiplier>1)
		timeMultiplier*=0.1;
	}
	public void trackingChange (){
		if(tracking==true){
			tracking = false;
		}else{
			tracking = true;
		}
		
	}
	public void setScreenSize(){
		if(screenSizeParameter<461541540.7407407+1.0){
			screenSizeParameter=13659489448.14815;
		}else{
			screenSizeParameter=461541540.7407407;
		}
	}
}
class KeycheckerEsc2 extends KeyAdapter { //ESC
	Game m;
	public KeycheckerEsc2 (Game o){
		m=o;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		char ch = event.getKeyChar();
		if(ch == '')
			m.change_escActive(true);
	}
	public void keyReleased(KeyEvent event){
		m.change_escActive(false);
	}
}
class KeycheckerArrow1 extends KeyAdapter { //ESC
	Game m;
	public KeycheckerArrow1 (Game o){
		m=o;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		char ch = event.getKeyChar();
		if(ch == ',')
			m.decreaseTime();
	}
	public void keyReleased(KeyEvent event){
		if(event.getKeyChar() == ',');
	}
}
class KeycheckerArrow2 extends KeyAdapter { //ESC
	Game m;
	public KeycheckerArrow2 (Game o){
		m=o;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		char ch = event.getKeyChar();
		if(ch == '.')
			m.increaseTime();
	}
	public void keyReleased(KeyEvent event){
		if(event.getKeyChar() == ',');
	}
}
class Keycheckeri1 extends KeyAdapter { //ESC
	Game m;
	public Keycheckeri1 (Game o){
		m=o;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		char ch = event.getKeyChar();
		if(ch == 'i')
			m.setScreenSize();
	}
	public void keyReleased(KeyEvent event){
		if(event.getKeyChar() == 'i');
	}
}
class Keycheckert1 extends KeyAdapter { //ESC
	Game m;
	public Keycheckert1 (Game o){
		m=o;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		char ch = event.getKeyChar();
		if(ch == 't'){
			m.trackingChange();
			m.setScreenSize();
		}
	}
	public void keyReleased(KeyEvent event){
		if(event.getKeyChar() == 't');
	}
}
class KeycheckerUp1 extends KeyAdapter { //ESC
	Game m;
	public KeycheckerUp1 (Game o){
		m=o;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		int ch = event.getKeyCode();
		if(ch == 38){
			if(m.trackingNum<m.planet.size()-1){
				m.trackingNum++;
			}
		}
	}
	public void keyReleased(KeyEvent event){
		if(event.getKeyChar() == 't');
	}
}
class KeycheckerDown1 extends KeyAdapter { //ESC
	Game m;
	public KeycheckerDown1 (Game o){
		m=o;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		int ch = event.getKeyCode();
		if(m.trackingNum != 0&&ch==40){
			m.trackingNum--;
		}
	}
	public void keyReleased(KeyEvent event){
		if(event.getKeyChar() == 't');
	}
}