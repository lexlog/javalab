package third_test;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

public interface View {

	public void drawController(Collection<?> _cars);

	public void drawGasStand(LinkedBlockingQueue<Car> _cars, Integer _fuelLvl,
			String _name);

}
