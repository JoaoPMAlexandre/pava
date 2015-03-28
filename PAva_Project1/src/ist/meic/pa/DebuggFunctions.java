package ist.meic.pa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javassist.*;
import javassist.expr.MethodCall;

import java.util.Stack;

public final class DebuggFunctions {

	public static Object trycatch(String classname, Object currentClass,
			String methodName, Object[] args) throws Exception {
		try {
			Class<?> classassigned = Class.forName(classname);
			Class<?>[] arguments = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				arguments[i] = args[i].getClass();
				if (arguments[i].getName().equals("java.lang.Integer"))
					arguments[i] = int.class;
				else if (arguments[i].getName().equals("java.lang.Byte"))
					arguments[i] = byte.class;
				else if (arguments[i].getName().equals("java.lang.Short"))
					arguments[i] = short.class;
				else if (arguments[i].getName().equals("java.lang.Long"))
					arguments[i] = long.class;
				else if (arguments[i].getName().equals("java.lang.Float"))
					arguments[i] = float.class;
				else if (arguments[i].getName().equals("java.lang.Double"))
					arguments[i] = double.class;
				else if (arguments[i].getName().equals("java.lang.Character"))
					arguments[i] = char.class;
				else if (arguments[i].getName().equals("java.lang.Boolean"))
					arguments[i] = boolean.class;
			}
			Method methodFromClass = classassigned.getMethod(methodName,
					arguments);
			methodFromClass.setAccessible(true);
			methodFromClass.invoke(classassigned.cast(currentClass), args);
		} catch (Exception e) {
			System.out.println(e.getCause());
			runDebugger(e, classname, currentClass, methodName, args);
		}
		return null;
	}

	public static void runDebugger(Exception ex, String classname, Object currentClass, String methodName,
			Object[] args) throws Exception {

		System.out.print("DebuggerCLI> ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String instruction = "default";

		while (true) {
			try {
				instruction = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String delims = "[ ]+";
			String[] cmd = instruction.split(delims);

			if (cmd[0].toLowerCase().equals("info"))
				info(classname, currentClass, methodName, args);
			else if (cmd[0].toLowerCase().equals("throw"))
				throwagain(ex);
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
				System.out
						.println("The command insert is not valid! Please insert a valid command.");

			System.out.print("DebuggerCLI> ");
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

	public static void throwagain(Exception e) throws Exception {
		throw e;
	}

	public static void info(String className, Object currentClass, String methodName, Object[] args) {
		try{
			Class<?> classAssigned = Class.forName(className);
			Field[] fields = classAssigned.getFields();
			String vars = new String();
			String parsedCallStack = new String();
			
			
			for(Field f : fields){
				vars += f.getName() + " ";
			}
			
			for(Object o : args){
				//VAI RECEBER UM ARRAY BIDIMENSIONAL (NOME DO METODO + ARGS)
				//FORMATA O OUTPUT PARA SER APRESENTADO NA VAR "parsedCallStack"
			}
			
			System.out.println("Called Object: " + currentClass.toString());
			System.out.println("       Fields: " + vars);
			System.out.println("Call Stack:");
			System.out.print(parsedCallStack);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
