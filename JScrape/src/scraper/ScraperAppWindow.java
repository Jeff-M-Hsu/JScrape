package scraper;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ScraperAppWindow {

	private JFrame sFrame;
	private JTextField urlField;
	private JTextField xPathField;
	private JTextField fileNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScraperAppWindow window = new ScraperAppWindow();
					window.sFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScraperAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int width = 480;
		int height = 480;
		sFrame = new JFrame();
		sFrame.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 20));
		sFrame.getContentPane().setLayout(null);
		sFrame.setResizable(false);
		sFrame.setForeground(new Color(102, 102, 102));
		sFrame.setBackground(new Color(102, 102, 102));
		sFrame.setBounds(200, 200, width, height);
		sFrame.setTitle("JScrape - A Java Based Web Scraper");
		sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(ScraperAppWindow.class.getResource("/resources/icon.png")));
		
		urlField = new JTextField();
		urlField.setHorizontalAlignment(SwingConstants.CENTER);
		urlField.setBounds(width/2-176, height/4-30, 352, 28);
		urlField.setToolTipText("Enter url...");
		sFrame.getContentPane().add(urlField);
		urlField.setColumns(10);
		
		JLabel urlLabel = new JLabel("Enter url:");
		urlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		urlLabel.setBounds(width/2-50, height/4-46, 100, 14);
		sFrame.getContentPane().add(urlLabel);
		
		xPathField = new JTextField();
		xPathField.setHorizontalAlignment(SwingConstants.CENTER);
		xPathField.setBounds(width/2-176, height/2-70, 352, 28);
		sFrame.getContentPane().add(xPathField);
		xPathField.setColumns(10);

		JLabel xPathLabel = new JLabel("Enter XPath:");
		xPathLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xPathLabel.setBounds(width/2-50, height/2-86, 100, 14);
		sFrame.getContentPane().add(xPathLabel);
		
		fileNameField = new JTextField();
		fileNameField.setHorizontalAlignment(SwingConstants.CENTER);
		fileNameField.setBounds(width/2-176, height*3/4-110, 352, 28);
		sFrame.getContentPane().add(fileNameField);
		fileNameField.setColumns(10);
		
		JLabel fileNameLabel = new JLabel("Enter file name:");
		fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileNameLabel.setBounds(width/2-50, height*3/4-126, 100, 14);
		sFrame.getContentPane().add(fileNameLabel);
		
		JButton scrape = new JButton("Scrape!");
		scrape.setBounds(width/2-50, height-150, 100, 32);
		sFrame.getContentPane().add(scrape);
		scrape.setEnabled(true);
		scrape.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				try{
					Scraper run = new Scraper(urlField.getText(), xPathField.getText(), fileNameField.getText());
					run.scrape();
				} catch(Exception f){
					System.out.println("Incorrect fields or result not found");
				}
				
			}
		});
		
		JLabel scrapeLabel = new JLabel("Your file will be written to your home directory \n "
				+ "Windows - <root>\\Users\\<username> \n "
				+ "MacOS - <root>/Users/<username> \n "
				+ "Linux - /home/<username>");
		scrapeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrapeLabel.setBounds(width/2-150, height-100, 300, 14);
		sFrame.getContentPane().add(scrapeLabel);
	}
}
