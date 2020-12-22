package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;




public class ScrapeForSandPandDJIA {
	
	private String price;
	private String anotherPrice;
	
	
	/**
	 * gets SandP500 live data
	 * @return
	 * @throws IOException
	 */
	public String SandPReader() throws IOException{
		
		URL url = new URL("https://www.reuters.com/quote/.SPX");
		URLConnection urc = url.openConnection();
		urc.addRequestProperty("User-Agent", "Chrome");
		urc.setReadTimeout(5000);
		urc.setConnectTimeout(5000);
		
		
		
		InputStreamReader isr = new InputStreamReader(urc.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		
		String line = br.readLine();
		while(line != null) {
			if(line.contains("Latest Trade")) {
				int target = line.indexOf("Latest Trade");
				int deci = line.indexOf(".", target);
				price = line.substring(deci -5, deci + 3);

			}

			line = br.readLine();
		}
			
		return price;

	}
	
	/**
	 * Gets DJIA live data
	 * @return
	 * @throws IOException
	 */
	public String DJIAReader() throws IOException{
		
		URL url = new URL("https://www.reuters.com/quote/.DJI");
		URLConnection urc = url.openConnection();
		urc.addRequestProperty("User-Agent", "Chrome");
		urc.setReadTimeout(5000);
		urc.setConnectTimeout(5000);
		
		
		
		InputStreamReader isr = new InputStreamReader(urc.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		
		String line = br.readLine();
		while(line != null) {
			if(line.contains("Latest Trade")) {
				int target = line.indexOf("Latest Trade");
				int deci = line.indexOf(".", target);
				anotherPrice = line.substring(deci -6, deci + 3);

			}

			line = br.readLine();
		}
		
		
		return anotherPrice;
		
	}
	
	
}
