package ist.meic.pa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Stack;

import javassist.*;

public class DebuggerCLI extends ClassLoader {
	
	
	public static void main(String[] args) throws Throwable {
		Class<?> rtClass = null;
		try {
			ClassPool pool = ClassPool.getDefault();
			CtClass ctClass = pool.getCtClass(args[0]);
			rtClass = ctClass.toClass();
			
			Method main = rtClass.getMethod("main", args.getClass());
	        main.invoke(null, new Object[] { args });

		}
		catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			
			/*StackTraceElement[] stackarray = e.getStackTrace();
			for (StackTraceElement s : stackarray){
				System.out.println(s.getMethodName());
			}*/
			e.printStackTrace();
			DebuggFunctions debugger = new DebuggFunctions();
			debugger.run(rtClass);
		}
	}

}
