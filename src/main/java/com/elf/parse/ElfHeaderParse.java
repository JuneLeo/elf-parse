package com.elf.parse;

import com.elf.parse.eident.EiClass;
import com.elf.parse.eident.EiData;
import com.elf.parse.eident.EiOSAbi;
import com.elf.parse.eident.EiVersion;
import com.elf.parse.header.EMachine;
import com.elf.parse.header.EType;
import com.elf.parse.header.EVersion;

public class ElfHeaderParse implements Parse {
    private final char[] magic = new char[4];
    private EiClass eiClass;
    private EiData eiData;
    private EiVersion eiVersion;
    private EiOSAbi eiOSAbi;

    private final char[] eiPad = new char[6]; // 填充
    private char eiNidentSize; // ident[]大小

    private char eiAbiVersion;

    private EType eType; // ELF类型

    private EMachine eMachine; // 平台

    private EVersion eVersion; //版本

    private long eEntry; //8   程序入口地址
    private long ePhoff; //8   程序段表头的偏移
    private long eShoff; //8   节表头的偏移

    private int eFlags; //4    和处理器相关的一个标记

    private int elfHeaderSize;// 2   ELF表头的大小

    private int ePhEntrySize; //2   程序表头的Entry大小
    private int ePhNum; //2       程序表头的Entry数量
    private int eShEntrySize; //2    节表头Entry大小
    private int eShNum;//2           节表头Entry数量
    private int e_ShStrIndex; //2    节表中字符Entry的索引位置


    ElfSectionHeaderParse elfSectionHeaderParse;


    @Override
    public int parse(long start, byte[] bytes) {
        System.out.println("Elf Header:");
        // ident
        for (int i = 0; i < 4; i++) {
            int ident = Utils.getU1Int(i, bytes);
            magic[i] = (char) ident;
        }

        int eClass = Utils.getU1Int(4, bytes);
        eiClass = EiClass.getEiClass(eClass);

        int eData = Utils.getU1Int(5, bytes);
        eiData = EiData.getEiData(eData);
        Utils.eiData = eData;

        int eversion = Utils.getU1Int(6, bytes);
        eiVersion = EiVersion.getEiVersion(eversion);

        int eOSAbi = Utils.getU1Int(7, bytes);
        eiOSAbi = EiOSAbi.getEiOSAbi(eOSAbi);

        eiAbiVersion = (char) Utils.getU1Int(8, bytes);
        //9
        for (int i = 0; i < 6; i++) {
            eiPad[i] = (char) Utils.getU1Int(9 + i, bytes);
        }
        // 15
        eiNidentSize = (char) Utils.getU1Int(15, bytes);

        printEident();

        eType = EType.getEType(Utils.getU2Int(16, bytes));
        if (eType != null) {
            System.out.println("文件类型：" + eType.name());
        }

        eMachine = EMachine.getEmachine(Utils.getU2Int(18, bytes));
        if (eMachine != null) {
            System.out.println("结构类型：" + eMachine.name());
        }

        eVersion = EVersion.getEVersion(Utils.getU4Int(20, bytes));
        if (eVersion != null) {
            System.out.println("目标文件版本：" + eVersion.name());
        }

        eEntry = Utils.getU8Long(0x18, bytes);
        System.out.println("程序入口地址：" + eEntry);
        ePhoff = Utils.getU8Long(0x20, bytes);
        System.out.println("program header 偏移：" + ePhoff);
        eShoff = Utils.getU8Long(0x28, bytes);
        System.out.println("section header 偏移：" + eShoff);

        eFlags = Utils.getU4Int(0x30, bytes);

        elfHeaderSize = Utils.getU2Int(0x34, bytes);
        System.out.println("header size：" + elfHeaderSize);

        ePhEntrySize = Utils.getU2Int(0x36, bytes);
        System.out.println("program header entry结构 size：" + ePhEntrySize);

        ePhNum = Utils.getU2Int(0x38, bytes);
        System.out.println("program header size：" + ePhNum);

        eShEntrySize = Utils.getU2Int(0x3A, bytes);
        System.out.println("section header entry结构 size：" + eShEntrySize);

        eShNum = Utils.getU2Int(0x3C, bytes);
        System.out.println("section header size：" + eShNum);

        e_ShStrIndex = Utils.getU2Int(0x3E, bytes);
        System.out.println("section中string table index：" + e_ShStrIndex);


        ElfProgramHeaderParse elfProgramHeaderParse = new ElfProgramHeaderParse(ePhoff, ePhEntrySize, ePhNum);
        elfProgramHeaderParse.parse(ePhoff, bytes);


        elfSectionHeaderParse = new ElfSectionHeaderParse(eShoff, eShEntrySize, eShNum, e_ShStrIndex);
        elfSectionHeaderParse.parse(eShoff, bytes);

        return 0;
    }

    private void printEident() {
        System.out.println("Eident content");
        System.out.print("Magic：");
        for (char c : magic) {
            System.out.print("" + c);
        }
        System.out.println("");
        if (eiClass != null) {
            System.out.println(eiClass.name());
        }

        if (eiData != null) {
            System.out.println("编码方式：" + eiData.name() + "（大端/小端）");
        }

        if (eiVersion != null) {
            System.out.println(eiVersion.name());
        }

        if (eiOSAbi != null) {
            System.out.println(eiOSAbi.name());
        }
    }

    public long getMethodAddress(String method) {
        if (elfSectionHeaderParse != null) {
            return elfSectionHeaderParse.getMethodAddress(method);
        }
        return 0;
    }
}
