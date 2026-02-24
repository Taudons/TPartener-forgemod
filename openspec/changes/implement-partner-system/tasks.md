## 1. 基础架构

- [ ] 1.1 创建 Capability 接口 `IPartnerData` 与实现类 `PartnerData`
- [ ] 1.2 注册 Capability 到 PlayerEntity
- [ ] 1.3 创建伙伴实体 `TPartnerEntity` (空壳，仅注册)
- [ ] 1.4 注册实体与渲染器 (EntityType, Rendering)

## 2. 召唤与收回

- [ ] 2.1 创建召唤物品 `SummonItem` (“精灵提灯”)
- [ ] 2.2 实现物品右键生成实体逻辑
- [ ] 2.3 实现 Shift+右键 收回实体逻辑
- [ ] 2.4 限制每个玩家只能召唤一个伙伴

## 3. 跟随 AI

- [ ] 3.1 为 `TPartnerEntity` 添加 `GoalSelector`
- [ ] 3.2 实现 `FollowOwnerGoal` (跟随玩家)
- [ ] 3.3 实现简单的状态切换 (跟随/等待)

## 4. 交互 GUI

- [ ] 4.1 创建 `PartnerMainScreen` (主界面)
- [ ] 4.2 实现右键打开 GUI 的事件处理
- [ ] 4.3 添加“打招呼”按钮增加好感度
- [ ] 4.4 客户端与服务端数据同步 (SimpleNetworking)

## 5. 完善

- [ ] 5.1 实现对话系统 (基于 JSON 文本)
- [ ] 5.2 测试并修复 NBT 持久化问题

