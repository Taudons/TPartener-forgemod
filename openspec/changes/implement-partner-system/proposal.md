## Why
实现一个基于 Minecraft 1.16.5 的伙伴系统（TPartner），允许玩家召唤、跟随和交互的伙伴实体，并使用 Capability 进行持久化数据存储。

## What Changes
- **新增伙伴实体 (TPartnerEntity)**: 可召唤的跟随实体。
- **新增召唤物品 (SummonItem)**: 用于召唤伙伴的“精灵提灯”。
- **新增持久化系统 (Partner Capability)**: 存储玩家与伙伴的好感度、状态等数据。
- **新增交互系统**: 右键交互打开 GUI，支持对话和设置。
- **新增 AI 行为**: 简单的跟随逻辑。

## Capabilities

### New Capabilities
- `partner-entity`: 伙伴实体实现，包括模型渲染和基础行为。
- `partner-persistence`: 基于 Forge Capability 的玩家数据持久化系统。
- `partner-gui`: 伙伴交互界面实现。
- `partner-ai`: 伙伴跟随和状态机逻辑。

### Modified Capabilities
- (无)

## Impact
- 新增实体 ID: `tpartner entity`
- 新增物品 ID: `tpartner:summon_item`
- 新增 Capability Key: `tpartner:partner_data`

