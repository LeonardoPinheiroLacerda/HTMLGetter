package util.get_html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Get {
	
	private String html;
	
	public  void setHtml(String link) {
		this.html = "";
		try {
			URL Url = new URL(link);
			BufferedReader in = new BufferedReader(new InputStreamReader(Url.openStream(), StandardCharsets.UTF_8));
			String line;
			while ((line = in.readLine()) != null) {
				this.html += line;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid Page.");
			this.html = null;
		}
	}
	
	public  String getHtml() {
		return this.html;
	}
	
}
