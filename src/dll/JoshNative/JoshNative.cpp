/*
 * JoshNative.cpp - Main source file for the DLL
 * Created by Joshua Kennedy on 7/21/2011
 *
 * JoshNative Java Win32 Library
 * Copyright 2011 Joshua Kennedy. All rights reserved.
 *
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

#include <stdio.h>
#include <stdlib.h>
#ifdef _WIN32
#include <Windows.h>
#endif
#include <string.h>

#include "JoshNative.h"

/* Clears the console buffer and resets the cursor position */
/* P.S: I am too cheap to actually use the native api. lol :3 */

JNIEXPORT void JNICALL Java_JoshNative__1ClearScreen (JNIEnv *, jclass)
{
#ifdef _WIN32
	COORD coord = {0}; // Top left corner coordinates
    CONSOLE_SCREEN_BUFFER_INFO cBufferInfo; // Contains information about screen buffer
    DWORD dwI; // Characters written in buffer, not used, but necessary output for later function
    DWORD dwSize; // Characters to clear ("area" of console, so to speak)
    HANDLE hI = GetStdHandle(STD_OUTPUT_HANDLE); // Handle to console
    
    if(hI == INVALID_HANDLE_VALUE)
	{
		exit(1);
	}
    if (GetConsoleScreenBufferInfo(hI, &cBufferInfo)) // Function returns 0 if error
    {
		dwSize = cBufferInfo.dwSize.X * cBufferInfo.dwSize.Y; // Calculate characters to clear
		FillConsoleOutputCharacter(hI, TEXT(' '), dwSize, coord, &dwI ); // Write char to buffer as many times as we tell it, i.e. dwSize
		SetConsoleCursorPosition(hI, coord); // Set console cursor to top left coord
    }
	else
	{
		exit(1);
	}
#else
	system("CLEAR");
#endif
}

/* Sets the console title */
/* P.S: It doesn't like Unicode. */

JNIEXPORT void JNICALL Java_JoshNative__1SetJavaConsoleTitle (JNIEnv* env, jclass, jstring title)
{
	const char *nativeString = (*env).GetStringUTFChars(title, 0);
#ifdef _WIN32
	SetConsoleTitleA(nativeString); // I don't like LPCWSTR. Yuck!
#elif __APPLE__
	char str[256];
	strcat(str, "sh title.sh ");
	strcat(str, nativeString);
#endif
}

JNIEXPORT jint JNICALL Java_JoshNative__1ShowMessageBox (JNIEnv * env, jclass, jint hwnd, jstring text, jstring title, jint options)
{
	const char *nativeTitle = (*env).GetStringUTFChars(title, 0);
	const char *nativeText = (*env).GetStringUTFChars(text, 0);
	return MessageBoxA((HWND)hwnd, nativeText, nativeTitle, (int)options);
}

JNIEXPORT jint JNICALL Java_JoshNative__1GetConsoleWindow (JNIEnv *, jclass)
{
	return (int)GetConsoleWindow();
}

JNIEXPORT void JNICALL Java_JoshNative__1SetTextColors(JNIEnv *, jclass, jint foreground, jint background)
{
    int Color = foreground + (background * 16);
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole, Color);
}

JNIEXPORT void JNICALL Java_JoshNative__1SetCursorPosition (JNIEnv *, jclass, jint x, jint y)
{
	HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

    COORD PlaceCursorHere;
    PlaceCursorHere.X = x;
    PlaceCursorHere.Y = y;

    SetConsoleCursorPosition(hConsole, PlaceCursorHere);
}
