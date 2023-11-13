package com;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;
@SuppressWarnings("serial")
class DuplicateException extends Exception
{
	DuplicateException(String msg)
	{
		super(msg);
	}
	public String toString()
	{
		return "Duplicate Contact Found...";
	}
}
class ContactStore implements Comparable<ContactStore>
{
	Scanner input=new Scanner(System.in);
	String name;
	int no;
	public ContactStore(String name,int no)
	{
		this.name=name;
		this.no=no;
	}
	public ContactStore(ContactStore c)
	{
		this.name=c.name;
		this.no=c.no;
	}
	public int compareTo(ContactStore c)
	{
		if (no > c.no) {
			return 1;
		}
		else if (no < c.no) {
			return -1;
		}
		else {
			return 0;
		}
	}
	public String toString()
	{
		return "Name = "+name+"Number = "+no;
	}
}
class ContactManage
{
	ArrayList<ContactStore> cm=new ArrayList<ContactStore>();
	void addContact(ContactStore c) throws DuplicateException
	{

		int length=cm.size();
		for(int i=0;i<length+1;i++)
		{
			if(length!=0)
			{
				if(cm.get(i).no==c.no)
				{
					throw new DuplicateException("Interrupted");
				}
				else
				{
					cm.add(c);
					break;
				}
			}
			else
			{
				cm.add(c);
			}
		}
	}
	ArrayList<String> searchContact(String name)
	{
		ArrayList<String> temp=new ArrayList<String>();
		String concatstr="";
		if(cm.size()!=0)
		{
			for(int i=0;i<cm.size();i++)
			{
				for(int j=i;j<cm.get(i).name.length();j++)
				{
					if((cm.get(i).name.substring(i, j)).equals(name))
					{
						concatstr+=cm.get(i).name;
						concatstr+=cm.get(i).no;
						temp.add(concatstr);
					}
				}
			}
			return temp;
		}
		else
			return temp;
	}
	void displayContacts()
	{
		//Iterator<ContactStore> itr= cm.iterator();
		TreeSet<ContactStore> set = new TreeSet<ContactStore>();
		for(ContactStore c : cm)
		{
			set.add(c);
		}		
		System.out.println("c.no"+ " " +"c.name");
		for (ContactStore c : set) {
			System.out.println(c.no + "  " + c.name);
		}
	}
}
public class ContactManagement {
	public static void main(String args[])
	{
		//ArrayList<Contact> cm=new ArrayList<Contact>();
		ContactManage cman=new ContactManage();
		int ch,again;
		do {
			System.out.println("\n1.Insert a new Contact\n2.Search Contact\n3.Display Contacts");
			System.out.println("Enter your choice(1-3)& Enter 0 to quit");
			Scanner input=new Scanner(System.in);
			ch=input.nextInt();
			switch(ch)
			{
			case 0:
				System.out.println("Quitting the program...");
				break;
			case 1:
				System.out.println("Enter your name: ");
				String nam=input.next();
				System.out.println("Enter your number: ");
				int n=input.nextInt();
				ContactStore c=new ContactStore(nam,n);
				try 
				{
					cman.addContact(c);
				}catch(DuplicateException e)
				{
					System.out.println("Caught..."+e);
				}
				break;
			case 2:
				ArrayList<String> temp=new ArrayList<String>();
				String nam1;
				System.out.println("Enter the name to be searched : ");
				nam1=input.next();
				temp=cman.searchContact(nam1);
				for(String s : temp)
					System.out.println(s);
				break;
			case 3:
				cman.displayContacts();
				break;
			default:
				System.out.println("Wrong Choice!!!");
				break;
			}
			System.out.println("Wanna perform again?(yes=1|no=0)");
			again=input.nextInt();
		}while(again==1);
	}
}
