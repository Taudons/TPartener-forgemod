# TPartner 伙伴系统设计文档

## 1. 核心概念
**TPartner**: 一个跟随玩家的智能实体（Smart Entity），作为玩家的伙伴存在于 Minecraft RPG 整合包中。
- **外观**: 兼容女仆模型（ArmorStand 或自定义 Model），预留动作接口。
- **数据**: 绑定玩家存档，使用 Capability 持久化。
- **交互**: 右键交互触发 GUI（对话、功能）。

## 2. 核心功能 (MVP - 最小可行性产品)

### 2.1 召唤与收回
- **召唤**: 玩家手持“伙伴召唤物品”（如“精灵提灯”），右键点击地面生成伙伴实体。
- **收回**: Shift + 右键点击伙伴，将其移除（实体消失，数据保留）。
- **限制**: 每个玩家只能召唤一个伙伴。

### 2.2 伙伴实体 (Entity)
- **基础行为**: 跟随玩家（Follow），保持一定距离。
- **模型**: 使用 `ArmorStand` 或简单的 `Entity` 挂载渲染。
- **交互**: 右键打开伙伴菜单（GUI）。

### 2.3 持久化数据 (Capability)
存储在玩家身上（Player Capability），确保随玩家存档一起保存。
- **好感度 (Affinity)**: 整数，影响对话内容和外观。
- **心情 (Mood)**: 整数，随时间或事件变化。
- **状态**: 是否已召唤。

### 2.4 交互 GUI
- **主界面**:
  - 显示伙伴名称、等级、好感度。
  - 按钮：对话、送礼、设置（跟随/等待）、地图（预留）。
- **对话系统**:
  - 伙伴根据好感度显示不同的台词。
  - 简单的 JSON 文本配置。

## 3. 架构设计

### 3.1 模块划分
```text
TPartnerMod (主类)
  ├── Capability (数据层)
  │     └── IPartnerData / PartnerDataProvider
  ├── Entity (实体层)
  │     └── TPartnerEntity (extends Entity)
  ├── Item (物品层)
  │     └── SummonItem (召唤物品)
  ├── AI (行为层)
  │     └── FollowGoal (跟随逻辑)
  └── GUI (界面层)
        └── PartnerGuiHandler (屏幕切换)
```

### 3.2 关键技术决策
- **实体类型**: `Entity` (推荐) 或 `EntityCreature`。
- **状态管理**: **Forge Capability** (玩家绑定)。
- **交互方式**: 
  - 右键点击伙伴实体 -> 打开 GUI。
  - 物品栏快捷键 -> 召唤/收回。
- **第三方集成**:
  - 暂时不做 FTB Quests 集成。
  - 预留 CustomNPCs 接口 (未来版本)。

## 4. 开发路线图 (MVP)

### Phase 1: 基础架构
1. **注册 Capability**: 实现 `IPartnerData` 接口，定义 NBT 读写。
2. **注册 Entity**: 创建 `TPartnerEntity`，实现基本的 `createInstance`。
3. **注册 Item**: 创建“精灵提灯”，实现右键生成实体。

### Phase 2: 交互与行为
4. **跟随逻辑**: 实现 `FollowGoal`，让实体自动跟随玩家。
5. **GUI**: 实现简单的 `Screen`，显示好感度和按钮。

### Phase 3: 完善
6. **对话系统**: 实现基于 JSON 的对话文本加载。
7. **持久化**: 完善 Capability 的保存/加载逻辑。

## 5. 技术栈
- **Minecraft**: 1.16.5
- **Java**:
  - 开发 (编译): Java 8 (推荐) 或 Java 17
  - 运行时: Java 17 (您的环境)
- **Forge Version**: 36.2.0 (或对应的 1.16.5 官方推荐版本)
- **依赖**: 无 (MVP 阶段不依赖其他模组)

