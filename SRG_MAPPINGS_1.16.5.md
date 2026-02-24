# Minecraft Forge 1.16.5 SRG 映射参考表

> 项目使用官方 SRG 映射，本文件记录常用的字段/方法对应关系

## Entity 相关

| MCP 名称 | SRG 名称 | 说明 |
|----------|----------|------|
| `entity.posX` | `entity.field_233621_a_` | X 坐标 |
| `entity.posY` | `entity.field_233618_b_` | Y 坐标 |
| `entity.posZ` | `entity.field_233620_c_` | Z 坐标 |
| `entity.rotationYaw` | `entity.field_233622_d_` | 旋转偏航角 |
| `entity.rotationPitch` | `entity.field_233619_e_` | 旋转俯仰角 |
| `entity.getLookVec()` | `entity.func_70608_a()` | 获取视线向量 |
| `entity.setPosition(x,y,z)` | `entity.func_233626_a(x,y,z)` | 设置位置 |
| `entity.getPosition()` | `entity.func_233621_a_()` | 获取位置方块坐标 |
| `entity.getEntityWorld()` | `entity.func_130014_f_()` | 获取所在世界 |

## World 相关

| MCP 名称 | SRG 名称 | 说明 |
|----------|----------|------|
| `world.isRemote` | `world.field_72995_K` | 是否为客户端 |
| `world.addEntity(entity)` | `world.func_242417_l(entity)` | 添加实体 |
| `world.getBlockState(pos)` | `world.func_180495_p(pos)` | 获取方块状态 |
| `world.setBlockState(pos, state)` | `world.func_180501_a(pos, state, flag)` | 设置方块状态 |

## Player 相关

| MCP 名称 | SRG 名称 | 说明 |
|----------|----------|------|
| `player.getHeldItem(hand)` | `player.func_184598_c(hand)` | 获取手持物品 |
| `player.getHorizontalFacing()` | `player.func_174811_aO()` | 获取水平朝向 |
| `player.getName()` | `player.func_189529_ax()` | 获取玩家名称 |
| `player.getUniqueID()` | `player.func_110124_au()` | 获取唯一ID |

## ItemStack 相关

| MCP 名称 | SRG 名称 | 说明 |
|----------|----------|------|
| `stack.getItem()` | `stack.func_77973_b()` | 获取物品 |
| `stack.getCount()` | `stack.func_77977_a()` | 获取数量 |
| `stack.setCount(count)` | `stack.func_190920_e(count)` | 设置数量 |
| `stack.shrink(amount)` | `stack.func_190918_g(amount)` | 减少数量 |

## BlockPos 相关

| MCP 名称 | SRG 名称 | 说明 |
|----------|----------|------|
| `pos.getX()` | `pos.func_177958_n()` | 获取 X 坐标 |
| `pos.getY()` | `pos.func_177956_o()` | 获取 Y 坐标 |
| `pos.getZ()` | `pos.func_177952_p()` | 获取 Z 坐标 |
| `pos.up()` | `pos.func_177984_a()` | 上方位置 |
| `pos.offset(facing, distance)` | `pos.func_177963_a(facing, distance)` | 偏移位置 |

## BlockState 相关

| MCP 名称 | SRG 名称 | 说明 |
|----------|----------|------|
| `state.isAir()` | `state.func_223624_e()` | 是否是空气方块 |

## ActionResult 相关

| MCP 名称 | SRG 名称 | 说明 |
|----------|----------|------|
| `ActionResultType.SUCCESS` | `ActionResultType.field_77637_a` | 成功 |
| `ActionResultType.FAIL` | `ActionResultType.field_77638_b` | 失败 |
| `ActionResultType.PASS` | `ActionResultType.field_77639_c` | 传递给下一个 |

## 获取实际 SRG 名称的方法

1. **查看反编译源码**: `build/tmp/recompileMc/sources/net/minecraft/`
2. **Gradle 任务**: 运行 `./gradlew genEclipseRuns` 或 `./gradlew genIntellijRuns`
3. **SRG 工具**: 使用 Forge 的 `fillarchives` 任务生成 SRG 映射文件

## 注意事项

- 1.16.5 版本字段名使用 `field_XXXXXX` 格式
- 方法名使用 `func_XXXXXX` 格式
- 建议在 IDE 中启用显示混淆名称以便调试

