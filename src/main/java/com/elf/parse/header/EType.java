package com.elf.parse.header;

import com.elf.parse.eident.EiClass;

public enum EType {
    ET_NONE,
    ET_REL,
    ET_EXEC,
    ET_DYN,
    ET_CORE,
    ET_LOOS,
    ET_SIE,
    ET_HIOS,
    ET_LOPROC,
    ET_HIPROC;


    public static EType getEType(int value) {
        switch (value) {
            case 0x0:
                return ET_NONE;
            case 0x1:
                return ET_REL;
            case 2:
                return ET_EXEC;
            case 3:
                return ET_DYN;


            case 0x4:
                return ET_CORE;
            case 0xfe00:
                return ET_LOOS;
            case 0xfe18:
                return ET_SIE;
            case 0xfeff:
                return ET_HIOS;
            case 0xff00:
                return ET_LOPROC;
            case 0xffff:
                return ET_HIPROC;
        }
        return null;
    }
}
