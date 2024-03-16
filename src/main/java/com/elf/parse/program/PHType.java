package com.elf.parse.program;

public enum PHType {
    PT_NULL,
    PT_LOAD,
    PT_DYNAMIC,
    PT_INERP,
    PT_NOTE,
    PT_SHLIB,
    PT_PHDR,
    PT_TLS,
    PT_NUM,
    PT_LOOS,
    PT_GNU_EH_FRAME,
    PT_GNU_STACK,
    PT_GNU_RELRO,
    //    PT_LOSUNW,
//    PT_SUNWBSS,
    LOSUNW_SUNWBSS,
    PT_SUNWSTACK,
    //    PT_HISUNW,
//    PT_HIOS,
    HISUNW_HIOS,
    PT_LOPROC,
    PT_HIPROC,
    // ARM Sections
    PT_SHT_ARM_EXIDX,
    PT_SHT_ARM_PREEMPTMAP,
    PT_SHT_ARM_ATTRIBUTES,
    PT_SHT_ARM_DEBUGOVERLAY,
    PT_SHT_ARM_OVERLAYSECTION;


    public static PHType getPHType(int type) {
        switch (type) {
            case 0x0:
                return PT_NULL;
            case 0x1:
                return PT_LOAD;
            case 0x2:
                return PT_DYNAMIC;
            case 0x3:
                return PT_INERP;
            case 0x4:
                return PT_NOTE;
            case 0x5:
                return PT_SHLIB;
            case 0x6:
                return PT_PHDR;
            case 0x7:
                return PT_TLS;
            case 0x8:
                return PT_NUM;
            case 0x60000000:
                return PT_LOOS;
            case 0x6474e550:
                return PT_GNU_EH_FRAME;
            case 0x6474e551:
                return PT_GNU_STACK;
            case 0x6474e552:
                return PT_GNU_RELRO;
//            case 0x6ffffffa:
//                return PT_LOSUNW;
//            case 0x6ffffffa:
//            return PT_SUNWBSS;
            case 0x6ffffffa:
                return LOSUNW_SUNWBSS;
            case 0x6ffffffb:
                return PT_SUNWSTACK;
//            case 0x6fffffff:
//                return PT_HISUNW;
//            case 0x6fffffff:
//                return PT_HIOS;
            case 0x6fffffff:
                return HISUNW_HIOS;
            case 0x70000000:
                return PT_LOPROC;
            case 0x7fffffff:
                return PT_HIPROC;
            case 0x70000001:
                return PT_SHT_ARM_EXIDX;
            case 0x70000002:
                return PT_SHT_ARM_PREEMPTMAP;
            case 0x70000003:
                return PT_SHT_ARM_ATTRIBUTES;
            case 0x70000004:
                return PT_SHT_ARM_DEBUGOVERLAY;
            case 0x70000005:
                return PT_SHT_ARM_OVERLAYSECTION;

        }
        return null;
    }
}
