

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class TestConnection {
	
	 private static final int TIMEOUT = 5000;
	final Logger LOGGER = Logger.getLogger(TestConnection.class.getName());

	public void test() {
		long beginTime = System.currentTimeMillis();
		try {
			HttpsURLConnection con;
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxycsf", 8080));
			con = (HttpsURLConnection) new URL("https://www.google.com/recaptcha/api/siteverify").openConnection(proxy);

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setConnectTimeout(TIMEOUT);

			con.setRequestProperty("Request-Method", "POST");

			String secretKey = "teste"+System.currentTimeMillis();
			String idRobot = "teste"+System.currentTimeMillis();

			String postParams = "secret=" + secretKey + "&response=" + idRobot;

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			long endTime = System.currentTimeMillis();
			LOGGER.info("connected in " + (endTime - beginTime) + " millisecounds");

		} 
		
		catch(java.net.SocketTimeoutException tmex) {
			LOGGER.severe(TIMEOUT + " millisecounds timeout exceded!");
		}
		
		catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
			ex.printStackTrace();
		}
		finally {
			long endTime = System.currentTimeMillis();
			LoadingTimeCounter.addLoadTime(endTime - beginTime);
		}
	}
}
