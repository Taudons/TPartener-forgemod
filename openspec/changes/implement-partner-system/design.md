## Context
本项目旨在为 Minecraft 1.16.5 开发一个 RPG 伙伴模组（TPartner）。核心需求是实现一个跟随玩家的实体，并持久化存储玩家与伙伴的关系数据（如好感度）。项目基于 Minecraft Forge 1.16.5 开发。

## Goals / Non-Goals

**Goals:**
- 实现伙伴召唤与收回功能。
- 使用 Forge Capability 实现玩家数据的跨存档持久化。
- 实现伙伴跟随玩家的 AI 行为。
- 实现右键交互 GUI。

**Non-Goals:**
- 复杂的任务系统 (FTB Quests)。
- NPC 交互集成。
- 高级 AI（战斗、采集）。

## Decisions

### 1. 数据持久化: Forge Capability
**选择**: 使用 `net.minecraftforge.capabilities.Capability`。
**理由**: 
- 官方推荐的标准方式。
- 自动与 World Save 集成，减少手动保存/加载的代码量。
- 相比直接操作 NBT 更易维护。

### 2. 实体类型: 自定义 Entity
**选择**: 继承 `net.minecraft.entity.Entity` (或 `EntityCreature`)。
**理由**: 
- 比 ArmorStand 更灵活，便于扩展行为。
- 方便挂载渲染（渲染手办模型）。
- `ArmorStand` 的交互逻辑较难处理。

### 3. AI 行为: Pathfinder API
**选择**: 使用 `net.minecraft.entity.ai.goal.Goal`。
**理由**: 
- Forge 官方推荐。
- 内置 `FollowOwnerGoal` 类似的逻辑可直接参考或复用。

### 4. GUI: Vanilla Screen
**选择**: 继承 `net.minecraft.client.gui.screen.Screen`。
**理由**: 
- 简单快捷。
- MVP 阶段无需复杂的 UI 库。

## Risks / Trade-offs

**[风险 1]**: 1.16.5 的 Entity 注册与 Client/Server 同步。
**→ 缓解**: 严格区分 `CommonSetup` 和 `ClientSetup`，确保实体渲染在 Client 线程正确注册。

**[风险 2]**: Capability 数据在客户端不可见。
**→ 缓解**: 优先在 Server 端处理逻辑，Client 端通过 Packet 更新 UI 数据。

**[风险 3]**: 召唤物品与现有 Mod 冲突（例如左键点击被拦截）。
**→ 缓解**: 使用 `ItemUseEvent` 或 `RightClickItem`，并添加 cancel check。

