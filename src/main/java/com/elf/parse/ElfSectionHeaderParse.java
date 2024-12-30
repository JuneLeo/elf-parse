package com.elf.parse;

import com.elf.parse.section.SHFlag;
import com.elf.parse.section.SHType;
import com.elf.parse.section.SectionElement;

import java.util.ArrayList;
import java.util.List;

public class ElfSectionHeaderParse implements Parse {

    public long offset;
    public int entrySize;
    public int num;
    public int strIndex;

    public final List<SectionElement> sectionElementList = new ArrayList<>();

    public ElfSectionHeaderParse(long eShoff, int eShEntrySize, int eShNum, int eShStrIndex) {
        this.offset = eShoff;
        this.entrySize = eShEntrySize;
        this.num = eShNum;
        this.strIndex = eShStrIndex;
    }

    @Override
    public void parse(long s, byte[] bytes) {
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

    }

    public SectionElement getSectionElement(String s) {
        for (SectionElement sectionElement : sectionElementList) {
            if (s.equals(sectionElement.name)) {
                return sectionElement;
            }
        }
        return null;
    }
}
