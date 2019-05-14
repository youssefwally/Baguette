package brownBaguette;

public class MemoryRW {

public static	boolean read=false;
public 	static boolean write=false;
	
static Boolean isBusy=false;
	
	
	public static void memory() {
		isBusy=true;
		String theString=Main.pipeLine[2];
		String PC= theString.substring(0, 32);
		String ALU1=theString.substring(32,64);
		String ALU2=theString.substring(64,96);
		String pcIfBranch=theString.substring(96,128);
		String writeRegister1=theString.substring(128,132);
		String writeRegister2=theString.substring(132,136);
		String Data2= theString.substring(136,168);
		String memoryReadControl=theString.substring(168,169);
		String memoryWriteControl=theString.substring(169,170);
		String JumpOrBEQ=theString.substring(171,172);
		String Zero=theString.substring(theString.length()-1,theString.length());
		String memoryRead="00000000000000000000000000000000";
		String chosenPC=PC;
		write=false;
		read=false;
		
		//we need to check equality

		
		if(JumpOrBEQ.equals("1")&&Zero.equals("1")) {
			chosenPC=pcIfBranch;
		System.err.println("nooooooooooooo");
		}
		
		if(memoryWriteControl.equals("1"))
			write=true;
		
		if(memoryReadControl.equals("1"))
			read=true;
		
			if(write) {
				int address=Integer.parseInt(ALU1,2);
				Main.dataMemory.set(address, Data2);
			}
			
			if(read) {
				int address=Integer.parseInt(ALU1,2);
				String dataRead=Main.dataMemory.get(address);
				memoryRead=dataRead;
			}
			
			
			String passingString=writeRegister1+writeRegister2+memoryRead+ALU1+ALU2+chosenPC+theString.substring(170,171)+theString.substring(172,theString.length());
			Main.pipeLine[3]=passingString;
			while(WriteBack.isBusy) {
			}
			isBusy=false;
			WriteBack.writeBack();
			
			
		}
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
}
