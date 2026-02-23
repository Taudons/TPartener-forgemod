# 项目规范

## 项目概览
- **项目名称**: TPartnerMod
- **类型**: Minecraft Forge 模组
- **目标 Minecraft 版本**: 1.16.5
- **描述**: 由 Taudon 开发的 Minecraft 模组。

## 技术栈
- **语言**: Java 8
- **构建工具**: Gradle (通过 ForgeGradle 5.1.+)
- **模组加载器**: Minecraft Forge 36.2.34
- **依赖项**:
  - Minecraft Forge
  - Minecraft (1.16.5)

## 开发规范

### 代码风格
- 遵循标准 Java 约定（变量和方法使用 camelCase，类名使用 PascalCase）。
- 使用有意义的变量和方法名称。
- 保持方法简洁，专注于单一任务。

### Git 工作流程
- 使用描述性的提交信息。
- 为新功能创建特性分支。
- 保持主分支稳定。

### 模组开发最佳实践
- 使用 Forge 的事件总线正确注册方块、物品和其他注册对象。
- 使用 `@Mod` 注解定义模组 ID。
- 适当处理客户端和服务器端逻辑（区分 `FMLCommonSetupEvent` 和 `FMLClientSetupEvent`）。
- 使用 `LOGGER` 进行日志记录，而不是 `System.out.println`。
- 遵循 Minecraft Forge 文档进行事件处理和注册。
