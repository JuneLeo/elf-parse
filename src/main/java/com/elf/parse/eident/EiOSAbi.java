package com.elf.parse.eident;

public enum EiOSAbi {

    ELFOSABI_NONE,    //No extensions or unspecified
    ELFOSABI_HPUX,    //Hewlett-Packard HP-UX
    ELFOSABI_NETBSD,    //NetBSD
    ELFOSABI_LINUX,    //Linux
    ELFOSABI_SOLARIS,    //Sun Solaris
    ELFOSABI_AIX,    //AIX
    ELFOSABI_IRIX,    //IRIX
    ELFOSABI_FREEBSD,    //FreeBSD
    ELFOSABI_TRU64,    //Compaq TRU64 UNIX
    ELFOSABI_MODESTO,    //Novell Modesto
    ELFOSABI_OPENBSD,    //Open BSD
    ELFOSABI_OPENVMS,    //Open VMS
    ELFOSABI_NSK,    //Hewlett-Packard Non-Stop Kernel
    ELFOSABI_AROS,    //Amiga Research OS
    ELFOSABI_ARM_AEABI,   //ARM EABI
    ELFOSABI_ARM,   //ARM
    ELFOSABI_STANDALONE;

    public static EiOSAbi getEiOSAbi(int value) {
        switch (value) {
            case 0x0:
                return ELFOSABI_NONE;
            case 0x1:
                return ELFOSABI_HPUX;
            case 0x2:
                return ELFOSABI_NETBSD;
            case 0x3:
                return ELFOSABI_LINUX;
            case 0x6:
                return ELFOSABI_SOLARIS;
            case 0x7:
                return ELFOSABI_AIX;
            case 0x8:
                return ELFOSABI_IRIX;
            case 0x9:
                return ELFOSABI_FREEBSD;
            case 0xA:
                return ELFOSABI_TRU64;
            case 0xB:
                return ELFOSABI_MODESTO;
            case 0xC:
                return ELFOSABI_OPENBSD;
            case 0xD:
                return ELFOSABI_OPENVMS;
            case 0xE:
                return ELFOSABI_NSK;
            case 0xF:
                return ELFOSABI_AROS;
            case 0x40:
                return ELFOSABI_ARM_AEABI;
            case 0x61:
                return ELFOSABI_ARM;
            case 0xFF:
                return ELFOSABI_STANDALONE;

        }
        return null;
    }
}
