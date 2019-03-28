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
import java.io.File;
import java.io.FileWriter;

public class Scraper {

	private String url;
	private String xPath;
	private String filename;

	public Scraper(String url, String xPath, String filename){
		this.url = url;
		this.xPath = xPath;
		this.filename = filename;
	}

	public void scrape(){
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
		WebClient client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			if(!url.contains("https://")){
				String addHttps = "https://" + url;
				url = addHttps;
			}
			HtmlPage page = client.getPage(url);
			List<HtmlElement> list = page.getByXPath(xPath);
			File textFile = new File(System.getProperty("user.home"), filename + ".txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
			if(list.size() == 0){
				writer.write("Could not find anything under XPath: " + xPath);
			}
			else {
				for(int i = 0; i < list.size(); i++){
					writer.write(list.get(i).asText());
					writer.write(System.lineSeparator());
				}

				writer.close();
			}
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
