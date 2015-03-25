package ist.meic.pa;


import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.Handler;
import javassist.expr.MethodCall;

public class MyTranslator implements Translator {
	boolean rethrow = false;
    public void start(ClassPool pool)
        throws NotFoundException, CannotCompileException {}
    public void onLoad(ClassPool pool, String classname)
        throws NotFoundException, CannotCompileException
    {
        CtClass cc = pool.get(classname);
        CtClass exception = pool.get("java.lang.Exception");
        cc.setModifiers(Modifier.PUBLIC);
        CtMethod[] methods = cc.getDeclaredMethods();
        for(CtMethod m : methods){
        	/*m.addCatch("{System.out.println($e); "
        			+ "Object obj = this;  DebuggFunctions.runDebugger(obj, MyTranslator.getrethrow(); throw $e;}", exception);*/
        	m.instrument(
        		    	new ExprEditor() {
        		        public void edit(MethodCall mc)
        		                      throws CannotCompileException
        		        {
        		        	mc.replace("{DebuggFunctions.trycatch(this.getMethod(), $args); $_ = $proceed($$); }");
        		        }
        		       
        		        
        		    });
        }
    }
}