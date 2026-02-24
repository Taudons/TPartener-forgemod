## ADDED Requirements

### Requirement: Partner Entity Spawning
玩家使用召唤物品在有效方块上右键时，必须在玩家当前视线的地面位置生成一个伙伴实体。

#### Scenario: Successful Spawn
- **WHEN** 玩家持有召唤物品并在有效方块上右键
- **THEN** 世界中生成一个 TPartnerEntity 实体
- **AND** 实体立即开始跟随玩家

#### Scenario: Spawn in Air
- **WHEN** 玩家在空中或无效方块上右键
- **THEN** 不生成实体，不报错（或者生成在玩家脚下）

### Requirement: Partner Entity Follow
伙伴实体必须保持在玩家附近的范围内。

#### Scenario: Player Moves
- **WHEN** 玩家移动距离超过 5 格
- **THEN** 伙伴实体通过 AI 路径寻路跟随玩家

### Requirement: Partner Interaction
玩家必须能够通过右键点击伙伴实体来打开交互菜单。

#### Scenario: Right Click Partner
- **WHEN** 玩家右键点击伙伴实体
- **THEN** 打开伙伴 GUI 界面

### Requirement: Partner Despawn
玩家必须能够通过特定操作移除伙伴实体。

#### Scenario: Shift Right Click Despawn
- **WHEN** 玩家 Shift + 右键点击伙伴实体
- **THEN** 伙伴实体从世界中移除（死亡或清除）

