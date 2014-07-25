import java.util.Scanner;
import java.io.*;
import javax.swing.*;

class q5
{
	static   //To print all the drives present on the system only once
	{
		long j=0;
		File [] q=File.listRoots();
		System.out.println("\n................................");
		System.out.println("\n\tPRESENT DRIVES\n");
		System.out.println("................................\n");
		
		for(File r : q)
		{
			System.out.println(r);
			j=r.getTotalSpace()/(1024*1024*1024);
			if(j==0)
			{
				System.out.println("Total Space "+(float)r.getTotalSpace()/(1024*1024)+"MB");
				System.out.println("Free Space "+(float)r.getFreeSpace()/(1024*1024)+"MB");
			}
			else
			{
			
			System.out.println("Total Space:"+(float)r.getTotalSpace()/(1024*1024*1024)+"GB");
		 	System.out.println("Free Space:"+(float)r.getFreeSpace()/(1024*1024*1024)+"GB");
			}
			System.out.println("............................");
		}
	}

	public static void main(String ... as)    //To check if the drive has been Removed or Inserted  
	{
		Scanner sc=new Scanner(System.in);
		File [] q=File.listRoots();
		int j=q.length;
		
		while(true)
		{
			File [] root=File.listRoots();
			int i=root.length;
			if( i>j )
			{
				File w=findDrive(q);
				Menu m=new Menu(w);
				m.start();    //Starts the thread of Menu
				main();      //To start the drive checking again
			}
			else if(i<j)
			{
			
				System.out.println("\n............................");
				System.out.println("EXTERNAL DRIVE REMOVED!!!!!!!!\n");
				System.out.println("............................\n");
				main();
			}
		}
				
	}

	static File findDrive(File [] driv)    //To find the Drive Inserted or Removed
	{	
		
		int y;
		int flag=1;
		int pos=0;
		
		File [] qw=File.listRoots();
		for(y=0;y<driv.length;y++)
		{
			if(qw[y].equals(driv[y]))
			{
				flag=1;
			}
			else
			{
				flag=0;
				pos=y;
				break;
			}
		}
		if(flag==1)
		pos=driv.length;
		return qw[pos];
	}		
	
}


class Menu extends Thread      //Thread running prallel to main
{
	File drive;
	Menu(File rt)
	{
		drive=rt;
	}

	public void run()
	{
		int choice;
		long q;
		do
		{
			Scanner sc=new Scanner(System.in);
			String ch=JOptionPane.showInputDialog(drive+" DRIVE INSERTED\n"+"MENU\n1.Display total space\n2.Display free space\n3.Space Occupied\n4.Display contents of Drive\n5.Display only Folders and Files\n6.Return to program\n7.Exit","Your choice");
			
			char [] c=ch.toCharArray();
			for(int j=0;j<c.length;j++)
			{	
				if((c[j]<48)||(c[j]>55))
				{	
					System.out.println("Wrong choice entered!!!!!");
					break;
				}
			}
			choice=(int)c[0]-48; 

			switch(choice)
			{
			case 1: q=drive.getTotalSpace()/(1024*1024*1024);
				System.out.println("\n***************************");
				System.out.println(drive);
				if(q==0)
				System.out.println("Total Space "+(float)drive.getTotalSpace()/(1024*1024)+"MB");
				else
				System.out.println("Total Space:"+(float)drive.getTotalSpace()/(1024*1024*1024)+" GB");
				System.out.println("\n***************************");
				break;
			case 2: q=drive.getFreeSpace()/(1024*1024*1024);
				System.out.println("\n***************************");
				System.out.println(drive);
				if(q==0)
				System.out.println("Free Space "+(float)drive.getFreeSpace()/(1024*1024)+"MB");
				else
				System.out.println("\nFree Space:"+(float)drive.getFreeSpace()/(1024*1024*1024)+" GB");
				System.out.println("\n***************************");
				break;
			case 3: q=drive.getFreeSpace()/(1024*1024*1024);
				System.out.println("\n***************************");
				System.out.println(drive);
				if(q==0)
				System.out.println("\nSpace Occupied:"+(float)(drive.getTotalSpace()-drive.getFreeSpace())/(1024*1024)+" MB");
				else
				System.out.println("\nSpace Occupied:"+(float)(drive.getTotalSpace()-drive.getFreeSpace())/(1024*1024*1024)+" GB");
				System.out.println("\n***************************");
				break;
			case 4: System.out.println("\n***************************");
				System.out.println(drive);
				System.out.println("\nFiles Present:\n");
				if(drive.getTotalSpace()==drive.getFreeSpace())
				{
					System.out.println("Drive is Empty");
					break;
				}
				showFiles(drive);
				System.out.println("\n***************************");
				break;
			case 5: System.out.println("\n***************************");
				System.out.println(drive);
				String [] p=drive.list();
				System.out.println("\nFiles Present:\n");
				for(String u : p)
				System.out.println(" -> "+u);
				System.out.println("\n***************************");
				break;
			case 6:q5.main();
			case 7:System.exit(0);
			}
		}while(choice!=6);
	}
	
	static void showFiles(File x)   //Passing the Address only
	{
		File [] name;  		 //Storing Address in a File Array
		name=x.listFiles();	 //listing files in name array
		for(int z=0;z<name.length;z++)
		{
			if(name[z].isDirectory())
			{
				File a;
				a=name[z].getAbsoluteFile();
				showFiles(a);
			}
			else
				System.out.println(name[z]);		
		}
		
	}
}