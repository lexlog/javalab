package third_task;

abstract class Resource {
	int type;//тип топлива
	final int max = 100;//максимум 100
	int curAmout = max;//текущее количество топлива в заправке
	
	synchronized int drain(int amount){
		System.out.println("Dispenser "+type+", cur: "+curAmout+", asked: "+amount);
		
		if (curAmout >= amount){ //если попросил меньше, чем 100 
			curAmout -= amount;
			return amount;//выдаем
		}
		int diff = curAmout;
		curAmout = 0;
		return diff;//иначе выдаем весь ресурс колонки
	}
	
	synchronized void refill(){  //перезаполнение колонки, если не хватило
		curAmout = max;
	}
}

public class Gas extends Resource{
	
	public Gas(int Type) throws InterruptedException{
		type = Type; //тип топлива
	}	
}
