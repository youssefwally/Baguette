package brownBaguette;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Gui extends JFrame implements Runnable{
	JList list;
	JList list_1;
	JTextPane textPane; 
	JTextPane textPane_1; 
	Icon icon =new ImageIcon("tab.png");
	public Gui() {
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		this.setTitle("Baguette Compiler");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		textPane = new JTextPane();
		textPane.setToolTipText("Enter your Instruction Here");
		panel.add(textPane);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Compile");
		//btnNewButton.setIcon(arg0);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arg0.getSource();
				Main.ArrayToInstructionMemory(textPane.getText());
				try {
					Thread.sleep(50);
					System.out.println(Main.instructionMemory.toString());
					
					list.setListData(Main.instructionMemory.toArray());
					list.revalidate();
					list.repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Execute");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arg0.getSource();
				Main.x.execute();
				try {
					Thread.sleep(50);
					list_1.setListData(Main.dataMemory.toArray());
					textPane_1.setText(Main.printRegisters());
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		panel_2.add(btnNewButton_1);
		
		textPane_1 = new MyTextPane();
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPane_1.setText(Main.printRegisters());
		textPane_1.setEditable(false);
		textPane.setForeground(Color.BLACK);
		panel.add(textPane_1);
		panel.add(new JScrollPane(textPane_1));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		 list = new BackgroundImageList(Main.instructionMemory.toArray());
		
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(list);
		JScrollPane scrollPane = new JScrollPane(list);
		panel_1.add(scrollPane);
		
		JLabel lblInstructionMemory = new JLabel("Instruction Memory");
		lblInstructionMemory.setFont(new Font("Garamond", Font.BOLD, 17));
		lblInstructionMemory.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblInstructionMemory);
		
		 list_1 = new BackgroundImageList2(Main.dataMemory.toArray());
		 list_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(list_1);
		list_1.setForeground(Color.BLACK);
		
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		panel_1.add(scrollPane_1);
		
		JLabel lblDataMemory = new JLabel("Data Memory");
		lblDataMemory.setFont(new Font("Garamond", Font.BOLD, 17));
		lblDataMemory.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblDataMemory);
		panel_1.setVisible(true);
		this.setSize(900, 700);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	  private static class MyTextPane extends JTextPane {
	        public MyTextPane() {
	            super();
	            setText("Hello World");
	            setOpaque(false);

	            // this is needed if using Nimbus L&F - see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6687960
	            setBackground(new Color(0,0,0,0));
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            // set background green - but can draw image here too
	           

	            // uncomment the following to draw an image
	             Image img = null;
				try {
					img = ImageIO.read(new File("tab.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             g.drawImage(img, 0, 0, this);


	            super.paintComponent(g);
	        }
	    }
	
	
	
	
	
}
