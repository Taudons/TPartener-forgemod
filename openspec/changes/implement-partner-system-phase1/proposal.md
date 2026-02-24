# Proposal: implement-partner-system-phase1

## Why

当前模组缺少伙伴系统的核心基础设施，玩家无法通过物品生成自定义实体作为伙伴。这限制了模组的互动性和可玩性。需要实现 Phase 1 的基础架构，为后续的伙伴系统功能（如 AI 行为、交互等）奠定基础。

## What Changes

1. **注册 Capability**: 实现 `IPartnerData` 接口，定义 NBT 读写方法，用于持久化伙伴数据
2. **注册 Entity**: 创建 `TPartnerEntity` 类，继承自 `Entity`，实现基本的 `createInstance` 方法
3. **注册 Item**: 创建"精灵提灯"（Fairy Lantern）物品，实现右键点击生成 `TPartnerEntity` 实体的功能

## Capabilities

### New Capabilities

- `partner-capability`: 伙伴数据能力，提供 NBT 读写接口，用于存储和加载伙伴的相关数据（如名称、等级、状态等）
- `partner-entity`: 伙伴实体，定义伙伴实体的基本行为和属性
- `fairy-lantern-item`: 精灵提灯物品，允许玩家通过右键生成伙伴实体

### Modified Capabilities

（无）

## Impact

- 新增 `src/main/java/com/Taudon/TPartnerMod/capability/` 目录存放 Capability 相关代码
- 新增 `src/main/java/com/Taudon/TPartnerMod/entity/` 目录存放 Entity 相关代码
- 新增 `src/main/java/com/Taudon/TPartnerMod/item/` 目录存放 Item 相关代码
- 需要在主类 `TPartnerMod.java` 中注册上述内容

