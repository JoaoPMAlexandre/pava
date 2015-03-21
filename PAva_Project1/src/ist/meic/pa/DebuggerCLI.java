package ist.meic.pa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebuggerCLI {
	
	public static void main(String[] args) {
		try {
			Class program = Class.forName(args[0]);
			Method program_main = program.getMethod("main", String[].class);
			
			String[] params = null;
			
			program_main.setAccessible(true);
			program_main.invoke(program, (Object) params);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String instruction = "default";
		
		try {
			instruction = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String delims = "[ ]+";
		String[] cmd = instruction.split(delims);
		
		if (cmd[0].equals("Abort"))
			abort();
		else if (cmd[0].equals("Info"))
			info();
		else if (cmd[0].equals("Throw"))
			throwagain();
		else if (cmd[0].equals("Return"))
			returnval(Integer.parseInt(cmd[1]));
		else if (cmd[0].equals("Get"))
			getfield(cmd[1]);
		else if (cmd[0].equals("Set"))
			setfield(cmd[1], cmd[2]);
		else if (cmd[0].equals("Retry"))
			retry();
		else
			System.out.println("The command insert is not valid! Please insert a valid command.");
	}
	
	public void abort(){
			
	}

	public void retry() {
		// TODO Auto-generated method stub
		
	}

	public void getfield(String gets) {
		// TODO Auto-generated method stub
		
	}

	public void setfield(String sets, String vals) {
		// TODO Auto-generated method stub
		
	}

	public void returnval(int ret) {
		// TODO Auto-generated method stub
		
	}

	public void throwagain() {
		// TODO Auto-generated method stub
		
	}

	public void info() {
		// TODO Auto-generated method stub
		
	}
}
