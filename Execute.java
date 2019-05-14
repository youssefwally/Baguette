package brownBaguette;

public class Execute {

	static Boolean isBusy=false;
	
	public static void execute() {
		isBusy=true;
		String entry = Main.pipeLine[1];
		String result = "";
		String alu1 = "";
		String alu2 = "";
		String pcIfBranch = "";

		String data_1 = entry.substring(0, 32);// 32
		String data_2 = entry.substring(32, 64);
		;// 32
		String data_3 = entry.substring(64, 96);
		;// 32
		String data_4 = entry.substring(96, 128);
		;// 32
		String extended_immediate = entry.substring(128, 160);
		;// 32
		String write_register_1 = entry.substring(160, 164);
		; // 4
		String write_register_2 = entry.substring(164, 168);
		; // 4
		String pc = entry.substring(168, 200);
		;// 32
		String fourbits_opcode_1 = entry.substring(200, 204);
		;
		String fourbits_opcode_2 = entry.substring(204, 208);
		;
		String ImmediateUNFormat=entry.substring(211,212);
		String Zero="0";
		if(data_1.equals(data_2))
			Zero="1";

System.out.println("opcode1: "+fourbits_opcode_1);
		int data1int = (int) Long.parseLong(data_1, 2);
		int data2int = (int) Long.parseLong(data_2, 2);
		System.out.println("data2int: "+data2int);
		int data3int = (int) Long.parseLong(data_3, 2);
		int data4int = (int) Long.parseLong(data_4, 2);
		int extendedimediateint = (int) Long.parseLong(extended_immediate, 2);
		int pcint = Integer.parseInt(pc, 2);
		int pcIfBranchInteger = pcint + extendedimediateint;
		pcIfBranch=bitextender(Integer.toBinaryString(pcIfBranchInteger));

		switch (fourbits_opcode_1) {
		// add
		case ("0000"): {
			int temp = data1int + data2int;
			alu1 += temp;
			break;
		}
		// sub
		case ("0001"): {
			int temp = data1int - data2int;
			alu1 += temp;
			break;
		}
		// div
		case ("0010"): {
			int temp = data1int / data2int;
			alu1 += temp;
			break;
		}

		// mod
		case ("0011"): {
			int temp = data1int / data2int;
			int temp2 = temp * data2int;
			int temp3 = temp - temp2;
			alu1 += temp3;
			break;
		} // slt
		case ("0100"): {
			int temp;
			if (data1int < data2int) {
				temp = 1;
			} else {
				temp = 0;
			}
			alu1 += temp;
			break;
		}
		// lw
		// hena
//		case ("0101"): {
////			int temp = data1int - data2int;
////			alu1 += temp;
//		}
//		// sw
//		case ("0110"): {
//			int temp = data1int - data2int;
//			alu1 += temp;
//		}
		// sll
		case ("0111"): {
			// data1int - data2int;

//			alu1 += temp;
			alu1 += shiftlogialLeft(data_1, data2int);
			break;
		}
		// slr
		case ("1000"): {
			alu1 += shiftlogialright(data_1, data2int);
			break;
		}
		// and
		case ("1001"): {
			alu1 += andbitwise(data_1, data_2);
			break;
		}
		// or
		case ("1010"): {
			alu1 += orbitwise(data_1, data_2);
			break;
		}
		// not
		case ("1011"): {
			alu1 += notbitwise(data_1);
			break;
		}
		// xor
		case ("1100"): {
			alu1 += xorbitwise(data_1, data_2);
			break;
		}
		case ("1101"): {
			alu1 += data2int * extendedimediateint;
			break;
		}

		}
		if(ImmediateUNFormat.equals("0"))
		switch (fourbits_opcode_2) {
		// add
		case ("0000"): {
			int temp = data3int + data4int;
			alu2 += temp;
			break;
		}
		// sub
		case ("0001"): {
			int temp = data3int - data4int;
			alu2 += temp;
			break;
		}
		// div
		case ("0010"): {
			int temp = data3int / data4int;
			alu2 += temp;
			break;
		}

		// mod
		case ("0011"): {
			int temp = data3int / data4int;
			int temp2 = temp * data4int;
			int temp3 = temp - temp2;
			alu2 += temp3;
			break;
		} // slt
		case ("0100"): {
			int temp;
			if (data3int < data4int) {
				temp = 1;
			} else {
				temp = 0;
			}
			alu2 += temp;
			break;
		}
		// lw
		// hena
//		case ("0101"): {
////			int temp = data1int - data2int;
////			alu1 += temp;
//		}
//		// sw
//		case ("0110"): {
//			int temp = data1int - data2int;
//			alu1 += temp;
//		}
		// sll
		case ("0111"): {
			// data1int - data2int;

//			alu1 += temp;
			alu2 += shiftlogialLeft(data_3, data4int);
			break;
		}
		// slr
		case ("1000"): {
			alu2 += shiftlogialright(data_3, data4int);
			break;
		}
		// and
		case ("1001"): {
			alu2 += andbitwise(data_3, data_4);
			break;
		}
		// or
		case ("1010"): {
			alu2 += orbitwise(data_3, data_4);
			break;
		}
		// not
		case ("1011"): {
			alu2 += notbitwise(data_3);
			break;
		}
		// xor
		case ("1100"): {
			alu2 += xorbitwise(data_3, data_4);
			break;
		}

		}
		System.out.println(alu1);
		String alut="0";
		String alut2 ="0";
		if(!fourbits_opcode_1.equals("1111")&&!fourbits_opcode_1.equals("1110")) {
		alut = Integer.toBinaryString(Integer.parseInt(alu1));
		if(!fourbits_opcode_1.equals("1101"))
		alut2 = Integer.toBinaryString(Integer.parseInt(alu2));
		}
		
		
		String alu1extended = bitextender(alut);
		String alu2extended = bitextender(alut2);

		result += pc;
		result += alu1extended;
		result += alu2extended;
		result += pcIfBranch;
		result += write_register_1;
		result += write_register_2;
		result += data_2;
		result += entry.substring(208,211)+entry.substring(212,entry.length())+Zero;

		Main.pipeLine[2]=result;
		while(MemoryRW.isBusy) {
		}
		isBusy=false;
		MemoryRW.memory();
	}

	public static String bitextender(String entry) {
		String result = "";
		int length = entry.length();
		int diifrence = 32 - length;
		for (int i = 0; i < diifrence; i++) {
			result += 0;
		}
		result += entry;

		return result;
	}

	public static int shiftlogialLeft(String x, int value) {
		String result = "";
		String temp = "";
		int i = 0;
		while (i < value) {
			temp += 0;
			i++;
		}
		int j;
		for (j = value; j < x.length(); j++) {
			result += x.charAt(j);
		}
		result += temp;

		int resultfinal = (int) Long.parseLong(result, 2);
		return resultfinal;
	}

	public static int shiftlogialright(String x, int value) {
		String result = "";
		String temp = "";
		int i = 0;
		while (i < value) {
			temp += 0;
			i++;
		}
		int j;
		for (j = 0; j < x.length() - value; j++) {
			result += x.charAt(j);
		}
		temp += result;

		int resultfinal = (int) Long.parseLong(temp, 2);
		return resultfinal;
	}

	public static int andbitwise(String x, String y) {
		String result = "";
		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) == y.charAt(i) && x.charAt(i) == '1') {
				result += 1;
			} else {
				result += 0;
			}
		}

		int resultfinal = (int) Long.parseLong(result, 2);
		return resultfinal;
	}

	public static int orbitwise(String x, String y) {
		String result = "";
		for (int i = 0; i < x.length(); i++) {
			if (y.charAt(i) == '1' || x.charAt(i) == '1') {
				result += 1;
			} else {
				result += 0;
			}
		}

		int resultfinal = (int) Long.parseLong(result, 2);
		return resultfinal;
	}

	public static int notbitwise(String x) {
		String result = "";
		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) == '1') {
				result += 0;
			} else {
				result += 1;
			}
		}

		int resultfinal = (int) Long.parseLong(result, 2);
		return resultfinal;
	}

	public static int xorbitwise(String x, String y) {
		String result = "";
		for (int i = 0; i < x.length(); i++) {
			if (y.charAt(i) == '1' && x.charAt(i) == '1') {
				result += 0;
			} else {
				if (y.charAt(i) == '1' && x.charAt(i) == '0') {
					result += 1;
				} else {
					if (y.charAt(i) == '0' && x.charAt(i) == '1') {
						result += 1;
					}
				}
			}
		}

		int resultfinal = (int) Long.parseLong(result, 2);
		return resultfinal;
	}

}