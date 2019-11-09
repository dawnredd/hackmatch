import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {
	

	//a list of users that are currently recorded. 
	private List<Participant> users;
	
	//a list of currently existing usernames in the database
	private List<String> usernames;
	
	//we will use java's scanner class to take user input
	Scanner scanner=new Scanner(System.in);
	
	//whether or not a user is currently logged in
	private boolean isLoggedIn;
	
	//The currently logged in user
	Participant current;
	
	/**
	 * Constructor for the main class, which runs the program.
	 **/
	public Main()
	{
		users=new ArrayList<Participant>();
		isLoggedIn=false;
		current=null;
		usernames=new ArrayList<String>();
	}

    /**
     * method for adding a new user with a specified username.
     * */
	public void setUsername()
	{
		System.out.println("Creating a new user.");
		//initializes and declares a new participant
		Participant user=new Participant();
		//adds that to the database of users
		users.add(user);
		//sets the current user to be the one that was just created.
		current=user;
		//while(true) loop forces the user to choose an appropriate username
		while(true)
		{
		System.out.println("Please enter your username:");
		//read userinput
		String username= scanner.nextLine();
		//if the username already exists, force the user to choose another one
		if(usernames.contains(username))
		{
			System.out.println("Duplicate username. Please choose another.");
			continue;
		}
		else
		{
		    //valid username; set the user's name to their input
		current.setUserName(username);
		//add it to the list of usernames
		usernames.add(username);
		//end the method+escape the infinite loop
		return;
		}
		}
		
	}
	
	/**
	 * method that sets the password of a newly created user
	 * */
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

			System.out.println("Please enter your username:");
        //boolean keeps track of whether or not the user logged in successfully
			boolean successful=false;
			//read user input
		String username=scanner.nextLine();
		//let the user sign up if they dont have an account
		if(username.equalsIgnoreCase("sign up"))
		{
			setUsername();
			setPassword();
			continue;
		}
		//find the user that has that specific user name in the list of users
		//note: binary search would've been more efficient here, if we had
		//more time we would've implemented that over linear search
		for(int i=0;i<users.size();i++)
		{
		    //stop at the user that has that username
			if(users.get(i).getUsername().equals(username))
			{
			    						successful=true;
				while(true)
				{
				   //gets the password and compares it to the user's password 
					System.out.println("Please enter your password:");
					String password=scanner.nextLine();
					//if it's correct, we're logged in!
					if(password.equals(users.get(i).getPassword()))
					{
						System.out.println("You have successfully logged in.");
						isLoggedIn=true;
						current=users.get(i);
						//leave the infinite loop
						break;
					}
					else
					{
					    //go back to the top if the wrong password was entered
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
	    //self explanatory. reads user input and performs the specific action
		System.out.println("Welcome to HackMatch!");
		System.out.println("Type log out to log out\nType read bio to read your profile\nType edit bio to edit your bio");
		String match="Your current matches: \n";
		//prints out the top 10 matches
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
	
	/**
	 * prints out the toString representation of the current user.
	 * */
	public void viewBio()
	{
		System.out.println(current);
	}
	
	/**
	 * method that allows the user to make changes to their bio
	 * */
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
	//there's a weird bug here that doesnt allow the user to input the
	//programming language's name and only the number, which is odd and 
	//likely has something to do with the scanner class 
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
