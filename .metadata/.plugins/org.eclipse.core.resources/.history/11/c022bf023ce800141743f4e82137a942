package third_task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Administrator implements Runnable{
	BlockingQueue<Gas> dispensers = new LinkedBlockingQueue<Gas>();//блокир массив колонок
	
	
	public Administrator() throws InterruptedException {
		dispensers.put(new Gas(1));
		dispensers.put(new Gas(2));
		dispensers.put(new Gas(3));
	}
	
	@Override
	public void run() {		
		while(!Thread.interrupted()){//будит потоки, которые заблокировались с этим объектом
			synchronized (this) {
				this.notify();
			}
		}
	}
	
	public synchronized Gas serve(int Amount, int Type){ //выдача колонки клиенту
		for(Gas d : dispensers){
			if (d.type == Type){
				return d;
			}
		}
		return null;
	}
	
	public synchronized void complain(Gas d){
		d.refill();
	}
}
