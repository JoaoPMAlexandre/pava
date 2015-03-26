package ist.meic.pa;


import javassist.*;
import javassist.bytecode.MethodInfo;
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
        cc.setModifiers(Modifier.PUBLIC);
        CtMethod[] methods = cc.getDeclaredMethods();
        for(CtMethod m : methods){
	        if(!classname.equals("ist.meic.pa.DebuggFunctions") && !classname.equals("ist.meic.pa.DebuggerCLI")){	
	        	m.instrument(
	        		    new ExprEditor() {
	        		        public void edit(MethodCall mc)
	        		                      throws CannotCompileException {
	         		        	mc.replace("{DebuggFunctions.trycatch(\"" + mc.getClassName() + "\", $0, \"" + mc.getMethodName() + "\", $args); $_ = $proceed($$); }");
	        		        }
	        		    });
	        }
        }
    }
}