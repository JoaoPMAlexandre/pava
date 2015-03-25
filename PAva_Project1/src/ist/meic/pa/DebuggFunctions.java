package ist.meic.pa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javassist.*;
import java.util.Stack;


public final class DebuggFunctions {

public static void trycatch(Object objectToInvoke, Object[] args){
	
	
}
	
public static void runDebugger() {
	
		
		System.out.println("Welcome to Debugger");
		System.out.println("Insert a command:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String instruction = "default";
		
		while(true){
			try {
				instruction = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String delims = "[ ]+";
			String[] cmd = instruction.split(delims);
			
			if (cmd[0].toLowerCase().equals("info"))
				info();
			else if (cmd[0].toLowerCase().equals("throw"))
				throwagain();
			else if (cmd[0].toLowerCase().equals("return"))
				returnval(Integer.parseInt(cmd[1]));
			else if (cmd[0].toLowerCase().equals("get"))
				getfield(cmd[1]);
			else if (cmd[0].toLowerCase().equals("set"))
				setfield(cmd[1], cmd[2]);
			else if (cmd[0].toLowerCase().equals("retry"))
				retry();
			else if (cmd[0].toLowerCase().equals("abort"))
				System.exit(1);
			else if (!cmd[0].toLowerCase().equals("abort"))
				System.out.println("The command insert is not valid! Please insert a valid command.");
		}
	}
	

	public static void retry() {
		// TODO Auto-generated method stub
		
	}

	public static void getfield(String gets) {
		// TODO Auto-generated method stub
		
	}

	public static void setfield(String sets, String vals) {
		// TODO Auto-generated method stub
		
	}

	public static void returnval(int ret) {
		
		
	}

	public static void throwagain() {
		// TODO Auto-generated method stub
		
	}

	public static void info() {
		

		//DUMPCLASS FOR DESCRIPTION OF THIS CLASS
		System.out.println("Called Object:");
		
	}
	
	
}
