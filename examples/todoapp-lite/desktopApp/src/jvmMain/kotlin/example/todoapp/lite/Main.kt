package example.todoapp.lite  // 定义包名

import MainView
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() {  // 程序入口点
    application {  // 启动一个Compose for Desktop应用
        Window(
            onCloseRequest = ::exitApplication,  // 当窗口关闭时的处理函数，这里是退出应用
            title = "TodoApp Lite",  // 窗口标题
            state = rememberWindowState(
                position = WindowPosition(alignment = Alignment.Center),  // 初始窗口位置，这里设置为屏幕中心
            ),
        ) {
            MaterialTheme {  // 应用Material Design主题
                MainView()  // 调用MainView，这里是UI的入口点，使用Jetpack Compose构建UI
            }
        }
    }
}
