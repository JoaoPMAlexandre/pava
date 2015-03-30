package ist.meic.pa;


import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class MyTranslator implements Translator {

    public void start(ClassPool pool)
        throws NotFoundException, CannotCompileException {}
    public void onLoad(ClassPool pool, String classname)
        throws NotFoundException, CannotCompileException
    {

        if(!classname.contains("ist.meic.pa.DebuggFunctions") && 
        		!classname.contains("ist.meic.pa.DebuggerCLI")){
        CtClass cc = pool.get(classname);
        cc.setModifiers(Modifier.PUBLIC);
        CtMethod[] methods = cc.getDeclaredMethods();
        for(CtMethod m : methods){
	
	        	m.instrument(
	        		    new ExprEditor() {
	        		        public void edit(MethodCall mc) 
	        		        		throws CannotCompileException {


	        		        	mc.replace("{$_ = ($r) DebuggFunctions.trycatch(\"" + 
	        		        			mc.getClassName() + "\", $0, \"" + 
	        		        			mc.getMethodName() + "\", $args); }");
	        		        	
	        		        	/*if(mc.getMethodName().equals("main")){
	        		        		mc.replace("{S_ = ($r) DebuggFunctions.callStack"
	        		        				+ ".push(\"main(\" + parseMainArgs($$)");
	        		        	}*/

	        		        }
	        		    });
	       
        }
    }
    }
}