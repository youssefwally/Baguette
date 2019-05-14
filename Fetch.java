package brownBaguette;

import java.util.ArrayList;

public class Fetch {

	static Boolean isBusy=false;

	public static void fetchInstruction() {
		isBusy=true;
		String x = Main.PC;
		int y = Integer.parseInt(x, 2);
		String data = Main.instructionMemory.get(y);
		y = y + 1;
		String pc = Integer.toBinaryString(y);
		while (pc.length() < 32) {
			pc = "0" + pc;
		}
		String instructionPlusOpcode = data + "" + pc;

		System.out.println(instructionPlusOpcode);
		Main.pipeLine[0]=instructionPlusOpcode;
		while(Decode.isBusy) {
		}
		isBusy=false;
		Decode.decode();

	}

}