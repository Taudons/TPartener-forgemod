# partner-entity

## 新增需求

### 需求：伙伴实体可通过 createInstance 创建
系统应提供一个 `TPartnerEntity` 类，实现 `createInstance` 方法以生成新的伙伴实体。

#### 场景：创建伙伴实体实例
- **When** 使用有效的世界上下文调用 `createInstance` 时
- **Then** 应创建一个新的 `TPartnerEntity` 实例
- **AND** 实体应被添加到世界中

### 需求：伙伴实体具有正确的实体类型注册
`TPartnerEntity` 应具有关联的 `EntityType`，并已在游戏中注册。

#### 场景：实体类型已注册
- **When** 模组初始化时
- **Then** `TPartnerEntity` 实体类型应完成注册
- **AND** 实体类型应可通过 `ForgeRegistries.ENTITY_TYPES` 访问

### 需求：伙伴实体在有效位置生成
实体应在调用者指定的有效位置生成。

#### 场景：在指定位置生成
- **When** 使用有效的方块位置生成实体时
- **Then** 实体应出现在该位置
- **AND** 实体应具有正确的世界引用

### 需求：伙伴实体是持久的
伙伴实体应在区块加载后仍然存在。

#### 场景：实体在加载的区块中持久化
- **When** 实体存在于已加载的区块中时
- **Then** 实体应保留在世界中
- **AND** 实体数据应被保留
