import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")  // 引入Kotlin多平台插件
    id("org.jetbrains.compose")  // 引入JetBrains Compose插件
}

kotlin {
    jvm {}  // 定义JVM目标，这里没有特别的配置
    sourceSets {
        val jvmMain by getting  {  // 定义JVM平台的主源代码集
            dependencies {
                implementation(compose.desktop.currentOs)  // 根据当前操作系统添加Compose for Desktop的依赖
                implementation(project(":shared"))  // 依赖共享模块
            }
        }
    }
}

compose.desktop {  // Compose for Desktop特定的配置
    application {
        mainClass = "example.todoapp.lite.MainKt"  // 指定程序入口点的主类

        nativeDistributions {  // 配置原生应用分发包
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)  // 指定目标格式：DMG (macOS), MSI (Windows), DEB (Linux)
            packageName = "TodoApp Lite"  // 分发包的名称
            packageVersion = "1.0.0"  // 分发包的版本

            windows {  // Windows特定的配置
                menuGroup = "Compose Examples"  // 开始菜单中的文件夹名称
                upgradeUuid = "5ac63736-d8c7-4a65-a66b-6870df88ddfe"  // 用于Windows安装器的升级UUID
            }
        }
    }
}
