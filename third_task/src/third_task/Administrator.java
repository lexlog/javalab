package third_task;
public class Administrator implements Runnable{
	final int dispensersAmount = 3;
	Gas dispenser = new Gas();
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			//imitation of previous customer service delay
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (this) {
				this.notifyAll();
			}
		}
	}
	
	public Gas serve(){		
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dispenser;
		}
	}
	
	public void complain(Gas d){
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			d.refill();
		}
	}
}
