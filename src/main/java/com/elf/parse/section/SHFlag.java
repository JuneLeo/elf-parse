package com.elf.parse.section;

public enum SHFlag {
    SF64_None,
    SF64_Exec,
    SF64_Alloc,
    SF64_Alloc_Exec,
    SF64_Write,
    SF64_Write_Exec,
    SF64_Write_Alloc,
    SF64_Write_Alloc_Exec;


    public static SHFlag getSHFlag(long value) {
        switch ((int) value) {
            case 0x0:
                return SF64_None;
            case 0x1:
                return SF64_Exec;
            case 0x2:
                return SF64_Alloc;
            case 0x3:
                return SF64_Alloc_Exec;
            case 0x4:
                return SF64_Write;
            case 0x5:
                return SF64_Write_Exec;
            case 0x6:
                return SF64_Write_Alloc;
            case 0x7:
                return SF64_Write_Alloc_Exec;

        }
        return null;
    }
}
