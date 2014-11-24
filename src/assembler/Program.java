package assembler;

import java.util.ArrayList;

import utilities.CacheDetailsHolder;
import instructions.Instruction;
import memory.Memory;

public class Program {
	Instruction[] instructions;
	Memory memory;
	int startAddress;
	
	public Program(String code, int startAddress, ArrayList<CacheDetailsHolder> caches, int mainMemoryAccessTime) {
		String[] lines = code.split("\n");
		instructions = Assembler.assembleProgram(lines);
		memory = Memory.getInstance();
		memory.initialize(caches, mainMemoryAccessTime);
		int address = startAddress;
		for (int i=0; i<instructions.length; i++) {
			memory.setMemoryValue(address, instructions[i].getMachineCode(), true);
			address += 4;
		}
	}
}
