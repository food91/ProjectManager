package com.xk.base.data;

import java.util.HashMap;
import java.util.Map;

public  class Identity {
    // 创建一个静态的映射表来存储数字和身份的对应关系
    private static final Map<Integer, String> identityMap = new HashMap<>();
    public static final int PROJECT_MANAGER_ID = 134;
    public static final int OFFICE_DIRECTOR_ID = 121;
    public static final int INSPECTION_SUPERVISOR_ID = 141;
    public static final int PROJECT_PARTY_ID = 111;
    public static final int CONTRACTOR_PARTY_ID = 112;
    public static final int SUPERVISOR_ID = 124;
    public static final String CODE = "345039";

    // 静态初始化块，用于初始化身份映射表
    static {
        // 项目方默认身份
        identityMap.put(PROJECT_MANAGER_ID, "项目经理");
        identityMap.put(OFFICE_DIRECTOR_ID, "办公室主任");
        identityMap.put(INSPECTION_SUPERVISOR_ID, "检查监理");
        identityMap.put(PROJECT_PARTY_ID, "项目方");
        identityMap.put(CONTRACTOR_PARTY_ID, "承包方");
        identityMap.put(SUPERVISOR_ID, "监理");
    }

    /**
     * 根据数字获取对应的身份名称
     * @param id 数字身份标识
     * @return 对应的身份名称，如果找不到则返回"未知身份"
     */
    public static String getIdentityName(int id) {
        return identityMap.getOrDefault(id, "未知身份");
    }


}
