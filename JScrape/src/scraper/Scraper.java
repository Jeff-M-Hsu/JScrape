package scraper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Scraper {
		public static void main(String[] args){
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
			java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
			WebClient client = new WebClient(BrowserVersion.CHROME);
			client.getOptions().setJavaScriptEnabled(false);
			try {
				HtmlPage page = client.getPage("https://www.pickuplinesbest.com/bee-pickup-lines/");
				List<HtmlElement> list = page.getByXPath("//blockquote//p");
				BufferedWriter writer = new BufferedWriter(new FileWriter("pickups.txt"));
				for(int i = 0; i < list.size(); i++){
					writer.write(list.get(i).asText());
					writer.write(System.lineSeparator());
				}
				writer.close();

			} catch (FailingHttpStatusCodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			client.close();
		}
}
