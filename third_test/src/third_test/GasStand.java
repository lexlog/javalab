package third_test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class GasStand implements Runnable {

	private static final int CAR_SWITCH_SPEED = 200;
	private static final int CAR_FILL_SPEED = 150;
	
	
	private String _name;
	private View _view;
	LinkedBlockingQueue<Car> _cars;
	private volatile Integer _fuelLvl=100;
	Semaphore semaphore;
	Semaphore semaphore2;

	public GasStand(String name) {
		this._name = name;
	}



	@Override
	public void run() {
		semaphore = new Semaphore(0);
		semaphore2 = new Semaphore(0);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						semaphore.acquire();
						while (get_fuelLvl() != 100) {
							Utill.pause(CAR_FILL_SPEED);
							System.out.println("refuel");
							inc_fuelLvl();
						}
						semaphore2.release();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		_cars = new LinkedBlockingQueue<Car>();
		while (true) {
			Utill.pause(10);
			while (!_cars.isEmpty()) {
				Utill.pause(CAR_SWITCH_SPEED);
				try {
					servise(_cars.peek());
					_cars.remove();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void servise(Car car) throws InterruptedException {
		while (car._currentGas != car._desiredGas) {
			Utill.pause(CAR_FILL_SPEED);
			if (get_fuelLvl() == 0) {
				semaphore.release();
				semaphore2.acquire();
			}
			dec_fuelLvl();
			car._currentGas++;
		}
	}

	public void display(View view) {
		this._view = view;
		view.drawGasStand(_cars, get_fuelLvl(), _name);
	}

	public void addCarToService(Car car) {
		_cars.add(car);
	}

	public Integer get_fuelLvl() {
		synchronized (_fuelLvl) {
			return _fuelLvl;
		}
	}

	public void set_fuelLvl(Integer _fuelLvl) {
		synchronized (_fuelLvl) {
			this._fuelLvl = _fuelLvl;
		}
	}

	public void dec_fuelLvl() {
		synchronized (_fuelLvl) {
			this._fuelLvl--;
		}
	}

	public void inc_fuelLvl() {
		synchronized (_fuelLvl) {
			this._fuelLvl++;
		}
	}

}