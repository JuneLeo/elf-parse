package com.elf.parse.section;

public class SymInfo {

    public STB stb;
    public STT stt;

    public SymInfo(int symInfoCode) {
        stb = getSTB(symInfoCode);
        stt = getSTT(symInfoCode);
    }

    /**
     * STB_LOCAL：本地符号，在本文件外不可见。
     * STB_GLOBAL：全局符号，在所有要链接在一起的 object files 中都可见（全局符号可以被其他文件引用）。
     * STB_WEAK：弱符号，类似于全局符号，但（定义的）优先级低于全局符号。
     */
    public enum STB {
        STB_LOCAL, //0
        STB_GLOBAL, //1
        STB_WEAK, //2
        STB_LOPROC, //13
        STB_HIPROC //15
    }

    /**
     * STT_NOTYPE：符号类型未指定。
     * STT_OBJECT：数据对象，比如变量、数组等。
     * STT_FUNC：函数或其它可执行代码。
     * STT_SECTION：表示一个 section，主要用于重定位，通常具有 STB_LOCAL 属性。
     * STT_FILE：文件符号，具有 STB_LOCAL 属性，st_shndx 的值为 SHN_ABS。在 ELF 文件的符号表中位于其他 STB_LOCAL 符号的前面。
     */

    public enum STT {
        STT_NOTYPE,  // 0
        STT_OBJECT, // 1
        STT_FUNC, //2
        STT_SECTION, //3
        STT_FILE, //4
        STT_LOPROC, //13
        STT_HIPROC //15
    }


    public STB getSTB(int symInfo) {
        int bind = symInfo << 4;
        switch (bind) {
            case 0:
                return STB.STB_LOCAL;
            case 1:
                return STB.STB_GLOBAL;
            case 2:
                return STB.STB_WEAK;
            case 13:
                return STB.STB_LOPROC;
            case 15:
                return STB.STB_HIPROC;
            default:
                return null;

        }
    }

    public STT getSTT(int symInfo) {
        // STT_NOTYPE,  // 0
        //        STT_OBJECT, // 1
        //        STT_FUNC, //2
        //        STT_SECTION, //3
        //        STT_FILE, //4
        //        STT_LOPROC, //13
        //        STT_HIPROC //15
        int bind = symInfo & 0xf;
        switch (bind) {
            case 0:
                return STT.STT_NOTYPE;
            case 1:
                return STT.STT_OBJECT;
            case 2:
                return STT.STT_FUNC;
            case 3:
                return STT.STT_SECTION;
            case 4:
                return STT.STT_FILE;
            case 13:
                return STT.STT_LOPROC;
            case 15:
                return STT.STT_HIPROC;
            default:
                return null;

        }
    }


    @Override
    public String toString() {
        return "SymInfo{" +
                "stb=" + stb +
                ", stt=" + stt +
                '}';
    }
}
