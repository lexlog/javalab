package third_task;

import java.util.Random;


public class Car implements Runnable{
	int gasNeeded = (new Random()).nextInt(110)+10; 
	final int id = gasNeeded; // различаем клиентов по количеству топлива
	final int gasType = (new Random()).nextInt(3) + 1;
	Administrator adm;
	
	public Car(Administrator a){
		System.out.println(id+ ": New client, needed: "+gasNeeded+" of "+gasType+" type");
		adm = a;
	}

	@Override
	public void run() {
		Gas d;//номер колонки
		int receivedAmount;
		
		d = adm.serve(gasNeeded, gasType);//получаем номер колонки
		
		receivedAmount = d.drain(gasNeeded);//сколько реально получили
		System.out.println(id + ": Client drained "+receivedAmount+" of fuel");
					
		while (receivedAmount != gasNeeded){ //пока не получили сколько надо
			gasNeeded = gasNeeded - receivedAmount;
			System.out.println(id + ": Client needs "+gasNeeded+" more gas");
			adm.complain(d); //жалуемся, что не долили
			System.out.println(id + ": Client complained to administrator");
				
			receivedAmount = d.drain(gasNeeded);
			System.out.println(id + ": Client drained "+receivedAmount+" of fuel");		
		}
		
		System.out.println(id + ": Client served");
	}
}

