@file:JvmName("Utils")  // 指定在生成的Java字节码中，这个Kotlin文件的类名将是Utils。

package example.todoapp.lite.common  // 包名定义

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal actual val MARGIN_SCROLLBAR: Dp = 0.dp  // 定义一个实际的平台特定值，这里是滚动条的边距，默认为0。

internal actual interface ScrollbarAdapter  // 定义一个实际的平台特定接口，用于适配滚动条。

@Composable
internal actual fun rememberScrollbarAdapter(scrollState: LazyListState): ScrollbarAdapter =
    object : ScrollbarAdapter {}  // 实际的平台特定函数，用于记住滚动条适配器。这里是一个空实现。

@Composable
internal actual fun VerticalScrollbar(
    modifier: Modifier,  // 接受一个修饰符作为参数
    adapter: ScrollbarAdapter  // 接受一个滚动条适配器作为参数
) {
    // no-op，这是一个空操作实现，意味着在这个平台上，垂直滚动条可能没有默认的UI表现或需要特定条件下实现。
}
