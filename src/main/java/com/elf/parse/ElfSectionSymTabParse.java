package com.elf.parse;

import com.elf.parse.section.SymTabElement;

import java.util.ArrayList;
import java.util.List;

public class ElfSectionSymTabParse implements Parse {
    private long sOffset;
    private long size;
    private long entrySize;
    private long strOffset;

    private final List<SymTabElement> symTabElementList = new ArrayList<>();

    public ElfSectionSymTabParse(long sOffset, long sSize, long sEntrySize, long strOffset) {
        this.sOffset = sOffset;
        this.size = sSize;
        this.entrySize = sEntrySize;
        this.strOffset = strOffset;
    }

    @Override
    public int parse(long s, byte[] bytes) {
        System.out.println("-------------ElfSymTabParse-------------");
        int start = (int) s;
        int length = (int) (size / entrySize);

        for (int i = 0; i < length; i++) {
            int begin = start;
            SymTabElement symTabElement = new SymTabElement();
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
            symTabElementList.add(symTabElement);
        }
        for (SymTabElement symTabElement : symTabElementList) {
            symTabElement.name = Utils.getString((int) (strOffset + symTabElement.symNameOff), bytes);
        }

        for (int i = 0; i < symTabElementList.size(); i++) {
            SymTabElement symTabElement = symTabElementList.get(i);
//            if(symTabElement.name != null && symTabElement.name.contains("fork")) {
                System.out.println(i + "," + symTabElementList.get(i).toString());
//            }
        }


        return 0;
    }

    public long getMethodAddress(String method) {
        for (SymTabElement symTabElement : symTabElementList) {
            if (method.equals(symTabElement.name)) {
                return symTabElement.symValue;
            }
        }
        return 0;
    }
}
