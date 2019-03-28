package scraper;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.Timer;

public class ScraperAppWindow {

	private JFrame sFrame;
	private JTextField urlField;
	private JTextField xPathField;
	private JTextField fileNameField;
	private JLabel finish;

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
		int width = 460;
		int height = 460;
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
		urlField.setBounds(width/2-179, height/2-140, 352, 28);
		urlField.setToolTipText("Enter url...");
		sFrame.getContentPane().add(urlField);
		urlField.setColumns(10);

		JLabel urlLabel = new JLabel("Enter url:");
		urlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		urlLabel.setBounds(width/2-53, height/2-158, 100, 14);
		sFrame.getContentPane().add(urlLabel);

		xPathField = new JTextField();
		xPathField.setHorizontalAlignment(SwingConstants.CENTER);
		xPathField.setBounds(width/2-179, height/2-56, 352, 28);
		sFrame.getContentPane().add(xPathField);
		xPathField.setColumns(10);

		JLabel xPathLabel = new JLabel("Enter XPath:");
		xPathLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xPathLabel.setBounds(width/2-53, height/2-74, 100, 14);
		sFrame.getContentPane().add(xPathLabel);

		fileNameField = new JTextField();
		fileNameField.setHorizontalAlignment(SwingConstants.CENTER);
		fileNameField.setBounds(width/2-179, height/2+28, 352, 28);
		sFrame.getContentPane().add(fileNameField);
		fileNameField.setColumns(10);

		JLabel fileNameLabel = new JLabel("Enter file name:");
		fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileNameLabel.setBounds(width/2-53, height/2+10, 100, 14);
		sFrame.getContentPane().add(fileNameLabel);

		JButton scrape = new JButton("Scrape!");
		scrape.setBounds(width/2-53, height-140, 100, 32);
		sFrame.getContentPane().add(scrape);
		scrape.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				try{
					scrape.setEnabled(false);							
					finish.setText("Saving file...");
					Timer clearText = new Timer(400, new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e){
							Scraper run = new Scraper(urlField.getText(), xPathField.getText(), fileNameField.getText());
							run.scrape();
							finish.setText("");
							scrape.setEnabled(true);
						}
					});
					Runtime.getRuntime().exec("explorer.exe /select, " + System.getProperty("user.home") + "\\" + fileNameField.getText() + ".txt");
					clearText.start();
				} catch(IOException f){
					System.out.println("Could not open directory");
				}
			}
		});

		JLabel directory = new JLabel("Your file will be written to your home directory");
		directory.setHorizontalAlignment(SwingConstants.CENTER);
		directory.setBounds(width/2-133, height-90, 260, 20);
		sFrame.getContentPane().add(directory);

		finish = new JLabel("");
		finish.setHorizontalAlignment(SwingConstants.CENTER);
		finish.setBounds(width/2-63, height-70, 120, 20);
		sFrame.getContentPane().add(finish);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 474, 21);
		sFrame.getContentPane().add(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem help = new JMenuItem("Help");
		file.add(help);

		help.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				try{
					HelpWindow window = new HelpWindow();
					window.setVisible(true);
				} catch(Exception f){
					System.out.println("Help window missing");
				}
			}
		});
	}
}
