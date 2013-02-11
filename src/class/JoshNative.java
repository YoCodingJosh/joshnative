/**
 * JoshNative.java
 *
 * JoshNative Java Bindings to my Native API
 *
 * Joshua Kennedy
 * Version 0.01 2011/7/17
 */

/*
 * JoshNative - Useful Java Native APIs Copyright (C) 2011 Josh Kennedy
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

import java.lang.String;
import java.lang.*;

public class JoshNative
{
	// Requires JoshNative.dll (Win32) or libJoshNative.so (UNIX) in the executable directory. :3

	private static native void _ClearScreen();
	private static native void _SetJavaConsoleTitle(String title);
	private static native int _ShowMessageBox(int hwnd, String text, String title, int options);
	private static native int _GetConsoleWindow();
	private static native void _SetTextColors(int foreground, int background);
	private static native void _SetCursorPosition(int x, int y);


	// MessageBox stuff
	// ps: java doesn't like unsigned ints. :(

    // button options
	public static int MB_ABORTRETRYIGNORE = (int)0x00000002L;
	public static int MB_CANCELTRYCONTINUE = (int)0x00000006L;
	public static int MB_OK = (int)0x00000000L;
	public static int MB_OKCANCEL = (int)0x00000001L;
	public static int MB_RETRYCANCEL = (int)0x00000005L;
	public static int MB_YESNO = (int)0x00000004L;
	public static int MB_YESNOCANCEL = (int)0x00000003L;

	// dialog icons
	public static int MB_ICONEXCLAMATION = (int)0x00000030L;
	public static int MB_ICONWARNING = MB_ICONEXCLAMATION; // same thing
	public static int MB_ICONINFORMATION = (int)0x00000040L;
	public static int MB_ICONASTERISK = MB_ICONINFORMATION; // still same thing
	public static int MB_ICONQUESTION = (int)0x00000020L;
	public static int MB_ICONSTOP = (int)0x00000010L;
	public static int MB_ICONERROR = MB_ICONSTOP;
	public static int MB_ICONHAND = MB_ICONSTOP; // still.. :)

	// default button
	public static int MB_DEFBUTTON1 = (int)0x00000000L;
	public static int MB_DEFBUTTON2 = (int)0x00000100L;
	public static int MB_DEFBUTTON3 = (int)0x00000200L;
	public static int MB_DEFBUTTON4 = (int)0x00000300L;

	// return values, atleast I don't have to cast them. :D
	public static int IDABORT = 3;
	public static int IDCANCEL = 2;
	public static int IDCONTINUE = 11;
	public static int IDIGNORE = 5;
	public static int IDNO = 7;
	public static int IDOK = 1;
	public static int IDRETRY = 4;
	public static int IDYES = 6;

	// Console Text Colors

	public static int Black = 0;
	public static int Blue = 1;
	public static int Green = 2;
	public static int Cyan = 3;
	public static int Red = 4;
	public static int Magenta = 5;
	public static int Brown = 6;
	public static int LightGrey = 7;
	public static int DarkGrey = 8;
	public static int LightBlue = 9;
	public static int LightGreen = 10;
	public static int LightCyan = 11;
	public static int LightRed = 12;
	public static int LightMagenta = 13;
	public static int Yellow = 14;
	public static int White = 15;
	public static int Blink = 128;

	// Clears the console screen and resets the cursor position.

	public static void ClearScreen()
	{
		System.loadLibrary("JoshNative");
		_ClearScreen();
	}

	// Sets console title, only supports Win32 and Mac OS X (through a shell script provided in the bin directory.

	public static void SetConsoleTitle(String title)
	{
		System.loadLibrary("JoshNative");
		_SetJavaConsoleTitle(title);
	}

	public static int MessageBox(String text, String title, int options)
	{
		System.loadLibrary("JoshNative");
		return _ShowMessageBox(_GetConsoleWindow(), text, title, options);
	}

	public static int MessageBox(int hwnd, String text, String title, int options)
	{
		System.loadLibrary("JoshNative");
		return _ShowMessageBox(hwnd, text, title, options);
	}

	public static void SetTextColors(int foreground, int background)
	{
		System.loadLibrary("JoshNative");
		_SetTextColors(foreground, background);
	}

	public static void ResetTextColor()
	{
		System.loadLibrary("JoshNative");
		_SetTextColors(LightGrey, Black);
	}

	public static void SetCursorPosition(int x, int y)
	{
		_SetCursorPosition(x, y);
	}

	public static void PauseExecution(int milliseconds)
    {
		try
		{
  			Thread.currentThread().sleep(milliseconds);
		}
		catch(Exception e)
		{
			System.exit(1);
    	}
    }
}
