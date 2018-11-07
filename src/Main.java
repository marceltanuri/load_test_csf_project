import java.util.logging.Logger;

public class Main {

	public static void main(String a[]) throws InterruptedException {

		 final Logger LOGGER = Logger.getLogger(Main.class.getName());
		
		int count = 0;
		while (count < 150) {
			LOGGER.info("Initializing load test...");
			count++;
			new TestConnectionRunner().run();
			Thread.sleep(1000);
		}
		LOGGER.info("Connections took " + LoadingTimeCounter.getAvarageLoadingTime() + " millisecounds on avarage.");
	}

}
