# ELF解析器

目前只解析了符号表和动态符号表

```
Elf Header:
Eident content
Magic：ELF
ELFCLASS64
编码方式：ELFDATA2LSB（大端/小端）
E_CURRENT
ELFOSABI_NONE
文件类型：ET_DYN
结构类型：EM_AARCH64
目标文件版本：EV_CURRENT
程序入口地址：0
program header 偏移：64
section header 偏移：1922920
header size：64
program header entry结构 size：56
program header size：9
section header entry结构 size：64
section header size：34
section中string table index：32
--------------- Program Header ---------------
0,SegmentElement{pType=PT_PHDR, pFlags=PF_Read, pOffset=64, pVirtualAddr=64, pPhysicalAddr=64, pFiles=504, pMems=504, pAlign=8}
1,SegmentElement{pType=PT_LOAD, pFlags=PF_Read_Exec, pOffset=0, pVirtualAddr=0, pPhysicalAddr=0, pFiles=274192, pMems=274192, pAlign=4096}
2,SegmentElement{pType=PT_LOAD, pFlags=PF_Read_Write, pOffset=274192, pVirtualAddr=278288, pPhysicalAddr=278288, pFiles=14272, pMems=14272, pAlign=4096}
3,SegmentElement{pType=PT_LOAD, pFlags=PF_Read_Write, pOffset=288464, pVirtualAddr=296656, pPhysicalAddr=296656, pFiles=88, pMems=2848, pAlign=4096}
4,SegmentElement{pType=PT_DYNAMIC, pFlags=PF_Read_Write, pOffset=286728, pVirtualAddr=290824, pPhysicalAddr=290824, pFiles=464, pMems=464, pAlign=8}
5,SegmentElement{pType=PT_GNU_RELRO, pFlags=PF_Read, pOffset=274192, pVirtualAddr=278288, pPhysicalAddr=278288, pFiles=14272, pMems=16624, pAlign=1}
6,SegmentElement{pType=PT_GNU_EH_FRAME, pFlags=PF_Read, pOffset=93276, pVirtualAddr=93276, pPhysicalAddr=93276, pFiles=5444, pMems=5444, pAlign=4}
7,SegmentElement{pType=PT_GNU_STACK, pFlags=PF_Read_Write, pOffset=0, pVirtualAddr=0, pPhysicalAddr=0, pFiles=0, pMems=0, pAlign=0}
8,SegmentElement{pType=PT_NOTE, pFlags=PF_Read, pOffset=568, pVirtualAddr=568, pPhysicalAddr=568, pFiles=188, pMems=188, pAlign=4}
--------------- Section Header ---------------
0,SectionElement{nameOffset=0, name='null', sType=SHT_NULL, sFlag=SF64_None, sAddr=0, sOffset=0, sSize=0, sLink=0, sInfo=0, sAddrAlign=0, sEntrySize=0}
1,SectionElement{nameOffset=1, name='.note.android.ident', sType=SHT_NOTE, sFlag=SF64_Alloc, sAddr=568, sOffset=568, sSize=152, sLink=0, sInfo=0, sAddrAlign=4, sEntrySize=0}
2,SectionElement{nameOffset=21, name='.note.gnu.build-id', sType=SHT_NOTE, sFlag=SF64_Alloc, sAddr=720, sOffset=720, sSize=36, sLink=0, sInfo=0, sAddrAlign=4, sEntrySize=0}
3,SectionElement{nameOffset=40, name='.dynsym', sType=SHT_DYNSYM, sFlag=SF64_Alloc, sAddr=760, sOffset=760, sSize=14160, sLink=7, sInfo=1, sAddrAlign=8, sEntrySize=24}
4,SectionElement{nameOffset=48, name='.gnu.version', sType=GNY_versym_HISUNW_HIOS, sFlag=SF64_Alloc, sAddr=14920, sOffset=14920, sSize=1180, sLink=3, sInfo=0, sAddrAlign=2, sEntrySize=2}
5,SectionElement{nameOffset=61, name='.gnu.version_r', sType=SHT_GNU_verdneed, sFlag=SF64_Alloc, sAddr=16100, sOffset=16100, sSize=64, sLink=7, sInfo=2, sAddrAlign=4, sEntrySize=0}
6,SectionElement{nameOffset=76, name='.gnu.hash', sType=SHT_GNU_HASH, sFlag=SF64_Alloc, sAddr=16168, sOffset=16168, sSize=3640, sLink=3, sInfo=0, sAddrAlign=8, sEntrySize=0}
7,SectionElement{nameOffset=86, name='.dynstr', sType=SHT_STRTAB, sFlag=SF64_Alloc, sAddr=19808, sOffset=19808, sSize=18623, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
8,SectionElement{nameOffset=94, name='.rela.dyn', sType=SHT_RELA, sFlag=SF64_Alloc, sAddr=38432, sOffset=38432, sSize=34896, sLink=3, sInfo=0, sAddrAlign=8, sEntrySize=24}
9,SectionElement{nameOffset=104, name='.rela.plt', sType=SHT_RELA, sFlag=null, sAddr=73328, sOffset=73328, sSize=2928, sLink=3, sInfo=21, sAddrAlign=8, sEntrySize=24}
10,SectionElement{nameOffset=114, name='.rodata', sType=SHT_PROGBITS, sFlag=null, sAddr=76256, sOffset=76256, sSize=14012, sLink=0, sInfo=0, sAddrAlign=16, sEntrySize=0}
11,SectionElement{nameOffset=122, name='.gcc_except_table', sType=SHT_PROGBITS, sFlag=SF64_Alloc, sAddr=90268, sOffset=90268, sSize=3008, sLink=0, sInfo=0, sAddrAlign=4, sEntrySize=0}
12,SectionElement{nameOffset=140, name='.eh_frame_hdr', sType=SHT_PROGBITS, sFlag=SF64_Alloc, sAddr=93276, sOffset=93276, sSize=5444, sLink=0, sInfo=0, sAddrAlign=4, sEntrySize=0}
13,SectionElement{nameOffset=154, name='.eh_frame', sType=SHT_PROGBITS, sFlag=SF64_Alloc, sAddr=98720, sOffset=98720, sSize=23524, sLink=0, sInfo=0, sAddrAlign=8, sEntrySize=0}
14,SectionElement{nameOffset=164, name='.text', sType=SHT_PROGBITS, sFlag=SF64_Write_Alloc, sAddr=122256, sOffset=122256, sSize=149940, sLink=0, sInfo=0, sAddrAlign=16, sEntrySize=0}
15,SectionElement{nameOffset=170, name='.plt', sType=SHT_PROGBITS, sFlag=SF64_Write_Alloc, sAddr=272208, sOffset=272208, sSize=1984, sLink=0, sInfo=0, sAddrAlign=16, sEntrySize=0}
16,SectionElement{nameOffset=175, name='.data.rel.ro', sType=SHT_PROGBITS, sFlag=SF64_Alloc_Exec, sAddr=278288, sOffset=274192, sSize=12512, sLink=0, sInfo=0, sAddrAlign=8, sEntrySize=0}
17,SectionElement{nameOffset=188, name='.fini_array', sType=SHT_FINI_ARRAY, sFlag=SF64_Alloc_Exec, sAddr=290800, sOffset=286704, sSize=16, sLink=0, sInfo=0, sAddrAlign=8, sEntrySize=0}
18,SectionElement{nameOffset=200, name='.init_array', sType=SHT_INIT_ARRAY, sFlag=SF64_Alloc_Exec, sAddr=290816, sOffset=286720, sSize=8, sLink=0, sInfo=0, sAddrAlign=8, sEntrySize=0}
19,SectionElement{nameOffset=212, name='.dynamic', sType=SHT_DYNAMIC, sFlag=SF64_Alloc_Exec, sAddr=290824, sOffset=286728, sSize=464, sLink=7, sInfo=0, sAddrAlign=8, sEntrySize=16}
20,SectionElement{nameOffset=221, name='.got', sType=SHT_PROGBITS, sFlag=SF64_Alloc_Exec, sAddr=291288, sOffset=287192, sSize=272, sLink=0, sInfo=0, sAddrAlign=8, sEntrySize=0}
21,SectionElement{nameOffset=226, name='.got.plt', sType=SHT_PROGBITS, sFlag=SF64_Alloc_Exec, sAddr=291560, sOffset=287464, sSize=1000, sLink=0, sInfo=0, sAddrAlign=8, sEntrySize=0}
22,SectionElement{nameOffset=235, name='.data', sType=SHT_PROGBITS, sFlag=SF64_Alloc_Exec, sAddr=296656, sOffset=288464, sSize=88, sLink=0, sInfo=0, sAddrAlign=8, sEntrySize=0}
23,SectionElement{nameOffset=241, name='.bss', sType=SHT_NOBITS, sFlag=SF64_Alloc_Exec, sAddr=296752, sOffset=288552, sSize=2752, sLink=0, sInfo=0, sAddrAlign=16, sEntrySize=0}
24,SectionElement{nameOffset=246, name='.comment', sType=SHT_PROGBITS, sFlag=null, sAddr=0, sOffset=288552, sSize=177, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=1}
25,SectionElement{nameOffset=255, name='.debug_abbrev', sType=SHT_PROGBITS, sFlag=SF64_None, sAddr=0, sOffset=288729, sSize=18044, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
26,SectionElement{nameOffset=269, name='.debug_info', sType=SHT_PROGBITS, sFlag=SF64_None, sAddr=0, sOffset=306773, sSize=468106, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
27,SectionElement{nameOffset=281, name='.debug_ranges', sType=SHT_PROGBITS, sFlag=SF64_None, sAddr=0, sOffset=774879, sSize=142336, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
28,SectionElement{nameOffset=295, name='.debug_str', sType=SHT_PROGBITS, sFlag=null, sAddr=0, sOffset=917215, sSize=241215, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=1}
29,SectionElement{nameOffset=306, name='.debug_line', sType=SHT_PROGBITS, sFlag=SF64_None, sAddr=0, sOffset=1158430, sSize=159364, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
30,SectionElement{nameOffset=318, name='.debug_loc', sType=SHT_PROGBITS, sFlag=SF64_None, sAddr=0, sOffset=1317794, sSize=455604, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
31,SectionElement{nameOffset=329, name='.symtab', sType=SHT_SYMTAB, sFlag=SF64_None, sAddr=0, sOffset=1773400, sSize=69624, sLink=33, sInfo=2312, sAddrAlign=8, sEntrySize=24}
32,SectionElement{nameOffset=337, name='.shstrtab', sType=SHT_STRTAB, sFlag=SF64_None, sAddr=0, sOffset=1843024, sSize=355, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
33,SectionElement{nameOffset=347, name='.strtab', sType=SHT_STRTAB, sFlag=SF64_None, sAddr=0, sOffset=1843379, sSize=79540, sLink=0, sInfo=0, sAddrAlign=1, sEntrySize=0}
-----------ElfDynSymParse-------------
0,DynSymElement{symNameOff=0, name='null', symInfo=0, symOther=0, symShndx=0, symValue=0, symSize=0}
1,DynSymElement{symNameOff=1, name='__cxa_finalize', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
2,DynSymElement{symNameOff=16, name='__cxa_atexit', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
3,DynSymElement{symNameOff=29, name='__register_atfork', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
4,DynSymElement{symNameOff=65, name='__android_log_print', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
5,DynSymElement{symNameOff=103, name='fork', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
6,DynSymElement{symNameOff=431, name='__stack_chk_fail', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
7,DynSymElement{symNameOff=707, name='strlen', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
8,DynSymElement{symNameOff=3220, name='memmove', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
9,DynSymElement{symNameOff=3228, name='memcpy', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
10,DynSymElement{symNameOff=3235, name='memchr', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
11,DynSymElement{symNameOff=3242, name='memset', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
12,DynSymElement{symNameOff=4950, name='memcmp', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
13,DynSymElement{symNameOff=6131, name='wmemmove', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
14,DynSymElement{symNameOff=6181, name='wmemcpy', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
15,DynSymElement{symNameOff=6505, name='wcslen', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
16,DynSymElement{symNameOff=6684, name='wmemchr', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
17,DynSymElement{symNameOff=7018, name='wmemset', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
18,DynSymElement{symNameOff=8843, name='wmemcmp', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
19,DynSymElement{symNameOff=10353, name='__errno', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
20,DynSymElement{symNameOff=10361, name='strtoul', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
21,DynSymElement{symNameOff=10530, name='wcstoul', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
22,DynSymElement{symNameOff=10621, name='strtoll', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
23,DynSymElement{symNameOff=10712, name='wcstoll', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
24,DynSymElement{symNameOff=10804, name='strtoull', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
25,DynSymElement{symNameOff=10897, name='wcstoull', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
26,DynSymElement{symNameOff=10987, name='strtof', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
27,DynSymElement{symNameOff=11075, name='wcstof', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
28,DynSymElement{symNameOff=11163, name='strtod', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
29,DynSymElement{symNameOff=11251, name='wcstod', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
30,DynSymElement{symNameOff=11340, name='strtold', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
31,DynSymElement{symNameOff=11430, name='wcstold', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
32,DynSymElement{symNameOff=11775, name='snprintf', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
33,DynSymElement{symNameOff=11861, name='swprintf', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
34,DynSymElement{symNameOff=11945, name='strtol', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
35,DynSymElement{symNameOff=11952, name='wcstol', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
36,DynSymElement{symNameOff=12707, name='malloc', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
37,DynSymElement{symNameOff=12737, name='free', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
38,DynSymElement{symNameOff=12742, name='posix_memalign', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
39,DynSymElement{symNameOff=12777, name='__strlen_chk', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}
40,DynSymElement{symNameOff=13472, name='__sF', symInfo=17, symOther=0, symShndx=0, symValue=0, symSize=0}
41,DynSymElement{symNameOff=13477, name='vfprintf', symInfo=18, symOther=0, symShndx=0, symValue=0, symSize=0}

-------------ElfSymTabParse-------------
0,SymTabElement{symNameOff=0, name='null', symInfo=0, symOther=0, symShndx=0, symValue=0, symSize=0}
1,SymTabElement{symNameOff=1, name='crtbegin_so.c', symInfo=4, symOther=0, symShndx=65521, symValue=0, symSize=0}
2,SymTabElement{symNameOff=15, name='__on_dlclose', symInfo=2, symOther=0, symShndx=14, symValue=122256, symSize=16}
3,SymTabElement{symNameOff=28, name='$x.1', symInfo=0, symOther=0, symShndx=14, symValue=122256, symSize=0}
4,SymTabElement{symNameOff=33, name='__dso_handle_const', symInfo=1, symOther=0, symShndx=16, symValue=278288, symSize=8}
5,SymTabElement{symNameOff=52, name='__on_dlclose_late', symInfo=2, symOther=0, symShndx=14, symValue=122280, symSize=8}
6,SymTabElement{symNameOff=70, name='$d.2', symInfo=0, symOther=0, symShndx=16, symValue=278288, symSize=0}
7,SymTabElement{symNameOff=75, name='$d.3', symInfo=0, symOther=0, symShndx=17, symValue=290800, symSize=0}
8,SymTabElement{symNameOff=80, name='$d.4', symInfo=0, symOther=0, symShndx=17, symValue=290808, symSize=0}
9,SymTabElement{symNameOff=85, name='$d.5', symInfo=0, symOther=0, symShndx=24, symValue=176, symSize=0}
10,SymTabElement{symNameOff=90, name='$d.6', symInfo=0, symOther=0, symShndx=13, symValue=98720, symSize=0}
11,SymTabElement{symNameOff=95, name='note_android_ident', symInfo=1, symOther=0, symShndx=1, symValue=568, symSize=152}
12,SymTabElement{symNameOff=114, name='$d.0', symInfo=0, symOther=0, symShndx=1, symValue=568, symSize=0}
13,SymTabElement{symNameOff=119, name='note_data', symInfo=0, symOther=0, symShndx=1, symValue=588, symSize=0}
14,SymTabElement{symNameOff=129, name='note_name', symInfo=0, symOther=0, symShndx=1, symValue=580, symSize=0}
15,SymTabElement{symNameOff=139, name='note_end', symInfo=0, symOther=0, symShndx=1, symValue=720, symSize=0}
16,SymTabElement{symNameOff=148, name='ndk_version', symInfo=0, symOther=0, symShndx=1, symValue=592, symSize=0}
17,SymTabElement{symNameOff=160, name='ndk_build_number', symInfo=0, symOther=0, symShndx=1, symValue=656, symSize=0}
18,SymTabElement{symNameOff=52339, name='__atexit_handler_wrapper', symInfo=2, symOther=2, symShndx=14, symValue=122288, symSize=20}
19,SymTabElement{symNameOff=52364, name='atexit', symInfo=2, symOther=2, symShndx=14, symValue=122308, symSize=28}
20,SymTabElement{symNameOff=52384, name='pthread_atfork', symInfo=2, symOther=2, symShndx=14, symValue=122336, symSize=16}
21,SymTabElement{symNameOff=52417, name='__dso_handle', symInfo=1, symOther=2, symShndx=16, symValue=278288, symSize=8}
22,SymTabElement{symNameOff=177, name='person.cpp', symInfo=4, symOther=0, symShndx=65521, symValue=0, symSize=0}
23,SymTabElement{symNameOff=188, name='$x.0', symInfo=0, symOther=0, symShndx=14, symValue=122352, symSize=0}
24,SymTabElement{symNameOff=28, name='$x.1', symInfo=0, symOther=0, symShndx=14, symValue=122496, symSize=0}
25,SymTabElement{symNameOff=70, name='$d.2', symInfo=0, symOther=0, symShndx=10, symValue=80668, symSize=0}
26,SymTabElement{symNameOff=75, name='$d.3', symInfo=0, symOther=0, symShndx=25, symValue=0, symSize=0}
27,SymTabElement{symNameOff=80, name='$d.4', symInfo=0, symOther=0, symShndx=26, symValue=0, symSize=0}
28,SymTabElement{symNameOff=85, name='$d.5', symInfo=0, symOther=0, symShndx=27, symValue=0, symSize=0}
29,SymTabElement{symNameOff=90, name='$d.6', symInfo=0, symOther=0, symShndx=28, symValue=0, symSize=0}
30,SymTabElement{symNameOff=193, name='$d.7', symInfo=0, symOther=0, symShndx=24, symValue=176, symSize=0}
31,SymTabElement{symNameOff=198, name='$d.8', symInfo=0, symOther=0, symShndx=13, symValue=98720, symSize=0}
32,SymTabElement{symNameOff=203, name='$d.9', symInfo=0, symOther=0, symShndx=29, symValue=0, symSize=0}
33,SymTabElement{symNameOff=208, name='native-lib.cpp', symInfo=4, symOther=0, symShndx=65521, symValue=0, symSize=0}
34,SymTabElement{symNameOff=188, name='$x.0', symInfo=0, symOther=0, symShndx=14, symValue=122548, symSize=0}
35,SymTabElement{symNameOff=28, name='$x.1', symInfo=0, symOther=0, symShndx=14, symValue=122588, symSize=0}
36,SymTabElement{symNameOff=223, name='$x.2', symInfo=0, symOther=0, symShndx=14, symValue=122712, symSize=0}
37,SymTabElement{symNameOff=228, name='$x.3', symInfo=0, symOther=0, symShndx=14, symValue=122764, symSize=0}
38,SymTabElement{symNameOff=233, name='GCC_except_table3', symInfo=0, symOther=0, symShndx=11, symValue=90268, symSize=0}
39,SymTabElement{symNameOff=80, name='$d.4', symInfo=0, symOther=0, symShndx=11, symValue=90268, symSize=0}
40,SymTabElement{symNameOff=251, name='$x.5', symInfo=0, symOther=0, symShndx=14, symValue=122968, symSize=0}
41,SymTabElement{symNameOff=256, name='$x.6', symInfo=0, symOther=0, symShndx=14, symValue=123092, symSize=0}
42,SymTabElement{symNameOff=261, name='$x.7', symInfo=0, symOther=0, symShndx=14, symValue=123128, symSize=0}
43,SymTabElement{symNameOff=266, name='$x.8', symInfo=0, symOther=0, symShndx=14, symValue=123212, symSize=0}
44,SymTabElement{symNameOff=271, name='$x.9', symInfo=0, symOther=0, symShndx=14, symValue=123248, symSize=0}
45,SymTabElement{symNameOff=276, name='$x.10', symInfo=0, symOther=0, symShndx=14, symValue=123268, symSize=0}
46,SymTabElement{symNameOff=282, name='$x.11', symInfo=0, symOther=0, symShndx=14, symValue=123288, symSize=0}
47,SymTabElement{symNameOff=288, name='$x.12', symInfo=0, symOther=0, symShndx=14, symValue=123328, symSize=0}
48,SymTabElement{symNameOff=294, name='$x.13', symInfo=0, symOther=0, symShndx=14, symValue=123344, symSize=0}
49,SymTabElement{symNameOff=300, name='$x.14', symInfo=0, symOther=0, symShndx=14, symValue=123384, symSize=0}
50,SymTabElement{symNameOff=306, name='$x.15', symInfo=0, symOther=0, symShndx=14, symValue=123404, symSize=0}
51,SymTabElement{symNameOff=312, name='$x.16', symInfo=0, symOther=0, symShndx=14, symValue=123488, symSize=0}
52,SymTabElement{symNameOff=318, name='$x.17', symInfo=0, symOther=0, symShndx=14, symValue=123540, symSize=0}
53,SymTabElement{symNameOff=324, name='$x.18', symInfo=0, symOther=0, symShndx=14, symValue=123580, symSize=0}
54,SymTabElement{symNameOff=330, name='$x.19', symInfo=0, symOther=0, symShndx=14, symValue=123624, symSize=0}
55,SymTabElement{symNameOff=336, name='$x.20', symInfo=0, symOther=0, symShndx=14, symValue=123660, symSize=0}
56,SymTabElement{symNameOff=342, name='$x.21', symInfo=0, symOther=0, symShndx=14, symValue=123680, symSize=0}
57,SymTabElement{symNameOff=348, name='$x.22', symInfo=0, symOther=0, symShndx=14, symValue=123716, symSize=0}
58,SymTabElement{symNameOff=354, name='$d.23', symInfo=0, symOther=0, symShndx=10, symValue=78959, symSize=0}
59,SymTabElement{symNameOff=360, name='$d.24', symInfo=0, symOther=0, symShndx=25, symValue=1048, symSize=0}
60,SymTabElement{symNameOff=366, name='$d.25', symInfo=0, symOther=0, symShndx=26, symValue=17648, symSize=0}
61,SymTabElement{symNameOff=372, name='$d.26', symInfo=0, symOther=0, symShndx=27, symValue=48, symSize=0}
62,SymTabElement{symNameOff=378, name='$d.27', symInfo=0, symOther=0, symShndx=28, symValue=0, symSize=0}
63,SymTabElement{symNameOff=384, name='$d.28', symInfo=0, symOther=0, symShndx=22, symValue=296656, symSize=0}
64,SymTabElement{symNameOff=390, name='$d.29', symInfo=0, symOther=0, symShndx=24, symValue=176, symSize=0}
65,SymTabElement{symNameOff=396, name='$d.30', symInfo=0, symOther=0, symShndx=13, symValue=98720, symSize=0}
66,SymTabElement{symNameOff=402, name='$d.31', symInfo=0, symOther=0, symShndx=29, symValue=4810, symSize=0}
67,SymTabElement{symNameOff=52743, name='_ZNKSt6__ndk112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE5c_strEv', symInfo=2, symOther=2, symShndx=14, symValue=123092, symSize=36}
68,SymTabElement{symNameOff=53181, name='_ZNKSt6__ndk112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE4dataEv', symInfo=2, symOther=2, symShndx=14, symValue=123344, symSize=40}
69,SymTabElement{symNameOff=53256, name='_ZNSt6__ndk17forwardINS_18__default_init_tagEEEOT_RNS_16remove_referenceIS2_E4typeE', symInfo=2, symOther=2, symShndx=14, symValue=123248, symSize=20}
70,SymTabElement{symNameOff=53340, name='_ZNSt6__ndk122__compressed_pair_elemINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE5__repELi0ELb0EEC2ENS_18__default_init_tagE', symInfo=2, symOther=2, symShndx=14, symValue=123268, symSize=20}
71,SymTabElement{symNameOff=53478, name='_ZNSt6__ndk122__compressed_pair_elemINS_9allocatorIcEELi1ELb1EEC2ENS_18__default_init_tagE', symInfo=2, symOther=2, symShndx=14, symValue=123288, symSize=40}
72,SymTabElement{symNameOff=53576, name='_ZNSt6__ndk19allocatorIcEC2Ev', symInfo=2, symOther=2, symShndx=14, symValue=123328, symSize=16}
73,SymTabElement{symNameOff=53606, name='_ZNKSt6__ndk112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE13__get_pointerEv', symInfo=2, symOther=2, symShndx=14, symValue=123404, symSize=84}
74,SymTabElement{symNameOff=53691, name='_ZNSt6__ndk112__to_addressIKcEEPT_S3_', symInfo=2, symOther=2, symShndx=14, symValue=123384, symSize=20}
75,SymTabElement{symNameOff=53729, name='_ZNKSt6__ndk112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE9__is_longEv', symInfo=2, symOther=2, symShndx=14, symValue=123488, symSize=52}
76,SymTabElement{symNameOff=53809, name='_ZNKSt6__ndk112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE18__get_long_pointerEv', symInfo=2, symOther=2, symShndx=14, symValue=123540, symSize=40}
77,SymTabElement{symNameOff=53899, name='_ZNKSt6__ndk112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE19__get_short_pointerEv', symInfo=2, symOther=2, symShndx=14, symValue=123580, symSize=44}
78,SymTabElement{symNameOff=53990, name='_ZNKSt6__ndk117__compressed_pairINS_12basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEE5__repES5_E5firstEv', symInfo=2, symOther=2, symShndx=14, symValue=123624, symSize=36}



```