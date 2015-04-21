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
		
		d = adm.serve(gasNeeded, gasType);//берем номер колонки
		
		receivedAmount = d.drain(gasNeeded);//заливаем 
		System.out.println(id + ": Client drained "+receivedAmount+" of fuel");
					
		while (receivedAmount != gasNeeded){//если недолили
			gasNeeded = gasNeeded - receivedAmount;//сколько недолили
			System.out.println(id + ": Client needs "+gasNeeded+" more gas");
			adm.complain(d);//жалуемся
			System.out.println(id + ": Client complained to administrator");
				
			receivedAmount = d.drain(gasNeeded);//доливаем
			System.out.println(id + ": Client drained "+receivedAmount+" of fuel");		//если снова не долили, обратно в while	
		}
		
		System.out.println(id + ": Client served");
	}
}

