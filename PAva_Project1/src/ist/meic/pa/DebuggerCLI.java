package ist.meic.pa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebuggerCLI {
	
	public static void main(String[] args) {
		try {
			Class program = Class.forName(args[0]);
			Method program_main = program.getMethod("main", null);
			program_main.setAccessible(true);
			program_main.invoke(program, null);
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
