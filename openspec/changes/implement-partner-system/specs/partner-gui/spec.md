## ADDED Requirements

### Requirement: Main GUI Display
玩家右键点击伙伴时，必须显示一个 GUI 界面。

#### Scenario: Open Main GUI
- **WHEN** 玩家成功触发交互事件
- **THEN** 客户端显示伙伴主界面 (PartnerMainScreen)

#### Scenario: Display Info
- **WHEN** 界面打开
- **THEN** 显示伙伴名称、当前好感度、心情值

### Requirement: Interaction Buttons
界面必须提供按钮供玩家操作。

#### Scenario: Click Talk Button
- **WHEN** 玩家点击“对话”按钮
- **THEN** 伙伴说出一句根据好感度决定的台词

#### Scenario: Click Settings Button
- **WHEN** 玩家点击“设置”按钮
- **THEN** 切换跟随/等待状态

