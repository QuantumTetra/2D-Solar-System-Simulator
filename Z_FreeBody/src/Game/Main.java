package Game;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Main {
	public static void main (String[]args) throws IOException, InterruptedException{
		boolean Quit = false;
		Exception e2 = new Exception();
		while(Quit == false){
			String endType = "";
			Game game = new Game(0);
			try{
				endType = game.playGame();
			}catch(Exception e){
				playSound("C:/Users/charl/workspace/Z_FreeBody/src/Game/world smallest violin.au");
				endType = "Quit";
				TimeUnit.SECONDS.sleep(12);
				e2 = e;
			}
			//System.out.println(endType);
			if(endType.equals("Quit"))
				Quit = true;
			
		}
		e2.printStackTrace();
		System.exit(0);
	}
	public static void playSound(String fileName){
		try {
		    File yourFile = new File(fileName);
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}

