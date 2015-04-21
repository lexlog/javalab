package third_task;

abstract class Resource {
	int type;//тип топлива
	final int max = 100;
	int curAmout = max;
	
	synchronized int drain(int amount){
		System.out.println("Dispenser "+type+", cur: "+curAmout+", asked: "+amount);
		
		if (curAmout >= amount){
			curAmout -= amount;
			return amount;//сколько смогли взять
		}
		int diff = curAmout;
		curAmout = 0;
		return diff;
	}
	
	synchronized void refill(){ //перезаправка колонки
		curAmout = max;
	}
}

public class Gas extends Resource{
	
	public Gas(int Type) throws InterruptedException{
		type = Type;
	}	
}