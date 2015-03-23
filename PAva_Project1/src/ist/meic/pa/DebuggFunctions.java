package ist.meic.pa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javassist.*;
import java.util.Stack;


public class DebuggFunctions {

	
public void run(Class<?> rtClass) {
	
		System.out.println("Welcome to Debugger");
		System.out.println("Insert a command:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String instruction = "default";
		
		while(!instruction.toLowerCase().equals("abort")){
			try {
				instruction = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String delims = "[ ]+";
			String[] cmd = instruction.split(delims);
			
			if (cmd[0].toLowerCase().equals("info"))
				info(rtClass);
			else if (cmd[0].toLowerCase().equals("throw"))
				throwagain(rtClass);
			else if (cmd[0].toLowerCase().equals("return"))
				returnval(Integer.parseInt(cmd[1]), rtClass);
			else if (cmd[0].toLowerCase().equals("get"))
				getfield(cmd[1], rtClass);
			else if (cmd[0].toLowerCase().equals("set"))
				setfield(cmd[1], cmd[2], rtClass);
			else if (cmd[0].toLowerCase().equals("retry"))
				retry(rtClass);
			else if (!cmd[0].toLowerCase().equals("abort"))
				System.out.println("The command insert is not valid! Please insert a valid command.");
		}
	}
	

	public void retry(Class<?> rtClass) {
		// TODO Auto-generated method stub
		
	}

	public void getfield(String gets, Class<?> rtClass) {
		// TODO Auto-generated method stub
		
	}

	public void setfield(String sets, String vals, Class<?> rtClass) {
		// TODO Auto-generated method stub
		
	}

	public void returnval(int ret, Class<?> rtClass) {
		
		
	}

	public void throwagain(Class<?> rtClass) {
		// TODO Auto-generated method stub
		
	}

	public void info(Class<?> rtClass) {
		

		//DUMPCLASS FOR DESCRIPTION OF THIS CLASS
		
		System.out.println("Called Object:");
		
	}
	
	
}
