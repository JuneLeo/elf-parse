package com.elf.parse;

import com.elf.parse.eident.EiClass;
import com.elf.parse.eident.EiData;
import com.elf.parse.eident.EiOSAbi;
import com.elf.parse.eident.EiVersion;
import com.elf.parse.header.EMachine;
import com.elf.parse.header.EType;
import com.elf.parse.header.EVersion;

public class ElfHeaderParse implements Parse {
    public final char[] magic = new char[4];
    public EiClass eiClass;
    public EiData eiData;
    public EiVersion eiVersion;
    public EiOSAbi eiOSAbi;

    public final char[] eiPad = new char[6]; // 填充
    public char eiNidentSize; // ident[]大小

    public char eiAbiVersion;

    public EType eType; // ELF类型

    public EMachine eMachine; // 平台

    public EVersion eVersion; //版本

    public long eEntry; //8   程序入口地址
    public long ePhoff; //8   程序段表头的偏移
    public long eShoff; //8   节表头的偏移

    public int eFlags; //4    和处理器相关的一个标记

    public int elfHeaderSize;// 2   ELF表头的大小

    public int ePhEntrySize; //2   程序表头的Entry大小
    public int ePhNum; //2       程序表头的Entry数量
    public int eShEntrySize; //2    节表头Entry大小
    public int eShNum;//2           节表头Entry数量
    public int e_ShStrIndex; //2    节表中字符Entry的索引位置


    @Override
    public void parse(long start, byte[] bytes) {
        Utils.log("Elf Header:");
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
            Utils.log("文件类型：" + eType.name());
        }

        eMachine = EMachine.getEmachine(Utils.getU2Int(18, bytes));
        if (eMachine != null) {
            Utils.log("结构类型：" + eMachine.name());
        }

        eVersion = EVersion.getEVersion(Utils.getU4Int(20, bytes));
        if (eVersion != null) {
            Utils.log("目标文件版本：" + eVersion.name());
        }

        eEntry = Utils.getU8Long(0x18, bytes);
        Utils.log("程序入口地址：" + eEntry);
        ePhoff = Utils.getU8Long(0x20, bytes);
        Utils.log("program header 偏移：" + ePhoff);
        eShoff = Utils.getU8Long(0x28, bytes);
        Utils.log("section header 偏移：" + eShoff);

        eFlags = Utils.getU4Int(0x30, bytes);

        elfHeaderSize = Utils.getU2Int(0x34, bytes);
        Utils.log("header size：" + elfHeaderSize);

        ePhEntrySize = Utils.getU2Int(0x36, bytes);
        Utils.log("program header entry结构 size：" + ePhEntrySize);

        ePhNum = Utils.getU2Int(0x38, bytes);
        Utils.log("program header size：" + ePhNum);

        eShEntrySize = Utils.getU2Int(0x3A, bytes);
        Utils.log("section header entry结构 size：" + eShEntrySize);

        eShNum = Utils.getU2Int(0x3C, bytes);
        Utils.log("section header size：" + eShNum);

        e_ShStrIndex = Utils.getU2Int(0x3E, bytes);
        Utils.log("section中string table index：" + e_ShStrIndex);
    }

    private void printEident() {
        Utils.log("Eident content");
        StringBuilder magicName = new StringBuilder("Magic：");
        for (char c : magic) {
            magicName.append(c);
        }
        Utils.log(magicName.toString());
        if (eiClass != null) {
            Utils.log(eiClass.name());
        }

        if (eiData != null) {
            Utils.log("编码方式：" + eiData.name() + "（大端/小端）");
        }

        if (eiVersion != null) {
            Utils.log(eiVersion.name());
        }

        if (eiOSAbi != null) {
            Utils.log(eiOSAbi.name());
        }
    }
}
