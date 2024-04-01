plugins {
    kotlin("multiplatform")  // Kotlin多平台插件
    id("com.android.application")  // Android应用插件
    id("org.jetbrains.compose")  // Jetpack Compose插件
}

kotlin {
    androidTarget()  // 定义Android目标平台
    sourceSets {
        val androidMain by getting {  // Android主源代码集
            dependencies {
                implementation(project(":shared"))  // 依赖共享模块
                implementation(compose.material)  // 依赖Jetpack Compose的Material设计组件库
            }
        }
    }
}

android {
    compileSdk = 34  // 编译SDK版本
    namespace = "example.todoapp.lite"  // 应用的命名空间
    defaultConfig {
        applicationId = "org.jetbrains.TodoAppLite"  // 应用ID
        minSdk = 26  // 最小支持的SDK版本
        targetSdk = 34  // 目标SDK版本
        versionCode = 1  // 版本号
        versionName = "1.0"  // 版本名称
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17  // 源代码兼容的Java版本
        targetCompatibility = JavaVersion.VERSION_17  // 目标Java版本
    }
    kotlin {
        jvmToolchain(17)  // 使用Java 17工具链
    }
}