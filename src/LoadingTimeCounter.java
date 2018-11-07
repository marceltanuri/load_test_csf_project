import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoadingTimeCounter {

	private static List<Long> loadingTimes;

	public static synchronized void addLoadTime(Long e) {
		if (loadingTimes == null) {
			loadingTimes = new ArrayList<Long>();
		}
		loadingTimes.add(e);
	}

	public static long getAvarageLoadingTime() {
		if (loadingTimes != null && loadingTimes.size() > 0) {
			Iterator<Long> iterator = loadingTimes.iterator();
			long sumLoadingTime = 0L;
			while (iterator.hasNext()) {
				sumLoadingTime = sumLoadingTime + iterator.next();
			}
			return sumLoadingTime / loadingTimes.size();
		}
		return 0;
	}
}
