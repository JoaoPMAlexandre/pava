package ist.meic.pa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Stack;

public final class DebuggFunctions {
	
	public static Stack<String> callStack = new Stack<String>();
	public static String name = new String();
	public static Object returnval = null;
	
	public static boolean retry = false;
	
	public static Object trycatch(String classname, Object currentClass,
			String methodName, Object[] args) throws Throwable {
		
		try {
			Class<?> classassigned = Class.forName(classname);
			
			Class<?>[] arguments = convertArgs(args);
			Method methodFromClass = classassigned.getMethod(methodName,
				arguments);
			methodFromClass.setAccessible(true);
		
		//if(classassigned.getName().contains(Class.forName(name).getPackage().getName()))

		callStack.push(classassigned.getName() + "." + methodName + parseArgs(args));
		methodFromClass.invoke(classassigned.cast(currentClass), args);
		callStack.pop();
		
	} catch (InvocationTargetException e) {

		System.out.println(e.getCause());
		if(!retry){
			
			runDebugger(e, classname, currentClass, methodName, args);
		}
		else callStack.pop();

	}
	return null;
}

	


public static Object runDebugger(Exception ex, String classname, Object currentClass, String methodName,
		Object[] args) throws Throwable {

	System.out.print("DebuggerCLI> ");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String instruction = "default";

	while (true) {
		try {
			instruction = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String delims = "[ ]+";
		String[] cmd = instruction.split(delims);

		if (cmd[0].toLowerCase().equals("info"))
			info(classname, currentClass, methodName, args);
		else if (cmd[0].toLowerCase().equals("throw"))
			throwagain(ex);
		else if (cmd[0].toLowerCase().equals("return")){
			returnval = returnval(cmd[1], classname, currentClass, methodName, args);
			return returnval;
		}
		else if (cmd[0].toLowerCase().equals("get"))
			getfield(cmd[1], currentClass);
		else if (cmd[0].toLowerCase().equals("set"))
			setfield(cmd[1], currentClass, cmd[2]);
		else if (cmd[0].toLowerCase().equals("retry"))
			retry(classname, currentClass, methodName, args);
		else if (cmd[0].toLowerCase().equals("abort"))
			System.exit(1);
		else if (!cmd[0].toLowerCase().equals("abort"))
			System.out
					.println("The command insert is not valid! Please insert a valid command.");

		System.out.print("DebuggerCLI> ");
	}
}

public static String parseArgs(Object[] args){
	String arguments = "(";
	
	for(int i = 0; i < args.length; i++){
		Object o = args[i];
		arguments += o.toString();
		if(i != args.length - 1)
			arguments += ",";
	}
	arguments += ")";
	return arguments;
}

public static String parseMainArgs(String[] args){
	String arguments = "(";
	
	for(int i = 0; i < args.length; i++){
		String o = args[i];
		arguments += o;
		if(i != args.length - 1)
			arguments += ",";
	}
	arguments += ")";
	return arguments;
}

public static Class<?>[] convertArgs(Object[] args) {
	
	Class<?>[] arguments = new Class<?>[args.length];
	for (int i = 0; i < args.length; i++) {
		if(args[i] == null)
			arguments[i] = Object.class;
		else{
			arguments[i] = args[i].getClass();
			if (args[i].getClass().getName().equals("java.lang.Integer"))
				arguments[i] = int.class;
			else if (args[i].getClass().getName().equals("java.lang.Byte"))
				arguments[i] = byte.class;
			else if (args[i].getClass().getName().equals("java.lang.Short"))
				arguments[i] = short.class;
			else if (args[i].getClass().getName().equals("java.lang.Long"))
				arguments[i] = long.class;
			else if (args[i].getClass().getName().equals("java.lang.Float"))
				arguments[i] = float.class;
			else if (args[i].getClass().getName().equals("java.lang.Double"))
				arguments[i] = double.class;
			else if (args[i].getClass().getName().equals("java.lang.Character"))
				arguments[i] = char.class;
			else if (args[i].getClass().getName().equals("java.lang.Boolean"))
				arguments[i] = boolean.class;
		}
	}
	
	return arguments;
}

public static void retry(String classname, Object currentClass, String methodname, Object[] args) {
	retry = true;
	try {
		trycatch(classname, currentClass, methodname, args);
	} catch (Throwable e) {
		System.out.println("Impossible call.");
	}
}

public static void getfield(String fieldname, Object currentClass) {
	
	Class<?> objClass = currentClass.getClass();

	Field[] fields = objClass.getDeclaredFields();
	Object value = null;
	
	for(Field field : fields) {
		if(field.getName().equals(fieldname)){
        	field.setAccessible(true);
        	try {
        		value = field.get(currentClass);
            	System.out.println(value.toString());
			} 
        	catch (IllegalArgumentException|IllegalAccessException e) {
        		System.out.println(e.getCause());
			}
        	

        	return;
		}

	}
	System.out.println("Field not found.");		
}


public static void setfield(String fieldname, Object currentClass, String val) {
	
	Class<?> objClass = currentClass.getClass();

	Field[] fields = objClass.getDeclaredFields();
	
	for(Field field : fields) {
		if(field.getName().equals(fieldname)){
        	field.setAccessible(true);
        	try {
        		//only can set primitive types so far.
        		Object value = null;
        		if(field.getType().equals(Integer.TYPE)){
        			try{
        				value = Integer.parseInt(val);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is integer.");
        			}
        		}
        			
        		else if(field.getType().equals(Double.TYPE)){
        			try{
        				value = Double.parseDouble(val);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is double.");
        			}
        		}
        		else if(field.getType().equals(Byte.TYPE)){
        			try{
        				value = Byte.parseByte(val);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is byte.");
        			}
        		}        		
        		else if(field.getType().equals(Short.TYPE)){
        			try{
        				value = Short.parseShort(val);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is short.");
        			}
        		}                		
        		else if(field.getType().equals(Long.TYPE)){
        			try{
        				value = Long.parseLong(val);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is long.");
        			}
        		}        		
        		else if(field.getType().equals(Float.TYPE)){
        			try{
        				value = Float.parseFloat(val);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is float.");
        			}
        		}        		
        		else if(field.getType().equals(Character.TYPE)){
        			try{
        				value = val.charAt(0);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is a character.");
        			}
        		}
        		else if(field.getType().equals(Boolean.TYPE)){
        			try{
        				value = Boolean.parseBoolean(val);
        			}
        			catch (Exception e){
        				System.out.println("Invalid set argument. This argument type is boolean.");
        			}
        		}
        		
        		field.set(currentClass, value);
        		
			} 
        	catch (IllegalArgumentException|IllegalAccessException e) {
        		System.out.println("Invalid argument to set");
			}

		}

	}
			
}

public static Object returnval(String ret, String classname, Object currentClass, String methodName, Object[] args) {

	Type t = null;
	
	try {
		Class<?> classassigned = Class.forName(classname);
		Class<?>[] arguments = convertArgs(args);
		
		Method methodFromClass = classassigned.getMethod(methodName,
			arguments);
	
		t = methodFromClass.getGenericReturnType();
		methodFromClass.setAccessible(true);
	
		if(t.equals(Integer.TYPE)){
			try{
				returnval = Integer.parseInt(ret);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is integer.");
			}
		}
			
		else if(t.equals(Double.TYPE)){
			try{
				returnval = Double.parseDouble(ret);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is double.");
			}
		}
		else if(t.equals(Byte.TYPE)){
			try{
				returnval = Byte.parseByte(ret);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is byte.");
			}
		}        		
		else if(t.equals(Short.TYPE)){
			try{
				returnval = Short.parseShort(ret);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is short.");
			}
		}                		
		else if(t.equals(Long.TYPE)){
			try{
				returnval = Long.parseLong(ret);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is long.");
			}
		}        		
		else if(t.equals(Float.TYPE)){
			try{
				returnval = Float.parseFloat(ret);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is float.");
			}
		}        		
		else if(t.equals(Character.TYPE)){
			try{
				returnval = ret.charAt(0);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is a character.");
			}
		}
		else if(t.equals(Boolean.TYPE)){
			try{
				returnval = Boolean.parseBoolean(ret);
			}
			catch (Exception e){
				System.out.println("Invalid set argument. This argument type is boolean.");
			}
		}
	} 
	catch (Exception e) {
		
	}
	return returnval;
	
}

public static void throwagain(Exception e) throws Throwable {
	if(callStack.size() > 0){
		callStack.pop();
		throw e.getCause();
	}
	else System.out.println("Call Stack Empty. Cannot throw any more exceptions.");

}

public static void info(String className, Object currentClass, String methodName, Object[] args) {
	try{
		Class<?> classAssigned = Class.forName(className);
		Field[] fields = classAssigned.getDeclaredFields();
		String vars = new String();
		String classNameToPresent = "null";
		
		for(Field f : fields){
			vars += f.getName() + " ";
		}
		
		classNameToPresent = currentClass.toString();
		
		System.out.println("Called Object: " + classNameToPresent);
		System.out.println("       Fields: " + vars);
		System.out.println("Call Stack:");
		
		for (int i = callStack.size() - 1 ; i > -1 ; i--) {
			System.out.println(callStack.get(i));
		}
	}
	catch(Exception e){
		System.out.println(e.getCause());
	}
		
	}

}
