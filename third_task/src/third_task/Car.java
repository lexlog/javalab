package third_task;

import java.util.Random;


public class Car implements Runnable{
	int gasNeeded = (new Random()).nextInt(150)+10;
	final int id = gasNeeded;
	final int gasType = (new Random()).nextInt(3) + 1;
	Administrator adm;
	
	public Car(Administrator a){
		System.out.println(id+ ": New client, needed: "+gasNeeded+" of "+gasType+" type");
		adm = a;
	}

	@Override
	public void run() {
		Gas d;
		int receivedAmount;
		
		d = adm.serve(gasNeeded, gasType);
		
		receivedAmount = d.drain(gasNeeded);
		System.out.println(id + ": Client drained "+receivedAmount+" of fuel");
					
		while (receivedAmount != gasNeeded){
			gasNeeded = gasNeeded - receivedAmount;
			System.out.println(id + ": Client needs "+gasNeeded+" more gas");
			adm.complain(d);
			System.out.println(id + ": Client complained to administrator");
				
			receivedAmount = d.drain(gasNeeded);
			System.out.println(id + ": Client drained "+receivedAmount+" of fuel");		
		}
		
		System.out.println(id + ": Client served");
	}
}

