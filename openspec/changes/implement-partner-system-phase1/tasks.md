# 任务清单：implement-partner-system-phase1

## 1. 伙伴 Capability

- [ ] 1.1 创建 `IPartnerData` 接口，包含 `readNBT` 和 `writeNBT` 方法
- [ ] 1.2 实现 `PartnerData` 类，实现 `IPartnerData` 接口
- [ ] 1.3 创建 `PartnerCapabilityProvider` 用于 capability 附加
- [ ] 1.4 在模组事件总线中注册 capability

## 2. 伙伴实体

- [ ] 2.1 创建 `TPartnerEntity` 类，继承 `Entity`
- [ ] 2.2 实现 `createInstance` 静态方法用于实体生成
- [ ] 2.3 在 `ForgeRegistries.ENTITY_TYPES` 中注册 `TPartnerEntity` 的 EntityType
- [ ] 2.4 为实体附加伙伴 capability

## 3. 精灵提灯物品

- [ ] 3.1 创建 `FairyLanternItem` 类，继承 `Item`
- [ ] 3.2 实现 `onItemUse` 或 `use` 方法来生成实体
- [ ] 3.3 添加生成位置验证（空气方块、足够空间）
- [ ] 3.4 在 `ForgeRegistries.ITEMS` 中注册物品
- [ ] 3.5 添加物品翻译字符串作为显示名称

## 4. 集成

- [ ] 4.1 在主模组类 `TPartnerMod.java` 中注册所有新组件
- [ ] 4.2 验证实体在创造/生存模式中正确生成
- [ ] 4.3 测试 capability 数据在游戏存档中的持久化
