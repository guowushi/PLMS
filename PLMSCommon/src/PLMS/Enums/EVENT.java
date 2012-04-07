package PLMS.Enums;

/**
 * 终端事件
 */
public enum EVENT {
        ERC1(1), // 数据初始化和版本变更记录
        ERC2(2),  //参数丢失记录
        ERC3(3),  //参数变更记录
        ERC4(4),  //状态量变位记录
        ERC5(5),  //遥控跳闸记录
        ERC6(6),  //功控跳闸记录
        ERC7(7),  //电控跳闸记录
        ERC8(8),  //电能表参数变更
        ERC9(9),  //电流回路异常
        ERC10(10),  //电压回路异常
        ERC11(11),  //相序异常
        ERC12(12),  //电能表时间超差
        ERC13(13),  //电表故障信息
        ERC14(14),  //终端停/上电事件
        ERC15(15),  //谐波越限告警
        ERC16(16),  //直流模拟量越限记录
        ERC17(17),  //电压/电流不平衡越限
        ERC18(18),  //电容器投切自锁记录
        ERC19(19),  //购电参数设置记录
        ERC20(20),  //消息认证错误记录
        ERC21(21),  //终端故障记录
        ERC22(22),  //有功总电能量差动越限事件记录
        ERC23(23),  //备用
        ERC24(24),  //电压越限记录
        ERC25(25),  //电流越限记录
        ERC26(26),  //视在功率越限记录
        ERC27(27),  //电能表示度下降
        ERC28(28),  //电能量超差
        ERC29(29),  //电能表飞走
        ERC30(30);  //电能表停走
        private  int value;
        private EVENT(int v) {
            this.value=v;

        }
        public byte[] valueOf()
        {
            byte[] ret=new byte[1];
            ret[0]=(byte)this.value;
            return ret;
        }
    // 根据值，找对应的枚举值
    public static EVENT get(int code) {
        for(EVENT s : values()) {
            if(s.value == code) return s;
        }
        return null;
    }
}