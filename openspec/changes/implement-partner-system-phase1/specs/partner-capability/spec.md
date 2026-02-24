# partner-capability

## 新增需求

### 需求：伙伴数据可以被存储和检索
系统应提供一个 `IPartnerData` 接口，允许通过 NBT 存储和检索伙伴相关数据。

#### 场景：将伙伴数据写入 NBT
- **When** 使用有效伙伴数据调用 `writeNBT` 时
- **Then** 数据应被序列化为 NBT 格式并返回

#### 场景：从 NBT 读取伙伴数据
- **When** 使用有效 NBT 数据调用 `readNBT` 时
- **Then** 伙伴数据应被反序列化并可供使用

#### 场景：没有数据时使用默认值
- **When** 使用空或 null NBT 调用 `readNBT` 时
- **Then** 所有伙伴数据字段应使用默认值

### 需求：伙伴数据包含必需字段
伙伴数据应至少包含以下字段：
- 伙伴名称（String）
- 伙伴等级（Integer）
- 伙伴经验值（Integer）

#### 场景：存储和检索伙伴名称
- **When** 设置并保存伙伴名称时
- **Then** 加载时应返回相同的名称

#### 场景：存储和检索伙伴等级
- **When** 设置并保存伙伴等级时
- **Then** 加载时应返回相同的等级

### 需求：Capability 自动附加到实体
`IPartnerData` capability 应自动附加到需要伙伴功能的实体。

#### 场景：实体上存在 capability
- **When** 创建使用伙伴数据的实体时
- **Then** capability 应附加到实体上
- **AND** capability 应可通过 `Capability<IPartnerData>` 访问
