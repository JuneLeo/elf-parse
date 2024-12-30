package com.elf.parse;

import com.elf.parse.section.SectionElement;

public class MainParse implements Parse{


    ElfHeaderParse elfHeaderParse = new ElfHeaderParse();
    ElfProgramHeaderParse elfProgramHeaderParse;

    ElfSectionHeaderParse elfSectionHeaderParse;

    ElfSectionSymTabParse elfSectionSymTabParse;
    ElfSectionDynSymParse elfSectionDynSymParse;
    @Override
    public void parse(long start, byte[] bytes) {
        elfHeaderParse = new ElfHeaderParse();
        elfHeaderParse.parse(start, bytes);

        elfProgramHeaderParse = new ElfProgramHeaderParse(elfHeaderParse.ePhoff, elfHeaderParse.ePhEntrySize, elfHeaderParse.ePhNum);
        elfProgramHeaderParse.parse(elfHeaderParse.ePhoff, bytes);

        elfSectionHeaderParse = new ElfSectionHeaderParse(elfHeaderParse.eShoff, elfHeaderParse.eShEntrySize, elfHeaderParse.eShNum, elfHeaderParse.e_ShStrIndex);
        elfSectionHeaderParse.parse(elfHeaderParse.eShoff, bytes);


        for (SectionElement element : elfSectionHeaderParse.sectionElementList) {
            if (element.name == null) {
                continue;
            }
            switch (element.name) {
                case ".symtab":
                    SectionElement strTable = elfSectionHeaderParse.getSectionElement(".strtab");
                    if (strTable != null) {
                        elfSectionSymTabParse = new ElfSectionSymTabParse(element.sOffset, element.sSize, element.sEntrySize, strTable.sOffset);
                        elfSectionSymTabParse.parse(element.sOffset, bytes);
                    }
                    break;
                case ".dynsym":
                    SectionElement dynStr = elfSectionHeaderParse.getSectionElement(".dynstr");
                    if (dynStr != null) {
                        elfSectionDynSymParse = new ElfSectionDynSymParse(element.sOffset, element.sSize, element.sEntrySize, dynStr.sOffset);
                        elfSectionDynSymParse.parse(element.sOffset, bytes);
                    }
                    break;
            }
        }
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
