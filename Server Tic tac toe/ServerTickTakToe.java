import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ServerTickTakToe extends JFrame implements MouseListener
{

	Image board,circle,cross,board2,line45,line135,line90,line180;
	Toolkit tk;
	static String fp = "::First Player won the match::";
	static String sp = "::Second Player won the match::";
	static String printt;
	int x,y,c=1,times=0,counter=0,mousecount=0,cv1=0,cv2=0,cv3=0,cv4=0,cv5=0,cv6=0,cv7=0,cv8=0,cv9=0;
	
	// server 
	Socket sock;
	ServerSocket s;
	PrintWriter pw;
	BufferedReader br;
	Scanner sc;
	ServerTickTakToe()
	{
		
	
		serverconnection();

		tk = tk.getDefaultToolkit();
		board = tk.getImage("images/board.png");
		board2 = tk.getImage("images/board2.jpg");
		circle = tk.getImage("images/circle2.jpg");
		cross = tk.getImage("images/cross.jpg");
		line45 = tk.getImage("images/Line-icon.png");
		line135 = tk.getImage("images/Line-icon2.png");
		line90 = tk.getImage("images/Line-icon3.png");
		line180 = tk.getImage("images/Line-icon5.png");

		addMouseListener(this);

		this.setTitle("Server");
		Container c=this.getContentPane();
		c.setBackground(Color.white);
		setSize(600,620);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void serverconnection()
	{
		try
		{
			s = new ServerSocket(8189);
			System.out.println("waiting for the client request ::");
			sock = s.accept();
			System.out.println("Client is connected ::");
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void servervalue(int x, int y)throws Exception
	{
		pw = new PrintWriter(sock.getOutputStream(),true);
		pw.println(x+ " "+y);
	}
	public void getclientvalue()throws Exception
	{
		sc = new Scanner(sock.getInputStream());
		int a = sc.nextInt();
		int b = sc.nextInt();
		x=a;
		y=b;
		c=2;
		
	//	System.out.println("client value is : "+a+ "  "+b);
		
	}
	
	public void setvalue(int x, int y, int c)throws Exception
	{
		if((x>=12 && x<=188) && (y>=36 && y<=208))  // for the first quardinate
		{
			this.x=34;
			this.y=47;
			cv1=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}
		if((x>=228 && y>=40) && (x<=371 && y<=205))  // for the second quardinate
		{
			this.x=236;
			this.y=43;
			cv2=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}
		if((x>=419 && x<=586) && (y>=48 && y<=204))  // for the third quardinate
		{
			this.x=436;
			this.y=52;
			cv3=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}
		if((x>=14 && x<=184) && (y>=251 && y<=393))  // for the fourth quardinate
		{
			this.x=32;
			this.y=251;
			cv4=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}
		if((x>=233 && x<=376) && (y>=249 && y<=401))  // for the fifth quardinate
		{
			this.x=233;
			this.y=249;
			cv5=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}
		if((x>=416 && x<=584) && (y>=252 && y<=393))  // for the sixth quardinate
		{
			this.x=431;
			this.y=252;
			cv6=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}
		if((x>=12 && x<=178) && (y>=442 && y<=602))  // for the seventh qurdinate
		{
			this.x=33;
			this.y=449;
			cv7=c;
			if(c!=2)
			servervalue(this.x,this.y);

		}
		if((x>=229 && x<=373) && (y>=442 && y<=604))  // for the eighth quardinate
		{
			this.x=234;
			this.y=446;
			cv8=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}
		if((x>=414 && x<=588) && (y>=442 && y<=606))  // for the nineth quardinate
		{
			this.x=427;
			this.y=442;
			cv9=c;
			if(c!=2)
			servervalue(this.x,this.y);
		}

	}
	
	public void paint(Graphics g)
	{
	//	super.paint(g);

		Graphics2D g2 = (Graphics2D)g;
		System.out.println(times);
		if(times>0)
		{
			g.drawImage(board,0,20,this);
			if(c%2!=0)
			{
			//	System.out.println("value of c for the circle and x and y" +c+ "   "+x+"  "+y);
				counter++;
				g2.drawImage(circle,x,y,this);
				
			}
			else
			{
			//	System.out.println("value of c for the cross and x and y" +c+ "   "+x+"  "+y);
				counter++;
				g2.drawImage(cross,x,y,this);
			//	
			}
		}
		else
		{
			g.drawImage(board2,0,20,this);
		}
		// logic 
		try{
		if(times>2)
		{
			if(cv1!=0)
			{
				if(cv2!=0 && cv3!=0)
				{
					if((cv1%2!=0) && (cv2%2!=0) && (cv3%2!=0))
					{
						g2.drawImage(line180,35,-150,this);
						printt = fp;
						System.out.println("first player won the match..::");
						NewPanel n = new NewPanel();
						dispose();
						sock.close();
					}
					else
					{
						if((cv1%2==0) && (cv2%2==0) && (cv3%2==0))
						{
							g2.drawImage(line180,35,-150,this);
							printt = sp;
							System.out.println("second player won the match..::");
							NewPanel n = new NewPanel();
							dispose();
							sock.close();
						}
					}
				}
				if(cv4!=0 && cv7!=0)
				{
					if((cv1%2!=0) && (cv4%2!=0) && (cv7%2!=0))
					{
						g2.drawImage(line90,-150,50,this);
						printt = fp;
						System.out.println("first player won the match..::");
						NewPanel n = new NewPanel();
						dispose();
						sock.close();
					}
					else
					{
						if((cv1%2==0) && (cv4%2==0) && (cv7%2==0))
						{
							g2.drawImage(line90,-150,50,this);
							printt = sp;
							System.out.println("second player won the match..::");
							NewPanel n = new NewPanel();
							dispose();
							sock.close();
						}
					}

				}
				if(cv5!=0 && cv9!=0)
				{
					if((cv1%2!=0) && (cv5%2!=0) && (cv9%2!=0))
					{
						g2.drawImage(line135,10,50,this);
						printt = fp;
						NewPanel n = new NewPanel();
						dispose();
						System.out.println("first player won the match..::");
						sock.close();
					}
					else
					{
						if((cv1%2==0) && (cv5%2==0) && (cv9%2==0))
						{	
							g2.drawImage(line135,10,50,this);
							printt = sp;
							NewPanel n = new NewPanel();
							dispose();
							System.out.println("second player won the match..::");
							sock.close();
						}
					}	

				}
			}
			if(cv2!=0)
			{
				if(cv5!=0 && cv8!=0)
				{
					if((cv2%2!=0) && (cv5%2!=0) && (cv8%2!=0))
					{
						g2.drawImage(line90,30,50,this);
						printt  = fp;
						NewPanel n = new NewPanel();
						dispose();
						System.out.println("first player won the match..::");
						sock.close();
					}
					else
					{
						if((cv2%2==0) && (cv5%2==0) && (cv8%2==0))
						{
							g2.drawImage(line90,30,50,this);
							printt = sp;
							NewPanel n = new NewPanel();
							dispose();
							System.out.println("second player won the match..::");
							sock.close();
						}
					}
				}
			}
			if(cv3!=0)
			{
				if(cv6!=0 && cv9!=0)
				{
					if((cv3%2!=0) && (cv6%2!=0) && (cv9%2!=0))
					{
						g2.drawImage(line90,220,50,this);
						printt = fp;
						NewPanel n = new NewPanel();
						dispose();
						System.out.println("first player won the match..::");
						sock.close();
					}
					else
					{
						if((cv3%2==0) && (cv6%2==0) && (cv9%2==0))
						{
							g2.drawImage(line90,220,50,this);
							printt = sp;
							NewPanel n = new NewPanel();
							dispose();
							System.out.println("second player won the match..::");
							sock.close();
						}
					}
				}
				if(cv5!=0 && cv7!=0)
				{
					if((cv3%2!=0) && (cv5%2!=0) && (cv7%2!=0))
					{
						g2.drawImage(line45,15,70,this);
						printt = fp;
						NewPanel n = new NewPanel();
						dispose();
						System.out.println("first player won the match..::");
						sock.close();
					}
					else
					{
						if((cv3%2==0) && (cv5%2==0) && (cv7%2==0))
						{
							g2.drawImage(line45,15,70,this);
							printt = sp;
							NewPanel n = new NewPanel();
							dispose();
							System.out.println("second player won the match..::");
							sock.close();						
						}	
					}
					
				}
			}
			if(cv4!=0)
			{
				if(cv5!=0 && cv6!=0)
				{
					if((cv4%2!=0) && (cv5%2!=0) && (cv6%2!=0))
					{
						g2.drawImage(line180,50,50,this);
						printt = fp;
						NewPanel n = new NewPanel();
						dispose();
						System.out.println("first player won the match..::");
						sock.close();
					}
					else
					{
						if((cv4%2==0) && (cv5%2==0) && (cv6%2==0))
						{
							g2.drawImage(line180,50,50,this);
							printt = sp;
							NewPanel n = new NewPanel();
							dispose();
							System.out.println("second player won the match..::");
							sock.close();			
						}
					}
				}
			}
			if(cv7!=0)
			{
				if(cv8!=0 && cv9!=0)
				{
					if((cv7%2!=0) && (cv8%2!=0) && (cv9%2!=0))
					{
						g2.drawImage(line180,50,250,this);
						printt = fp;
						NewPanel n = new NewPanel();
						dispose();
						System.out.println("first player won the match..::");
						sock.close();					
					}
					else
					{
						if((cv7%2==0) && (cv8%2==0) && (cv9%2==0))
						{
							g2.drawImage(line180,50,250,this);
							printt = sp;
							NewPanel n = new NewPanel();
							dispose();
							System.out.println("second player won the match..::");
							sock.close();						
						}
					}
				}
			}
	System.out.println("value of times  :  "+times);
			if(times>=9)
			{
				printt = "       ::Draw the match::";
				NewPanel n = new NewPanel();
				dispose();
				sock.close();
			}
		}
		}
		catch(Exception e1)
		{}
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		
		
		try{
			if(mousecount%2!=0)
			{	
				System.out.println("getclient vlaue is called::");
				getclientvalue();
				
				setvalue(x,y,c);			
			}
			else{
			c=1;	
			x = e.getX();
			y = e.getY();
//		System.out.println(x+ "  "+y);
				setvalue(x,y,c);		
			}
		
		
		repaint();
		times++;
		mousecount++;
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}

	public static void main(String[] args) 
	{
		new ServerTickTakToe();
	}
}
