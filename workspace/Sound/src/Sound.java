import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {
	public static void main(String[] args) throws InterruptedException{
		while(true){
			//beep forever
//		    Toolkit tk = Toolkit.getDefaultToolkit();
////		    Thread.sleep(200);
//		    tk.beep();
//			System.out.print("\007");
			AudioClip clip;
			try {
				clip = Applet.newAudioClip(new URL("file://Users/chaoran/Desktop/beep-01a.wav"));
				clip.play();
				Thread.sleep(200);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
