package brownBaguette;

public class WriteBack {

	static Boolean isBusy=false;
	
	public static void writeBack() {
		isBusy=true;
		String MWB = Main.pipeLine[3];
		//String opcode1=MWB.substring(136, 140);
		String MemoryInstruction=MWB.substring(136,137);
		String WriteRegister1=MWB.substring(137,138);
		String WriteRegister2=MWB.substring(138,139);
		
		//System.out.println("MWB+");
		if(WriteRegister2.equals("1")&&Integer.parseInt(MWB.substring(4, 8),2)!=0){
			Main.registers[Integer.parseInt(MWB.substring(4, 8),2)]=MWB.substring(72,104);
		}
		boolean write1=true;
		String outMux;
		if(MemoryInstruction.equals("1")) {
			outMux=MWB.substring(8,40);
		}else {
			outMux=MWB.substring(40,72);
		}
		if(WriteRegister1.equals("0"))
			write1=false;
		if(write1&&Integer.parseInt(MWB.substring(0, 4),2)!=0) {
			Main.registers[Integer.parseInt(MWB.substring(0, 4),2)]=outMux;
		}
		while(Decode.isBusy) {
		}
		isBusy=false;
		Main.PC=MWB.substring(104,136);
	}
}
//0110,
//0110,1101
//1101 in opcode 1 write1 on
//1110,1111 in opcode 1 doesn't wirte at all