package third_test;

import java.util.concurrent.TimeUnit;

public class Utill {
	static public void pause(int ms){
		try {
			TimeUnit.MILLISECONDS.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}