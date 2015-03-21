package ist.meic.pa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebuggerCLI {
	
	public static void main(String[] args) {
		try {
			Class<?> program = Class.forName(args[0]);
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
			DebuggFunctions deb = new DebuggFunctions();
			deb.run();
		}
	}
	
	
}
