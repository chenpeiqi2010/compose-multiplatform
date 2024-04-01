package example.todoapp.lite  // 包名

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

class MainActivity : AppCompatActivity() {  // MainActivity继承自AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {  // 当Activity创建时被调用
        super.onCreate(savedInstanceState)

        setContent {  // 设置Activity的内容
            MaterialTheme {  // 使用Material Design主题
                Surface(color = MaterialTheme.colors.background) {  // 创建一个Surface，背景色使用Material主题的背景色
                    MainView()  // 调用MainView，这是使用Jetpack Compose构建的UI入口
                }
            }
        }
    }
}