package ist.meic.pa;

import javassist.*;

public class DebuggerCLI extends ClassLoader {
	
	public static String arg = new String();
	
	public static void main(String[] args) throws Throwable {
		arg = args[0];
		ClassPool pool = ClassPool.getDefault();
		Translator mytranslator = new MyTranslator();
		pool.importPackage("ist.meic.pa.DebuggFunctions");
		Loader loader = new Loader(pool);
		loader.addTranslator(pool, mytranslator);
		loader.run(args);
	}
}

