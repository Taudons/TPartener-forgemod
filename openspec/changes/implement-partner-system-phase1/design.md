# Design: implement-partner-system-phase1

## Context

本项目是一个 Minecraft Forge 模组，旨在实现一个伙伴系统。玩家可以通过特定物品生成自定义实体作为伙伴。目前模组处于初始阶段，需要建立基础架构。

根据 proposal，Phase 1 需要实现三个核心组件：
- **Partner Capability**: 存储伙伴数据的持久化机制
- **Partner Entity**: 可被玩家召唤的伙伴实体
- **Fairy Lantern Item**: 用于生成伙伴的物品

### 技术约束
- 使用 Minecraft Forge 1.16.5 API
- 基于 NeoForge 的 Capability 系统
- 需要与现有模组结构兼容

## Goals / Non-Goals

**Goals:**
1. 实现 `IPartnerData` 接口，提供 NBT 读写能力
2. 创建 `TPartnerEntity`，支持通过 `createInstance` 生成
3. 创建"精灵提灯"物品，右键点击可生成伙伴实体
4. 在主类中正确注册所有组件

**Non-Goals:**
- 伙伴 AI 行为（后续 Phase 实现）
- 伙伴与玩家的交互功能（后续 Phase 实现）
- 伙伴外观的自定义渲染（后续 Phase 实现）
- 多伙伴支持（后续 Phase 实现）

## Decisions

### D1: 使用 Capability 存储伙伴数据

**选择**: 利用 Forge 的 Capability 系统存储伙伴数据
**理由**: 
- Capability 是 Forge 官方推荐的数据持久化方案
- 自动与 Entity 生命周期绑定
- 支持跨维度数据同步
- 与其他模组兼容性好

**替代方案**:
- 直接使用 NBT 存储在 Entity 中 → 缺点：代码耦合度高，不够模块化
- 使用自定义数据结构 → 缺点：需要手动管理生命周期

### D2: 伙伴实体继承基础 Entity 类

**选择**: 创建 `TPartnerEntity` 继承 `Entity`
**理由**:
- Forge 官方推荐的自定义实体方式
- 可以利用 Entity 的完整生命周期管理
- 便于后续添加 AI 目标、导航等高级功能

**替代方案**:
- 继承 `Mob` → 缺点：包含不必要的 AI 框架，Phase 1 不需要
- 继承 `TamableAnimal` → 缺点：需要宠物所有权逻辑，Phase 1 不需要

### D3: 物品使用 `OnItemUse` 事件生成实体

**选择**: 在物品的 `onItemUse` 或 `use` 方法中生成实体
**理由**:
- 简单直接，适合 Phase 1
- 利用 Forge 的事件系统，无需复杂的自定义逻辑

**替代方案**:
- 使用自定义 `ItemUseGoal` → 缺点：过度设计
- 使用方块形式 → 缺点：不符合设计文档中的"物品"定义

## Risks / Trade-offs

### R1: 实体注册可能失败

**风险**: 如果 EntityType 未正确注册，游戏可能崩溃或实体无法生成
**缓解**: 
- 严格遵循 Forge 实体注册流程
- 在测试环境中验证实体生成

### R2: Capability 同步问题

**风险**: 跨维度时 Capability 数据可能丢失
**缓解**: 
- 实现 `writeNBT` 和 `readNBT` 方法确保数据正确序列化
- 后续 Phase 添加明确的同步逻辑

### R3: 物品生成实体的位置判断

**风险**: 玩家可能在无效位置使用物品
**缓解**: 
- 检查目标方块是否为空气
- 检查是否有足够的空间生成实体

