/**
 * @(#)JoshNative_Test.java
 *
 * JoshNative Test application
 *
 * Joshua Kennedy
 * Version 1.00 2011/7/21
 */

public class JoshNative_Test {

    public static void main(String[] args)
    {
    	JoshNative.ClearScreen();
    	JoshNative.SetConsoleTitle("Josh's Java Application");
    	System.out.print("Now you see me . . . ");
    	JoshNative.PauseExecution(2000);
    	JoshNative.ClearScreen();
    	System.out.print("Now you don't . . . ");
    	JoshNative.PauseExecution(2000);
    	JoshNative.ClearScreen();
    	if(JoshNative.MessageBox("Do you like cake?", "I has a question.", JoshNative.MB_YESNO | JoshNative.MB_ICONQUESTION) == JoshNative.IDYES)
    	{
			System.out.println("User selected yes.");
			JoshNative.MessageBox("You are awesome!", "Cake rocks!", JoshNative.MB_OK | JoshNative.MB_ICONINFORMATION);
    	}
    	else
    	{
			System.out.println("User selected no.");
			JoshNative.MessageBox("You are HORRIBLE!", "Booooooo!!!!", JoshNative.MB_OK | JoshNative.MB_ICONERROR);
    	}
    	JoshNative.SetConsoleTitle("Quite a magnificent presentation, if I do say so myself.");
    	JoshNative.SetTextColors(JoshNative.Magenta, JoshNative.Black);
    	System.out.println("I am purple!!");
    	JoshNative.ResetTextColor();
    	System.out.println("I am normal!");
    	JoshNative.ResetTextColor();
    	JoshNative.SetTextColors(JoshNative.Red, JoshNative.Green);
    	System.out.println("I am CCCCRRRRAAAAZZZZYYYY!!!!1");
    	JoshNative.ResetTextColor();
    	System.out.println("\n\n");
    	JoshNative.SetCursorPosition(45, 10);
    }
}
