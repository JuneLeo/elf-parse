package com.elf.parse;

import com.elf.parse.section.DynSymElement;

import java.util.ArrayList;
import java.util.List;

public class ElfSectionDynSymParse implements Parse {
    private long sOffset;
    private long size;
    private long entrySize;
    private long strOffset;

    private final List<DynSymElement> dynSymElementList = new ArrayList<>();

    public ElfSectionDynSymParse(long sOffset, long sSize, long sEntrySize, long strOffset) {
        this.sOffset = sOffset;
        this.size = sSize;
        this.entrySize = sEntrySize;
        this.strOffset = strOffset;
    }

    @Override
    public int parse(long s, byte[] bytes) {
        System.out.println("-----------ElfDynSymParse-------------");
        int start = (int) s;
        int length = (int) (size / entrySize);

        for (int i = 0; i < length; i++) {
            int begin = start;
            DynSymElement symTabElement = new DynSymElement();
            symTabElement.symNameOff = Utils.getU4Int(begin, bytes);
            begin += 4;
            symTabElement.symInfo = Utils.getU1Int(begin, bytes);
            begin += 1;
            symTabElement.symOther = Utils.getU1Int(begin, bytes);
            begin += 1;
            symTabElement.symShndx = Utils.getU2Int(begin, bytes);
            begin += 2;
            symTabElement.symValue = Utils.getU8Long(begin, bytes);
            begin += 8;
            symTabElement.symSize = Utils.getU8Long(begin, bytes);
            start += entrySize;
            dynSymElementList.add(symTabElement);
        }
        for (DynSymElement symTabElement : dynSymElementList) {
            symTabElement.name = Utils.getString((int) (strOffset + symTabElement.symNameOff), bytes);
        }

        for (int i = 0; i < dynSymElementList.size(); i++) {
            DynSymElement dynSymElement = dynSymElementList.get(i);
            if(dynSymElement.name != null && dynSymElement.name.contains("fork")) {
                System.out.println(i + "," + dynSymElementList.get(i));
            }
        }



        return 0;
    }

    public long getMethodAddress(String method) {
        for (DynSymElement symTabElement : dynSymElementList) {
            if (method.equals(symTabElement.name)) {
                return symTabElement.symValue;
            }
        }
        return 0;
    }
}
