## ADDED Requirements

### Requirement: Player Partner Data Storage
每个玩家的伙伴数据（好感度、心情、是否召唤）必须持久化存储。

#### Scenario: New Player Join
- **WHEN** 新玩家加入游戏
- **THEN** 系统自动为该玩家初始化默认的伙伴数据（好感度 0，未召唤）

#### Scenario: Save Data
- **WHEN** 世界保存 (World Save)
- **THEN** 玩家身上的伙伴数据必须自动写入存档（NBT）

#### Scenario: Load Data
- **WHEN** 玩家进入世界
- **THEN** 玩家身上的伙伴数据必须从存档恢复

### Requirement: Affinity System
玩家与伙伴的交互必须影响好感度数值。

#### Scenario: Increase Affinity
- **WHEN** 玩家点击 GUI 中的“打招呼”按钮
- **THEN** 好感度 +1

#### Scenario: Decrease Affinity
- **WHEN** 玩家长时间未互动 (可选)
- **THEN** 好感度 -1

