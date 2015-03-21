package ist.meic.pa;

import java.util.Scanner;

public class DebuggerCLI {
	
	public static void main(String[] args) {
		try{
		
		}catch (Throwable ex) {
			System.out.println("Welcome to Debugger:");
			System.out.println("Tips: 1-Abort , 2-Info , 3-Throw , 4-Return<value>, 5-Get<field name>, 6-Set<field name><new value>, 7-Retry");
			System.out.println("Which one operation do you want to do:");
			Scanner sc = new Scanner(System.in);
		    int i = sc.nextInt();
		    DebuggerCLI debugg = new DebuggerCLI();
		    
		    switch(i){
		    
		    	case 1: debugg.abort();
		    			break;
		    
		    	case 2: debugg.info();
		    			break;
		    	
		    	case 3: debugg.throwagain();
		    			break;
		    			
		    	case 4: System.out.println("What value do you wanna return?");
		    			int ret = sc.nextInt();
		    			debugg.returnval(ret);
		    			break;
		    			
		    	case 5: System.out.println("What field name do you wanna get?");
    					String gets = sc.next();
    					debugg.getfield(gets);
    					break;
    					
		    	case 6: System.out.println("What field name do you wanna set?");
						String sets = sc.next();
						System.out.println("Which value?");
						String vals = sc.next();
						debugg.setfield(sets,vals);
						break;
						
		    	case 7: debugg.retry();
		    			break;
		    }
			
		}
	}
	
	public void abort(){
		
		
	}

	public void retry() {
		// TODO Auto-generated method stub
		
	}

	public void getfield(String gets) {
		// TODO Auto-generated method stub
		
	}

	public void setfield(String sets, String vals) {
		// TODO Auto-generated method stub
		
	}

	public void returnval(int ret) {
		// TODO Auto-generated method stub
		
	}

	public void throwagain() {
		// TODO Auto-generated method stub
		
	}

	public void info() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
