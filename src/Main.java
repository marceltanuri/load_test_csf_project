import java.util.logging.Logger;

public class Main {
	
	public static void main(String a[]) throws InterruptedException {
		
		final Logger LOGGER = Logger.getLogger(Main.class.getName());
		
		int count = 0;
		LOGGER.info("Initializing load test...");
		
		while (count < 150) {
			count++;
			new TestConnectionRunner().run();
			Thread.sleep(1000);
		}
		
		LOGGER.info("Connections took " + LoadingTimeCounter.getAvarageLoadingTime() + " millisecounds on avarage.");
	}
	
}