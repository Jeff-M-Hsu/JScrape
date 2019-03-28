package scraper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(HelpWindow.class.getResource("/resources/icon.png")));
		setTitle("How to use JScrape");
		
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
		
		JLabel usage = new JLabel("<html><h3><u>Usage</html>");
		usage.setBounds(width/2-34, 30, 52, 20);
		usage.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(usage);
		
		JTextPane usageContent = new JTextPane();
		usageContent.setContentType("text/html");
		usageContent.setText("<html><head><style type=\"text/css\">.text{font-family: Tahoma; font-size:13;}</style></head>"
				+ "<body><table class=\"text\"><tr><td>Url</td><td>https://www.w3schools.com/xml/xpath_syntax.asp</td></tr><br><br>"
				+ "<tr><td>XPath</td><td>//div[@id=\"leftmenuinner\"]//h2[last()]/span</td></tr><br><br>"
				+ "<tr><td>Filename</td><td>link</td></tr><br><br>"
				+ "<tr><td>Result</td><td>link.txt containing one span element in the last h2 element in the div with the id \"leftmenuinner\"</td></tr><br><br></table></body></html>");
		usageContent.setEditable(false);
		usageContent.setBackground(null);
		usageContent.setBorder(null);
		usageContent.setBounds(width/2-194, 60, 392, 120);
		getContentPane().add(usageContent);
		
		JLabel directory = new JLabel("<html><h3><u>Home Directories</html>");
		directory.setBounds(width/2-78, 200, 140, 20);
		directory.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(directory);
		
		JTextPane directories = new JTextPane();
		directories.setContentType("text/html");
		directories.setText("<html><head><style type=\"text/css\">.text{font-family: Tahoma; font-size:13;}</style></head>"
				+ "<body><table class=\"text\"><tr><td>Windows</td><td>&lt;root&gt;\\Users\\&lt;username&gt;</td></tr><br><br>"
				+ "<tr><td>MacOS</td><td>&lt;root&gt;/Users/&lt;username&gt;</td></tr><br><br>"
				+ "<tr><td>Linux</td><td>/home/username</td></tr></table></body></html>");
		directories.setEditable(false);
		directories.setBackground(null);
		directories.setBorder(null);
		directories.setBounds(width/2-194, 230, 392, 120);
		getContentPane().add(directories);
	}
}
