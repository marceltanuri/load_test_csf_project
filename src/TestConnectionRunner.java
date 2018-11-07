
public class TestConnectionRunner implements Runnable {

	@Override
	public void run() {
		new TestConnection().test();
	}

}
