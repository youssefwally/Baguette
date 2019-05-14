package brownBaguette;

import java.lang.reflect.Array;

public class Decode {
	
	static Boolean isBusy=false;

	public static void decode() {
		isBusy=true;
		String IFID=Main.pipeLine[0];
		String writeRegister1 =IFID.substring(4, 8);
		String writeRegister2 =IFID.substring(20, 24);
		String Data1=Main.registers[Integer.parseInt(IFID.substring(8, 12), 2)];
		String Data2=Main.registers[Integer.parseInt(IFID.substring(12, 16), 2)];
		String BEQ=IFID.substring(0,4).equals("1110")?"1":"0";
		String Mult=IFID.substring(0,4).equals("1101")?"1":"0";
		if(BEQ.equals("1")) 
			Data2=Main.registers[Integer.parseInt(IFID.substring(4, 8), 2)];
		if(Mult.equals("1")) 
			Data2=Main.registers[Integer.parseInt(IFID.substring(8, 12), 2)];
		String Data3=Main.registers[Integer.parseInt(IFID.substring(24, 28), 2)];
		String Data4=Main.registers[Integer.parseInt(IFID.substring(28, 32), 2)];
		String extendedImmediate=IFID.charAt(12)=='0'?"000000000000"+IFID.substring(12, 32):"111111111111"+IFID.substring(12, 32);
		String PC=IFID.substring(32,64);
		String opcode1=IFID.substring(0, 4);
		String opcode2=IFID.substring(16, 20);
		String MemoryRead="0";
		String MemoryWrite="0";
		String MemoryInstruction="0";
		if(opcode1.equals("0110"))
			MemoryWrite="1";
		
		if(opcode1.equals("0101")) {
			MemoryRead="1";
			MemoryInstruction="1";
		}	
		String ImmediateUNFormat=(opcode1.equals("1111")||opcode1.equals("1110")||opcode1.equals("1101"))?"1":"0";
		String JumpOrBEQ=(opcode1.equals("1111")||opcode1.equals("1110"))?"1":"0";
		
		//0110,
		//0110,1101
		//1101 in opcode 1 write1 on
		//1110,1111 in opcode 1 doesn't wirte at all
		
		String WriteRegister1Control="1";
		if(opcode1.equals("1110")||opcode1.equals("1111")||opcode1.equals("0110"))
			WriteRegister1Control="0";
		String WriteRegister2Control="1";
		if(opcode1.equals("1110")||opcode1.equals("1111")||opcode2.equals("0110")||opcode1.equals("1101"))
			WriteRegister2Control="0";
	
		Main.pipeLine[1]=Data1+Data2+Data3+Data4+extendedImmediate+writeRegister1+writeRegister2+PC+opcode1+opcode2+MemoryRead+MemoryWrite+MemoryInstruction
				+ImmediateUNFormat+JumpOrBEQ+WriteRegister1Control+WriteRegister2Control;
		
		while(Execute.isBusy) {
		}
		isBusy=false;
		
		Execute.execute();
	}
}
