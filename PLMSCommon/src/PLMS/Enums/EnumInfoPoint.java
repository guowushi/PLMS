package PLMS.Enums;

/**
 * 测量点（Measured Point):一个点代表一个表计
 * 一个终端（P0）最多可以接64个表计（P1~P64)；
 */
public enum EnumInfoPoint {
    P0(0),         // P0表示终端本身，而非某一个测量点
    P1(1),P2(2),P3(3),P4(4),P5(5),P6(6),P7(7),P8(8),
    P9(9),P10(10),P11(11),P12(12),P13(13),P14(14),P15(15),P16(16),
    P17(17),P18(18),P19(19),P20(20),P21(21),P22(22),P23(23),P24(24),
    P25(25),P26(26),P27(27),P28(28),P29(29),P30(30),P31(31),P32(32),
    P33(33),P34(34),P35(35),P36(36),P37(37),P38(38),P39(39),P40(40),
    P41(41),P42(42),P43(43),P44(44),P45(45),P46(46),P47(47),P48(48),
    P49(49),P50(50),P51(51),P52(52),P53(53),P54(54),P55(55),P56(56),
    P57(57),P58(58),P59(59),P60(60),P61(61),P62(62),P63(63),P64(64);
    // --------------------------------------------------------------------
    private int value; 
    private EnumInfoPoint(int v) {
         this.value=v;
    }
    public int valueOf()
    {
        return value;
    }
}