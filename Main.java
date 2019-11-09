import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {
	
	private List<Participant> participants;
	
	private List<Participant> users;
	
	private List<String> usernames;
	
	Scanner scanner=new Scanner(System.in);
	
	private boolean isLoggedIn;
	
	private boolean signIn;
	
	Participant current;
	
	public Main()
	{
		participants=new ArrayList<>();
		users=new ArrayList<Participant>();
		isLoggedIn=false;
		signIn=false;
		current=null;
		usernames=new ArrayList<String>();
	}

	public void setUsername()
	{
		System.out.println("Creating a new user.");
		Participant user=new Participant();
		users.add(user);
		current=user;
		while(true)
		{
		System.out.println("Please enter your username:");
		
		String username= scanner.nextLine();
		
		if(usernames.contains(username))
		{
			System.out.println("Duplicate username. Please choose another.");
			continue;
		}
		else
		{
		current.setUserName(username);
		usernames.add(username);
		return;
		}
		}
		
	}
	
	public void setPassword()
	{
	
		System.out.println("Please enter a password:");
		
		String password= scanner.nextLine();
		
		current.setPassword(password);	
		
		System.out.println("Your password has been set. Please relogin.");

	}
	
	public void login()
	{
		
		
		while(true)
		{
			System.out.println(usernames);
			System.out.println(users);
			for(int i=0;i<users.size();i++)
			{
				System.out.println(users.get(i).getUsername());
			}

			System.out.println("Please enter your username:");

			boolean successful=false;
		String username=scanner.nextLine();
		
		if(username.equalsIgnoreCase("sign up"))
		{
			setUsername();
			setPassword();
			continue;
		}
		for(int i=0;i<users.size();i++)
		{
			if(users.get(i).getUsername().equals(username))
			{
				while(true)
				{
					System.out.println("Please enter your password:");
					String password=scanner.nextLine();
					if(password.equals(users.get(i).getPassword()))
					{
						System.out.println("You have successfully logged in.");
						isLoggedIn=true;
						successful=true;
						current=users.get(i);
						break;
					}
					else
					{
						System.out.println("Wrong password. Please try again");
						continue;
					}
				}
			}
		}
		if(!successful)
		System.out.println("Username does not exist. Please try again or type \"sign up\" to sign up.");
		else
			break;
		
		}
		
	}
	
	public void runMainMenu()
	{
		System.out.println("Welcome to HackMatch!");
		System.out.println("Type log out to log out\nType read bio to read your profile\nType edit bio to edit your bio");
		String match="Your current matches: \n";
		for(int i=0;i<matchCurrent().length;i++)
		{
			match+=matchCurrent()[i]+"\n";
		}
		System.out.println(match);
		String input=scanner.nextLine();
		if(input.equalsIgnoreCase("log out"))
		{
			wantsToLogOut(true);
		}
		else if(input.equalsIgnoreCase("read bio"))
		{
			viewBio();
		}
		else if(input.equalsIgnoreCase("edit bio"))
		{
			editBio();
		}
		else
		{
			System.out.println("Not a valid input.");
		}
		
		
		
	}
	
	public void wantsToLogOut(boolean logout)
	{
		if(!logout)
		{
			return;
		}
		while(true)
		{
			System.out.println("Are you sure you want to log out");
			String input=scanner.nextLine();
			if(input.equals("yes"))
			{
				System.out.println("You have successfully logged out.");
				isLoggedIn=false;
				return;
			}
			else
			{
				break;
			}
		}
	}
	

	public static void main(String[] args)
	{
		Main main=new Main();
		System.out.println("Welcome to HackMatch");
		
		while(true)
		{
		while(!main.isLoggedIn())
		{
		main.login();
		}
		while(main.isLoggedIn())
		{
		main.runMainMenu();
		}
		}
		
		
		
	}
	
	public void viewBio()
	{
		System.out.println(current);
	}
	
	public void editBio()
	{
		while(true)
		{
		System.out.println("What do you want to edit?");
		String input=scanner.nextLine();
		if(input.equals("college"))
{
			while(true)
			{
		System.out.println("Please edit your college: ");
		input=scanner.nextLine();
		current.setCollege(input);
		System.out.println("You have set your college to: " + input);
		break;
			}
}
else if(input.equals("languages"))
{
	//BUG
	while(true)
	{
    System.out.println("Enter a programming language that you know, type \"quit\" to quit:");
    String l=scanner.nextLine();
    if(l.equals("quit"))
    {
    	break;
    }
    current.getLanguageNames().add(l);
    System.out.println("On a scale of 1-10, how proficient are you with this language?");
    int in=scanner.nextInt();
    current.getLanguagesMap().put(l, in);
  
	}
    
}
else if(input.equals("major"))
{
	if(current.getMajor().size()==0)
	{
		System.out.println("Set your current major: ");
		input=scanner.nextLine();
		current.addMajor(input);
		
	}
	while(true)
	{
	System.out.println("Enter another major, or type \"quit\" to quit: ");
	input=scanner.nextLine();
	if(input.equals("quit"))
	{
		break;
	}
	else
	{
		current.addMajor(input);
		continue;
	}
	}
	
}
else
{
	System.out.println("You are back at the main menu.");
	break;
}
	
}
    
	}
	
	public boolean isLoggedIn()
	{
		return isLoggedIn;
	}
	
	public Participant[] matchCurrent()
	{
		List<Participant> scores=new ArrayList<>();
		scores=recursiveSort(users);
		scores.remove(current);
		Participant[] temp=new Participant[10];
		int i=scores.size()-1;
		while(i>=0)
		{
			temp[i]=scores.get(i);
			i--;
		}
		
		return temp;
		
	}
	
	private List<Participant> combine(List<Participant> list1, List<Participant> list2)
	{
		List<Participant> temp=new ArrayList<>();
		while(list1.size()!=0&&list2.size()!=0)
		{
			if(current.compareTo(list1.get(0))>current.compareTo(list2.get(0)))
			{
				 temp.add(list2.remove(0));
			}
			else
			{
				temp.add(list1.remove(0));
			}
		}
		if(list1.size()!=0)
		{
			while(list1.size()!=0)
			{
				temp.add(list1.remove(0));
			}
		}
		else if(list2.size()!=0)
		{
			while(list2.size()!=0)
			{
				temp.add(list2.remove(0));
			}
		}
		return temp;
	}
	
	private List<Participant> recursiveSort(List<Participant> list)
	{
		if(list.size()<=1)
		{
			return list;
		}
		List<Participant> half1=new ArrayList<>();
		List<Participant> half2=new ArrayList<>();
		
		for(int i=0;i<list.size()/2;i++)
		{
			half1.add(list.get(i));
		}
		
		for(int i=list.size()/2;i<list.size();i++)
		{
		half2.add(list.get(i));
		}
		
		List<Participant> left = recursiveSort(half2);
		List<Participant> right = recursiveSort(half1);
		return combine(left, right);
	}
	
}
