import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class NewPanel extends JFrame implements ActionListener
{
	JButton restart,quit;
	JLabel label1;
	Font f,f2;
	String s = TickTakToe.printt;
	static int check = 0;

	NewPanel()
	{
		f = new Font("serif", Font.BOLD, 20);
		f2 = new Font("serif", Font.BOLD, 20);
		
		label1 = new JLabel(s);
		add(label1);
		label1.setBounds(150,20,320,29);
		label1.setBackground(Color.white);
		label1.setForeground(Color.blue);
		label1.setFont(f);
		
		restart = new JButton("Restart");
		add(restart);
		restart.setBounds(130,80,100,29);
		restart.setBackground(Color.white);
		restart.setForeground(Color.green);
		restart.setFont(f2);

		quit = new JButton("Quit");
		add(quit);
		quit.setBounds(330,80,100,29);
		quit.setBackground(Color.white);
		quit.setForeground(Color.red);
		quit.setFont(f2);
		
		restart.addActionListener(this);
		quit.addActionListener(this);

		Container c=this.getContentPane();
		c.setBackground(Color.white);
		setSize(550,160);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==quit)
		{
			dispose();
		}
		if(e.getSource()==restart)
		{
			check++;
		    TickTakToe t = new TickTakToe();
			dispose();
		}
	}
	public static void main(String[] args) 
	{
		new NewPanel();
	}
}
