package com.elf.parse;

import com.elf.parse.section.SymInfo;
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
        Utils.log("-------------ElfSymTabParse-------------");
        int start = (int) s;
        int length = (int) (size / entrySize);

        for (int i = 0; i < length; i++) {
            int begin = start;
            SymTabElement symTabElement = new SymTabElement();
            symTabElement.symNameOff = Utils.getU4Int(begin, bytes);
            begin += 4;
            symTabElement.symInfo = new SymInfo(Utils.getU1Int(begin, bytes));
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
            //
            Utils.log(i + "," + symTabElement);
        }

        /**
         * 102228: 0000000000ebc420 392 OBJECT LOCAL DEFAULT 20 vtable for amap::vcs::VCSManagerStateForListening
         * 4809: 0000000000f060c0 24 OBJECT LOCAL DEFAULT 24 amap::vcs::RING_STATE
         * 404: 0000000000000000 0 OBJECT GLOBAL DEFAULT UND std::__ndk1::num_put<wchar_t, std::__ndk1::ostreambuf_iterator<wchar_t, std::__ndk1::char_traits<wchar_t> > >::id
         * 503: 0000000000000000 0 OBJECT GLOBAL DEFAULT ABS FOO
         * 107643,SymTabElement{symNameOff=5083295, name='FOO', symInfo=17, symOther=0, symShndx=65521, symValue=0, symSize=0}
         *15
         * 88833,SymTabElement{symNameOff=3297781, name='_ZN4amap3vcs10RING_STATEE', symInfo=1, symOther=0, symShndx=24, symValue=15597304, symSize=24}
         * 77121,SymTabElement{symNameOff=2171578, name='_ZN4amap3vcs27VCSManagerStateForListeningD0Ev', symInfo=2, symOther=0, symShndx=12, symValue=1677572, symSize=52}
         */


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
