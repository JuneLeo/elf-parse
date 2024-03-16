package com.elf.parse.section;

public enum SHType {

    SHT_NULL, /* Inactive section header */
    SHT_PROGBITS, /* Information defined by the program */
    SHT_SYMTAB, /* Symbol table - not DLL */
    SHT_STRTAB, /* String table */
    SHT_RELA, /* Explicit addend relocations, Elf64_Rela */
    SHT_HASH, /* Symbol hash table */
    SHT_DYNAMIC, /* Information for dynamic linking */
    SHT_NOTE, /* A Note section */
    SHT_NOBITS, /* Like SHT_PROGBITS with no data */
    SHT_REL, /* Implicit addend relocations, Elf64_Rel */
    SHT_SHLIB, /* Currently unspecified semantics */
    SHT_DYNSYM, /* Symbol table for a DLL */
    SHT_INIT_ARRAY, /* Array of constructors */
    SHT_FINI_ARRAY, /* Array of deconstructors */
    SHT_PREINIT_ARRAY, /* Array of pre-constructors */
    SHT_GROUP, /* Section group */
    SHT_SYMTAB_SHNDX, /* Extended section indeces */
    SHT_NUM, /* Number of defined types */
    SHT_LOOS, /* Lowest OS-specific section type */
    SHT_GNU_ATTRIBUTES, /* Object attribuytes */
    SHT_GNU_HASH, /* GNU-style hash table */
    SHT_GNU_LIBLIST, /* Prelink library list */
    SHT_CHECKSUM, /* Checksum for DSO content */
    SHT_LOSUNW, /* Sun-specific low bound */
    SHT_SUNW_move, // Same thing
    SHT_SUNW_COMDAT,
    SHT_SUNW_syminfo,
    SHT_GNU_verdef, /* Version definition section */
    SHT_GNU_verdneed, /* Version needs section */
//    SHT_GNY_versym, /* Version symbol table */
//    SHT_HISUNW, /* Sun-specific high bound */
//    SHT_HIOS, /* Highest OS-specific section type */
    GNY_versym_HISUNW_HIOS,
    SHT_LOPROC, /* Start of processor-specific section type */
    SHT_HIPROC, /* End of processor-specific section type */
    SHT_LOUSER, /* Start of application-specific */
    SHT_HIUSER; /* Ennd of application-specific */


    public static SHType getSHType(int value) {
        switch (value) {
            case 0x0:
                return SHT_NULL;
            case 0x1:
                return SHT_PROGBITS;


            case 0x2:
                return SHT_SYMTAB;
            case 0x3:
                return SHT_STRTAB;
            case 0x4:
                return SHT_RELA;
            case 0x5:
                return SHT_HASH;
            case 0x6:
                return SHT_DYNAMIC;
            case 0x7:
                return SHT_NOTE;
            case 0x8:
                return SHT_NOBITS;
            case 0x9:
                return SHT_REL;
            case 0xA:
                return SHT_SHLIB;
            case 0xB:
                return SHT_DYNSYM;
            case 0xE:
                return SHT_INIT_ARRAY;
            case 0xF:
                return SHT_FINI_ARRAY;
            case 0x10:
                return SHT_PREINIT_ARRAY;
            case 0x11:
                return SHT_GROUP;
            case 0x12:
                return SHT_SYMTAB_SHNDX;
            case 0x13:
                return SHT_NUM;
            case 0x60000000:
                return SHT_LOOS;
            case 0x6ffffff5:
                return SHT_GNU_ATTRIBUTES;
            case 0x6ffffff6:
                return SHT_GNU_HASH;
            case 0x6ffffff7:
                return SHT_GNU_LIBLIST;
            case 0x6ffffff8:
                return SHT_CHECKSUM;
            case 0x6ffffff9:
                return SHT_LOSUNW;
            case 0x6ffffffa:
                return SHT_SUNW_move;
            case 0x6ffffffb:
                return SHT_SUNW_COMDAT;
            case 0x6ffffffc:
                return SHT_SUNW_syminfo;
            case 0x6ffffffd:
                return SHT_GNU_verdef;
            case 0x6ffffffe:
                return SHT_GNU_verdneed;
//            case 0x6fffffff:
//                return SHT_GNY_versym;
//            case 0x6fffffff:
//                return SHT_HISUNW;
//            case 0x6fffffff:
//                return SHT_HIOS;
            case 0x6fffffff:
                return GNY_versym_HISUNW_HIOS;
            case 0x70000000:
                return SHT_LOPROC;
            case 0x80000000:
                return SHT_LOUSER;

            case 0x8fffffff:
                return SHT_HIUSER;


        }
        return null;
    }
}
