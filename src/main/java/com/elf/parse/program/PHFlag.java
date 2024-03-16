package com.elf.parse.program;

import com.elf.parse.section.SHFlag;

public enum PHFlag {

    PF_None,
    PF_Exec,
    PF_Write,
    PF_Write_Exec,
    PF_Read,
    PF_Read_Exec,
    PF_Read_Write,
    PF_Read_Write_Exec;


    public static PHFlag getSHFlag(long value) {
        switch ((int) value) {
            case 0x0:
                return PF_None;
            case 0x1:
                return PF_Exec;
            case 0x2:
                return PF_Write;
            case 0x3:
                return PF_Write_Exec;
            case 0x4:
                return PF_Read;
            case 0x5:
                return PF_Read_Exec;
            case 0x6:
                return PF_Read_Write;
            case 0x7:
                return PF_Read_Write_Exec;

        }
        return null;
    }
}
