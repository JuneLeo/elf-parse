package com.elf.parse;

import com.elf.parse.section.SHFlag;
import com.elf.parse.section.SHType;
import com.elf.parse.section.SectionElement;

import java.util.ArrayList;
import java.util.List;

public class ElfSectionHeaderParse implements Parse {

    private long offset;
    private int entrySize;
    private int num;
    private int strIndex;

    private final List<SectionElement> sectionElementList = new ArrayList<>();

    public ElfSectionHeaderParse(long eShoff, int eShEntrySize, int eShNum, int eShStrIndex) {
        this.offset = eShoff;
        this.entrySize = eShEntrySize;
        this.num = eShNum;
        this.strIndex = eShStrIndex;
    }

    ElfSectionSymTabParse elfSectionSymTabParse;
    ElfSectionDynSymParse elfSectionDynSymParse;

    @Override
    public int parse(long s, byte[] bytes) {
        int start = (int) s;
        for (int i = 0; i < num; i++) {
            SectionElement sectionElement = new SectionElement();
            sectionElement.nameOffset = Utils.getU4Int(start, bytes);
            start += 4;
            int type = Utils.getU4Int(start, bytes);
            sectionElement.sType = SHType.getSHType(type);
            start += 4;
            long flag = Utils.getU8Long(start, bytes);
            sectionElement.sFlag = SHFlag.getSHFlag(flag);
            start += 8;
            sectionElement.sAddr = Utils.getU8Long(start, bytes);
            start += 8;
            sectionElement.sOffset = Utils.getU8Long(start, bytes);
            start += 8;
            sectionElement.sSize = Utils.getU8Long(start, bytes);
            start += 8;
            sectionElement.sLink = Utils.getU4Int(start, bytes);
            start += 4;
            sectionElement.sInfo = Utils.getU4Int(start, bytes);
            start += 4;
            sectionElement.sAddrAlign = Utils.getU8Long(start, bytes);
            start += 8;
            sectionElement.sEntrySize = Utils.getU8Long(start, bytes);
            start += 8;
            sectionElementList.add(sectionElement);
        }

        SectionElement sectionElement = sectionElementList.get(strIndex); //.shstrtab

        int sStrOffset = (int) sectionElement.sOffset;

        for (SectionElement element : sectionElementList) {
            element.name = Utils.getString(sStrOffset + element.nameOffset, bytes);
        }
        Utils.log("--------------- Section Header ---------------");
        for (int i = 0; i < sectionElementList.size(); i++) {
            Utils.log(i+ "," + sectionElementList.get(i));
        }



        for (SectionElement element : sectionElementList) {
            if (element.name == null) {
                continue;
            }
            switch (element.name) {
                case ".symtab":
                    SectionElement strTable = this.getSectionElement(".strtab");
                    if(strTable != null) {
                        elfSectionSymTabParse = new ElfSectionSymTabParse(element.sOffset, element.sSize, element.sEntrySize, strTable.sOffset);
                        elfSectionSymTabParse.parse(element.sOffset, bytes);
                    }
                    break;
                case ".dynsym":
                    SectionElement dynStr = this.getSectionElement(".dynstr");
                    if(dynStr != null) {
                        elfSectionDynSymParse = new ElfSectionDynSymParse(element.sOffset, element.sSize, element.sEntrySize, dynStr.sOffset);
                        elfSectionDynSymParse.parse(element.sOffset, bytes);
                    }
                    break;
            }
        }

        return 0;
    }

    private SectionElement getSectionElement(String s) {
        for (SectionElement sectionElement : sectionElementList) {
            if (s.equals(sectionElement.name)) {
                return sectionElement;
            }
        }
        return null;
    }


    public long getMethodAddress(String method) {
        if (elfSectionSymTabParse != null) {
            long methodAddress = elfSectionSymTabParse.getMethodAddress(method);
            if (methodAddress != 0) {
                return methodAddress;
            }
        }

        if (elfSectionDynSymParse != null) {
            long methodAddress = elfSectionDynSymParse.getMethodAddress(method);
            if (methodAddress != 0) {
                return methodAddress;
            }
        }

        return 0;
    }
}
