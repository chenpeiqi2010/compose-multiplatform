import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import example.todoapp.lite.common.RootContent

@Composable fun MainView() = RootContent(Modifier.fillMaxSize())
// 定义了一个Composable函数MainView，它简单地调用了RootContent，并传入了一个Modifier来使内容充满整个屏幕。
