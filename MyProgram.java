
public class MyProgram
{
    
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
		
}
