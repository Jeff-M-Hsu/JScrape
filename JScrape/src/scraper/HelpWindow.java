package scraper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class HelpWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public HelpWindow() {
		int width = 440;
		int height = 400;
		setBounds(200, 200, width, height);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(width/2-38, height-80, 60, 30);
		getContentPane().add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		okButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				try{
					dispose();
				} catch(Exception f){
					System.out.println("Catastrophic error");
				}
			}
		});;
		
		JLabel usage = new JLabel("Usage");
		usage.setBounds(width/2-28, 30, 40, 20);
		usage.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(usage);

		JLabel usageContent = new JLabel("<html><table><tr><td>Url</td><td>https://example.com/home</td></tr><br><br>"
				+ "<tr><td>XPath</td><td>div/ul[@class=entry]//li</td></tr><br><br>"
				+ "<tr><td>Filename</td><td>entries</td></tr><br><br>"
				+ "<tr><td>Result</td><td>entries.txt containing all list elements in the first unordered list with class value of 'entry' in the first div</td></tr><br><br></table></html>");
		usageContent.setVerticalAlignment(SwingConstants.TOP);
		usageContent.setBounds(width/2-208, 60, 400, 120);
		getContentPane().add(usageContent);
		
		JLabel directory = new JLabel("Home Directories");
		directory.setBounds(width/2-78, 200, 140, 20);
		directory.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(directory);
		
		JLabel directories = new JLabel("<html><table><tr><td>Windows</td><td>&lt;root&gt;\\Users\\&lt;username&gt;</td></tr><br><br>"
				+ "<tr><td>MacOS</td><td>&lt;root&gt;/Users/&lt;username&gt;</td></tr><br><br>"
				+ "<tr><td>Linux</td><td>/home/username</td></tr></table></html>");
		directories.setVerticalAlignment(SwingConstants.TOP);
		directories.setBounds(width/2-208, 230, 400, 120);
		getContentPane().add(directories);
	}
}
