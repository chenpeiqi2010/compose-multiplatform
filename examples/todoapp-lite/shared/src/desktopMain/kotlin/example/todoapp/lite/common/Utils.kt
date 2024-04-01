// 指定在JVM平台上生成的类名
@file:JvmName("Utils")

package example.todoapp.lite.common

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// 实际实现滚动条的边距，针对桌面平台，设置为8dp
internal actual val MARGIN_SCROLLBAR: Dp = 8.dp

// 解决不匹配的expect/actual声明问题的临时解决方案
@Suppress("ACTUAL_WITHOUT_EXPECT")
// 为桌面平台定义ScrollbarAdapter类型别名，直接使用Compose提供的ScrollbarAdapter
internal actual typealias ScrollbarAdapter = androidx.compose.foundation.v2.ScrollbarAdapter

// 实际创建并记住ScrollbarAdapter的实现，利用Compose自带的rememberScrollbarAdapter方法
@Composable
internal actual fun rememberScrollbarAdapter(scrollState: LazyListState): ScrollbarAdapter =
    androidx.compose.foundation.rememberScrollbarAdapter(scrollState)

// 实现桌面平台的垂直滚动条，直接使用Compose框架中的VerticalScrollbar组件
@Composable
internal actual fun VerticalScrollbar(
    modifier: Modifier, // 修饰符参数，用于自定义滚动条样式
    adapter: ScrollbarAdapter // 滚动条适配器，用于与列表状态关联
) {
    androidx.compose.foundation.VerticalScrollbar(
        modifier = modifier, // 应用传入的修饰符
        adapter = adapter // 使用传入的滚动条适配器
    )
}
