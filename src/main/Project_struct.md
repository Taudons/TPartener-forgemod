src/main/java/com/taudon/tpartnermod/
├── TPartnerMod.java           # 主类
├── util/
│   └── Constants.java        # 常量定义
├── item/
│   └── FairyLanternItem.java # 精灵提灯物品
├── event/
│   ├── FairyLanternEvents.java # 右键生成实体事件
│   └── CapabilityEvents.java   # Capability事件（待实现）
├── entity/
│   ├── PartnerEntity.java     # 伙伴实体类
│   └── PartnerEntityType.java # 实体类型注册
├── client/
│   └── ClientSetup.java      # 客户端初始化