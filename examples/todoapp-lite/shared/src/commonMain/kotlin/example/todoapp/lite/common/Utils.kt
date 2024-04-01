package example.todoapp.lite.common

// 导入必要的Compose和UI库
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.Dp

// 定义一个预期的（expect）变量，用于表示滚动条的边距，具体实现由各平台提供
internal expect val MARGIN_SCROLLBAR: Dp

// 定义一个预期的接口，用于滚动条适配器，具体实现由各平台提供
internal expect interface ScrollbarAdapter

// 定义一个预期的函数，用于记住滚动条适配器的实例，具体实现由各平台提供
@Composable
internal expect fun rememberScrollbarAdapter(scrollState: LazyListState): ScrollbarAdapter

// 定义一个预期的函数，用于显示垂直滚动条，具体实现由各平台提供
@Composable
internal expect fun VerticalScrollbar(
    modifier: Modifier,
    adapter: ScrollbarAdapter
)

// 定义一个扩展函数，用于处理键盘事件，在键被释放时执行特定的动作
internal fun Modifier.onKeyUp(key: Key, action: () -> Unit): Modifier =
    onKeyEvent { event ->
        if ((event.type == KeyEventType.KeyUp) && (event.key == key)) {
            action() // 如果匹配到特定的键被释放，执行动作
            true // 返回true表示事件已被处理
        } else {
            false // 否则返回false
        }
    }
