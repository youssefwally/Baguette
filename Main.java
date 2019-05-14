package brownBaguette;

import java.util.ArrayList;

public class Main {
		
		public static String PC="00000000000000000000000000000000";
		public static ArrayList<String> instructionMemory = new ArrayList<>();
		public static ArrayList<String> dataMemory = new ArrayList<>();
		static String[] registers;
		static String[] pipeLine;
		static Main x;
		
		
		
		public Main() {
			// TODO Auto-generated constructor stub
			
			registers=new String[16];
			registers[0]="00000000000000000000000000000001";
			for(int i=1;i<registers.length;i++) {
				registers[i]="00000000000000000000000000000000";
			}
			for(int i=0;i<32;i++)
			{
				instructionMemory.add("00000000000000000000000000000000");
				dataMemory.add("00000000000000000000000000000000");
			}
			pipeLine=new String[4];
		}


		public static void main(String[] args) throws InterruptedException {
			x=new Main();
//			instructionMemory.add("11100001000000000010000000000010");
//			instructionMemory.add("11010010000100000000000000000010");
//			Fetch.fetchInstruction();
//			Thread.sleep(50);	
//			Fetch.fetchInstruction();
//			x.printRegisters();
			
			
			System.out.println(instructionMemory.toString()+" Instruction Mem");
			//ArrayToInstructionMemory("add #s1 #1 #1\nmuli #gp #s1 25");
			System.out.println(instructionMemory.toString());
		//	x.execute();
		//	x.printRegisters();
			Gui kk = new Gui();
			
		}
		
		
		
		public void execute() {
			for(int i=0;i<instructionMemory.size();i++) {
				while(Fetch.isBusy) {
				}
				if(instructionMemory.get(i).equals("00000000000000000000000000000000"))
					break;
				Fetch.fetchInstruction();
				
			}
		}
		
		
		
		public static void ArrayToInstructionMemory(String input){
			PC="00000000000000000000000000000000";
			String[] lines=input.split("\n");
			System.out.println(lines[0]);
			String line="";
			int memLoc=0;
			for(int i=0;i<lines.length;i++) {
				line=InstructionToBinary(lines[i]);
				if(line.length()==32) {
					instructionMemory.set(memLoc, line);
					line="";
					memLoc++;
				}
				if(line.length()==16) {
					if(i+1!=lines.length) {
						String nextInst=InstructionToBinary(lines[i+1]);
						i++;
						if(nextInst.length()==16) {
							line+=nextInst;
							instructionMemory.set(memLoc, line);
						
							memLoc++;
						}
						if(nextInst.length()==32) {
							line+="0000000000000000";
							instructionMemory.set(memLoc, line);
							instructionMemory.set(memLoc+1, nextInst);
							memLoc+=2;						}
					}else {
						
						line+="0000000000000000";
						instructionMemory.set(memLoc, line);
					}
				}
			}
			//System.out.println(instructionMemory.toString());
		}
		
		public static String RegisterToBinary(String regName) {
			regName=regName.toLowerCase();
			switch(regName) {
			case "#1":return "0000";
			case "#sp":return "0001";
			case "#gp":return "0010";
			case "#i0":return "0011";
			case "#i1":return "0100";
			case "#i2":return "0101";
			case "#s0":return "0110";
			case "#s1":return "0111";
			case "#s2":return "1000";
			case "#s3":return "1001";
			case "#t0":return "1010";
			case "#t1":return "1011";
			case "#t2":return "1100";
			case "#t3":return "1101";
			case "#r0":return "1110";
			case "#r1":return "1111";
			default:return "0000";
			}
		}
		
		public static String OpcodeToBinary(String opcode) {
			opcode=opcode.toLowerCase();
			switch(opcode) {
			case "add":return "0000";
			case "sub":return "0001";
			case "div":return "0010";
			case "mod":return "0011";
			case "slt":return "0100";
			case "lw":return "0101";
			case "sw":return "0110";
			case "sll":return "0111";
			case "slr":return "1000";
			case "and":return "1001";
			case "or":return "1010";
			case "not":return "1011";
			case "xor":return "1100";
			case "muli":return "1101";
			case "beq":return "1110";
			case "j":return "1111";
			default:return "0000";
			}
		}
		
		public static String IntTo20BitBinary(String num) {
			String result;
			System.out.println("the number: "+num+"k");
			int i=0;
			String num2=num;
			num="";
			while(i<num2.length()&&num2.charAt(i)>='0'&&num2.charAt(i)<='9') {
				num+=num2.charAt(i);
				i++;
			}
			String res=Integer.toBinaryString(Integer.parseInt(num));
			if(res.length()>20) {
				result=res.substring(res.length()-20, res.length());
				return result;
			}
			while (res.length() < 20) {
				res="0"+res;
			}
			return res;
			
			
		}
		
		public static String InstructionToBinary(String instruction) {
			String BinaryInstruction="";
			String[] alone=instruction.split(" ");
			String opcode=OpcodeToBinary(alone[0]);
			if(alone.length==4) {
				if(alone[3].startsWith("#")) {
					BinaryInstruction=opcode+RegisterToBinary(alone[1])+RegisterToBinary(alone[2])+RegisterToBinary(alone[3]);
				}else {
					
					BinaryInstruction=opcode+RegisterToBinary(alone[1])+RegisterToBinary(alone[2])+IntTo20BitBinary(alone[3]);
				}
			}
			if(alone.length==3) {
				BinaryInstruction=opcode+RegisterToBinary(alone[1])+RegisterToBinary(alone[2])+"0000";
			}
			if(alone.length==2) {
				BinaryInstruction=opcode+"00000000"+IntTo20BitBinary(alone[1]);
			}
			return BinaryInstruction;
		}
		
		
		
		public static String printRegisters() {
			String s="";
			s+="PC: "+PC+"\n";
			s+="#1: "+registers[0]+"\n";
			s+="#sp: "+registers[1]+"\n";
			s+="#gp: "+registers[2]+"\n";
			s+="#i0: "+registers[3]+"\n";
			s+="#i1: "+registers[4]+"\n";
			s+="#i2: "+registers[5]+"\n";
			s+="#s0: "+registers[6]+"\n";
			s+="#s1: "+registers[7]+"\n";
			s+="#s2: "+registers[8]+"\n";
			s+="#s3: "+registers[9]+"\n";
			s+="#t0: "+registers[10]+"\n";
			s+="#t1: "+registers[11]+"\n";
			s+="#t2: "+registers[12]+"\n";
			s+="#t3: "+registers[13]+"\n";
			s+="#r0: "+registers[14]+"\n";
			s+="#r1: "+registers[15]+"\n";
			s+="IF/ID: "+pipeLine[0]+"\n";
			s+="ID/EX:"+pipeLine[1]+"\n";
			s+="EX/M:"+pipeLine[2]+"\n";
			s+="M/WB: "+pipeLine[3]+"\n";
			return s;
		}
}
