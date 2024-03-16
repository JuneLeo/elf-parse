package com.elf.parse;

import com.elf.parse.program.PHFlag;
import com.elf.parse.program.PHType;
import com.elf.parse.program.SegmentElement;

import java.util.ArrayList;
import java.util.List;

public class ElfProgramHeaderParse implements Parse {

    private int entrySize;
    private int num;
    private long offset;
    private final List<SegmentElement> segmentElementList = new ArrayList<>();

    public ElfProgramHeaderParse(long ePhOffset, int ePhEntrySize, int ePhNum) {
        this.offset = ePhOffset;
        this.entrySize = ePhEntrySize;
        this.num = ePhNum;
    }

    @Override
    public int parse(long s, byte[] bytes) {
        int start = (int) s;
        for (int i = 0; i < num; i++) {
            SegmentElement segmentElement = new SegmentElement();
            int type = Utils.getU4Int(start, bytes);
            segmentElement.pType = PHType.getPHType(type);
            start += 4;
            int flag = Utils.getU4Int(start, bytes);
            segmentElement.pFlags = PHFlag.getSHFlag(flag);
            start += 4;
            segmentElement.pOffset = Utils.getU8Long(start, bytes);
            start += 8;
            segmentElement.pVirtualAddr = Utils.getU8Long(start, bytes);
            start += 8;
            segmentElement.pPhysicalAddr = Utils.getU8Long(start, bytes);
            start += 8;
            segmentElement.pFiles = Utils.getU8Long(start, bytes);
            start += 8;
            segmentElement.pMems = Utils.getU8Long(start, bytes);
            start += 8;
            segmentElement.pAlign = Utils.getU8Long(start, bytes);
            start += 8;
            segmentElementList.add(segmentElement);
        }


        System.out.println("--------------- Program Header ---------------");
        for (int i = 0; i < segmentElementList.size(); i++) {
            System.out.println(i + "," + segmentElementList.get(i));
        }


        return 0;
    }
}
