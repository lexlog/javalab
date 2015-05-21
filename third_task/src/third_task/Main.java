package third_task;

public class Main {

	public static void main(String[] args) throws Exception {
		Administrator adm = new Administrator();
		new Thread(adm).start();		
		for (int i = 0; i < 100; i++){//100 машин
			new Thread(new Car(adm)).start();
		}

	}

}

