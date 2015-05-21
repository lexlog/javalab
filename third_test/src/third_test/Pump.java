package third_test;

public class Pump {
	int number;
	
	int curAmout = 100;//текущее количество топлива в заправке

	public Pump (int n) {
		number=n;
		System.out.println("Pump №"+number+" is ready");
	}
	
	public int drain (int gasNeeded) {		
		if (curAmout >= gasNeeded){ //если попросил меньше, чем осталось  
			System.out.println("Pump"+number+": Current oil: "+curAmout+" Given: "+gasNeeded);
			curAmout -= gasNeeded;
			return gasNeeded;
		}
		int diff = curAmout;
		System.out.println("Pump"+number+": Current oil: "+curAmout+" Given: "+curAmout);
		curAmout = 0;
		return diff;//иначе выдаем что осталось
	}
	
	public void refill() {
		curAmout=100;
	}
}
