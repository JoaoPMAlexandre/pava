package ist.meic.pa;

import javassist.*;

public class DebuggerCLI extends ClassLoader {
	
	
	public static void main(String[] args) throws Throwable {
		ClassPool pool = ClassPool.getDefault();
		Translator mytranslator = new MyTranslator();
		pool.importPackage("ist.meic.pa.DebuggFunctions");
		Loader loader = new Loader(pool);
		loader.addTranslator(pool, mytranslator);
		
		DebuggFunctions.callStack.push(args[0]); //Não faz nada so far.
		loader.run(args);
	}

}

