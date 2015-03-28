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
	        		        public void edit(MethodCall mc) throws CannotCompileException {
	        		     //   	DebuggFunctions.callStack.add(mc);
	        		        	/*if(!mc.getClassName().contains("java"))
									try {
										System.out.println(mc.getClassName() + "." + mc.getMethodName() + "(" + mc.getMethodInfo().getCodeAttribute() + ")");
									} catch (NotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}*/
	        		        	mc.replace("{$_ = ($r) DebuggFunctions.trycatch(\"" + mc.getClassName() + "\", $0, \"" + mc.getMethodName() + "\", $args); }");
	        		        //	mc.replace("{DebuggFunctions.trycatch(\"" + mc.getClassName() + "\", $0, \"" + mc.getMethodName() + "\", $args); $_ = $proceed($$); }");
	        		        }
	        		    });
	        }
        }
    }
}