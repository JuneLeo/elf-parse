package com.elf.parse.section;

public class SectionElement {
    public int nameOffset; // 相对于.shstrtab表中虚拟地址的偏移

    public String name;    // 字符串

    public SHType sType;      // 节类型

    public SHFlag sFlag; // 节标志位

    public long sAddr; // 节的虚拟地址空间，如果该节加载到内存，则使用此地址

    public long sOffset; // 该节的相对于文件的偏移地址

    public long sSize; // 节的大小

    public int sLink; // 该节依赖的节区信息

    public int sInfo; // 该节依赖的节区信息

    public long sAddrAlign; // 节对齐方式

    public long sEntrySize; // 节 entry 大小，eg： symtab entry size


    @Override
    public String toString() {
        return "SectionElement{" +
                "nameOffset=" + nameOffset +
                ", name='" + name + '\'' +
                ", sType=" + sType+
                ", sFlag=" + sFlag+
                ", sAddr=" + sAddr +
                ", sOffset=" + sOffset +
                ", sSize=" + sSize +
                ", sLink=" + sLink +
                ", sInfo=" + sInfo +
                ", sAddrAlign=" + sAddrAlign +
                ", sEntrySize=" + sEntrySize +
                '}';
    }
}
