import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.FileWriter;
import java.util.*;
import java.io.*;

public class ATM_Replica extends JFrame
{
	JLabel name,password,sh_bal,sh_dep_st,sh_wit_st;
	JTextField en_name,dep_amt,wit_amt;
	JPasswordField en_pass;
	JButton Cr_Acc,Dep,Bal_Inq,done_dep,done_wit,With,cls,strt;

	public ATM_Replica(){ }
	public ATM_Replica(String s){super(s);}
	public void setComponents()
	{
		name=new JLabel("Enter Your Name");
		password=new JLabel("Enter Your Password");
		en_pass=new JPasswordField();
		Cr_Acc=new JButton("Create Account");
		Dep=new JButton("Deposit");
		Bal_Inq=new JButton("Balance Inquiry");
		sh_bal=new JLabel();
		dep_amt=new JTextField();
		wit_amt=new JTextField();
		en_name=new JTextField();
		sh_dep_st=new JLabel();
		sh_wit_st=new JLabel();
		done_dep=new JButton("Done");
		done_wit=new JButton("Done");
		With=new JButton("Withdraw");
		cls=new JButton("Clear");
		strt=new JButton("Welcome to MyBank ATM");

		Cr_Acc.setBackground(Color.yellow);
		Dep.setBackground(Color.yellow);
		Bal_Inq.setBackground(Color.yellow);
		With.setBackground(Color.yellow);
		done_dep.setBackground(Color.green);
		done_wit.setBackground(Color.green);
		strt.setBackground(Color.green);
		cls.setBackground(Color.red);
		
		
		setLayout(null);
		name.setBounds(100,100,100,20);
		en_name.setBounds(300,100,50,20);
		password.setBounds(100,150,150,20);
		en_pass.setBounds(300,150,50,20);
		Cr_Acc.setBounds(10,200,150,20);	//button
		Dep.setBounds(180,200,100,20);		//button
		Bal_Inq.setBounds(300,200,150,20);	//button
		With.setBounds(470,200,150,20);		//button
		cls.setBounds(640,200,100,20);		//button
		strt.setBounds(250,250,200,100);		//button

		setLayout(null);
		add(wit_amt);
		add(done_wit);
		add(sh_wit_st);
		add(sh_bal);
		add(done_dep);
		add(dep_amt);
		add(sh_dep_st);
		add(name);
		add(password);
		add(en_name);
		add(en_pass);
		add(Cr_Acc);
		add(Dep);
		add(Bal_Inq);
		add(With);
		add(cls);
		add(strt);

		Cr_Acc.addActionListener(new create());
		Dep.addActionListener(new deposit());
		Bal_Inq.addActionListener(new inqurie());
		With.addActionListener(new withdraw());
		done_dep.addActionListener(new final_deposit());
		done_wit.addActionListener(new final_withdraw());
		cls.addActionListener(new clear());
		strt.addActionListener(new start());

			name.setVisible(false);
			en_name.setVisible(false);
			en_pass.setVisible(false);
			password.setVisible(false);
			Cr_Acc.setVisible(false);
			Dep.setVisible(false);
			Bal_Inq.setVisible(false);
			With.setVisible(false);
			cls.setVisible(false);
	}
	/////////////////////////////////////////////////////////////
	class create implements ActionListener
	{
		public  void actionPerformed(ActionEvent e1)
		{
			try{
			String amt ="0";
			String getname = en_name.getText();
			File f1=new File(getname);
			if(!f1.exists())
			{
				FileOutputStream fout=new FileOutputStream(getname,true);
				String getpass = en_pass.getText();
				FileWriter fw=new FileWriter(getname,true);
				fw.write(getpass);
				fw.write(" ");
				fw.write(amt);
				fw.close();
				fout.close();
				sh_bal.setVisible(false);
			}
			else
			{   
				sh_bal.setBounds(10,220,150,20);
				sh_bal.setVisible(true);
				sh_bal.setText("Account Already Exists");
			}}catch(IOException ioe){ }
		}
	}
	//////////////////////////////////////////////////////////////
	class deposit implements ActionListener
	{
		public  void actionPerformed(ActionEvent e2)
		{try
		{
			wit_amt.setVisible(false);
			done_wit.setVisible(false);
			sh_wit_st.setVisible(false);
			String getname = en_name.getText();
			String getpass = en_pass.getText();
			Scanner sd = new Scanner(new File(getname));
			String check_pass;
			check_pass = sd.next();
			if(getpass.equals(check_pass))
			{
				done_dep.setVisible(true);
				dep_amt.setVisible(true);
				sh_dep_st.setVisible(true);
				sh_bal.setText("Current Balance: "+sd.next());
				sh_bal.setBounds(10,220,150,20);
				sh_dep_st.setText("Amount to deposit");
				sh_dep_st.setBounds(50,250,150,20);
				dep_amt.setBounds(200,250,50,20);
				done_dep.setBounds(300,250,80,20);
			}
			else{sh_bal.setBounds(10,220,150,20);
				sh_bal.setText("Incorrect password");}
				sd.close();

		}
		catch(FileNotFoundException fnf){sh_bal.setBounds(10,220,150,20);
				sh_bal.setText("Account not created");}

		}
	}
	////////////////////////////////////////////////////////////////////////////
	class final_deposit implements ActionListener
	{
		public  void actionPerformed(ActionEvent e3)
		{try
		{

			String getname = en_name.getText();
			String getpass = en_pass.getText();
			Scanner sd = new Scanner(new File(getname));
			String amt_deposited;
			amt_deposited = dep_amt.getText();
			String pre_amt;
			sd.next();
			pre_amt=sd.next();
			FileWriter fw=new FileWriter(getname,false);
					fw.write(getpass);
					fw.write(" ");
					Integer amt1=Integer.parseInt(amt_deposited);
					Integer amt2=Integer.parseInt(pre_amt);
					Integer amt=amt1+amt2;
					amt_deposited = String.valueOf(amt);
					fw.write(amt_deposited);
					sh_bal.setBounds(10,220,150,20);
					sh_bal.setText("Current Balance: "+amt);
					fw.close();
					sd.close();
			dep_amt.setText("");
			done_dep.setVisible(false);
			dep_amt.setVisible(false);
			sh_dep_st.setVisible(false);
		}catch(IOException ioe){}
		}
	}

	////////////////////////////////////////////////////////////////////////////
	class inqurie implements ActionListener
	{
		public  void actionPerformed(ActionEvent e3)
		{
			try{
				done_dep.setVisible(false);
				dep_amt.setVisible(false);
				sh_dep_st.setVisible(false);
				wit_amt.setVisible(false);
				done_wit.setVisible(false);
				sh_wit_st.setVisible(false);
				String getname = en_name.getText();
				String getpass = en_pass.getText();
				Scanner sd = new Scanner(new File(getname));
				String check_pass;
				check_pass = sd.next();
				if(getpass.equals(check_pass))
				{
					sh_bal.setBounds(10,220,150,20);
					sh_bal.setText("Current Balance: "+sd.next());
				}
				else{
					sh_bal.setBounds(10,220,150,20);
				sh_bal.setText("Incorrect password");}
				sd.close();
			}catch(FileNotFoundException fnf){sh_bal.setBounds(10,220,150,20);
				sh_bal.setText("Account not created");}
		}
	}
	////////////////////////////////////////////////////////////////////////////
	class withdraw implements ActionListener
	{
		public  void actionPerformed(ActionEvent e3)
		{
			try
			{	
				done_dep.setVisible(false);
				dep_amt.setVisible(false);
				sh_dep_st.setVisible(false);
				String getname = en_name.getText();
				String getpass = en_pass.getText();
				Scanner sd = new Scanner(new File(getname));
				String check_pass;
				check_pass = sd.next();
				if(getpass.equals(check_pass))
				{
					wit_amt.setVisible(true);
					done_wit.setVisible(true);
					sh_wit_st.setVisible(true);
					sh_bal.setText("Current Balance: "+sd.next());
					sh_bal.setBounds(10,220,150,20);
					sh_wit_st.setText("Amount to withdraw");
					sh_wit_st.setBounds(50,250,150,20);
					wit_amt.setBounds(200,250,50,20);
					done_wit.setBounds(300,250,80,20);
				}
				else{sh_bal.setBounds(10,220,150,20);
					sh_bal.setText("Incorrect password");}
			sd.close();
			}catch(FileNotFoundException fnf){sh_bal.setBounds(10,220,150,20);
				sh_bal.setText("Account not created");}
		}
	}
	////////////////////////////////////////////////////////////////////////////
	class final_withdraw implements ActionListener
	{
		public  void actionPerformed(ActionEvent e3)
		{
			try
			{
				String getname = en_name.getText();
				String getpass = en_pass.getText();
				Scanner sd = new Scanner(new File(getname));
				String amt_withdrawn;
				amt_withdrawn = wit_amt.getText();
				String pre_amt;
				sd.next();
				pre_amt=sd.next();
				FileWriter fw=new FileWriter(getname,false);
						fw.write(getpass);
						fw.write(" ");
						Integer amt1=Integer.parseInt(amt_withdrawn);
						Integer amt2=Integer.parseInt(pre_amt);
						if(amt1 < amt2)
						{
							Integer amt=amt2-amt1;
							amt_withdrawn = String.valueOf(amt);
							fw.write(amt_withdrawn);
							sh_bal.setBounds(10,220,150,20);
							sh_bal.setText("Current Balance: "+amt);
							fw.close();

						}
						else
						{
							sh_bal.setBounds(10,220,150,20);
							sh_bal.setText("Insufficient Balance");
							fw.write(" ");
							fw.write(pre_amt);
						}
				wit_amt.setText("");
				wit_amt.setVisible(false);
				done_wit.setVisible(false);
				sh_wit_st.setVisible(false);
				fw.close();sd.close();
			}catch(IOException ioe){}
		}
	}
	////////////////////////////////////////////////////////////////////////////
	class clear implements ActionListener
	{
		public  void actionPerformed(ActionEvent e1)
		{
			en_pass.setText("");
			en_name.setText("");
			sh_bal.setText("");
			name.setVisible(false);
			en_name.setVisible(false);
			en_pass.setVisible(false);
			password.setVisible(false);
			Cr_Acc.setVisible(false);
			Dep.setVisible(false);
			Bal_Inq.setVisible(false);
			With.setVisible(false);
			cls.setVisible(false);
			strt.setVisible(true);
			done_dep.setVisible(false);
			dep_amt.setVisible(false);
			sh_dep_st.setVisible(false);
			wit_amt.setVisible(false);
			done_wit.setVisible(false);
			sh_wit_st.setVisible(false);
		}
	}
////////////////////////////////////////////////////////////////////////////
class start implements ActionListener
	{
		public  void actionPerformed(ActionEvent e1)
		{
			strt.setVisible(false);
			name.setVisible(true);
			en_name.setVisible(true);
			en_pass.setVisible(true);
			password.setVisible(true);
			Cr_Acc.setVisible(true);
			Dep.setVisible(true);
			Bal_Inq.setVisible(true);
			With.setVisible(true);
			cls.setVisible(true);
		}
	}
	
	public static void main(String []args)
	{
		ATM_Replica jf=new ATM_Replica("MyBank");
				
		jf.setComponents();
		jf.setSize(800,800);
		jf.setVisible(true);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}