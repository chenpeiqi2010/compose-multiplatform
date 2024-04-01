package example.todoapp.lite.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// 定义一个可复用的UI部件（Composable函数）用于显示主要内容
@Composable
internal fun MainContent(
    modifier: Modifier = Modifier, // 允许外部调用时自定义样式修饰符
    items: List<TodoItem>, // 待办事项列表
    inputText: String, // 输入框中的文本
    onItemClicked: (id: Long) -> Unit, // 点击待办事项时的回调函数
    onItemDoneChanged: (id: Long, isDone: Boolean) -> Unit, // 改变待办事项完成状态时的回调函数
    onItemDeleteClicked: (id: Long) -> Unit, // 点击删除按钮时的回调函数
    onAddItemClicked: () -> Unit, // 点击添加按钮时的回调函数
    onInputTextChanged: (String) -> Unit, // 输入框文本变化时的回调函数
) {
    // 使用列布局显示内容
    Column(modifier) {
        // 顶部应用栏，显示标题
        TopAppBar(title = { Text(text = "Todo List") })

        // 使用Box布局包裹待办事项列表，使其填满除输入框和顶部应用栏外的所有空间
        Box(Modifier.weight(1F)) {
            // 显示待办事项列表
            ListContent(
                items = items,
                onItemClicked = onItemClicked,
                onItemDoneChanged = onItemDoneChanged,
                onItemDeleteClicked = onItemDeleteClicked
            )
        }

        // 显示输入框和添加按钮
        Input(
            text = inputText,
            onAddClicked = onAddItemClicked,
            onTextChanged = onInputTextChanged
        )
    }
}


// 定义一个私有的Composable函数，用于渲染待办事项列表
@Composable
private fun ListContent(
    items: List<TodoItem>, // 待办事项数据列表
    onItemClicked: (id: Long) -> Unit, // 点击待办事项时的回调
    onItemDoneChanged: (id: Long, isDone: Boolean) -> Unit, // 更改待办事项完成状态时的回调
    onItemDeleteClicked: (id: Long) -> Unit, // 删除待办事项时的回调
) {
    Box {
        val listState = rememberLazyListState() // 记住列表滚动的状态

        // 使用懒加载列表展示待办事项
        LazyColumn(state = listState) {
            // 为列表中的每个项目生成UI
            items(items) { item ->
                // 渲染单个待办事项
                Item(
                    item = item,
                    onClicked = { onItemClicked(item.id) },
                    onDoneChanged = { onItemDoneChanged(item.id, it) },
                    onDeleteClicked = { onItemDeleteClicked(item.id) }
                )

                Divider() // 在每个待办事项下方添加分隔线
            }
        }

        // 显示一个垂直滚动条
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(), // 修饰符，定位在右侧中央，并填充最大高度
            adapter = rememberScrollbarAdapter(scrollState = listState) // 使用自定义的滚动条适配器
        )
    }
}


// 定义一个私有的Composable函数，用于渲染单个待办事项
@Composable
private fun Item(
    item: TodoItem, // 单个待办事项的数据
    onClicked: () -> Unit, // 点击事件的回调函数
    onDoneChanged: (Boolean) -> Unit, // 完成状态变更的回调函数
    onDeleteClicked: () -> Unit // 删除事件的回调函数
) {
    // 水平布局，用于展示待办事项
    Row(modifier = Modifier.clickable(onClick = onClicked)) {
        Spacer(modifier = Modifier.width(8.dp)) // 左侧的间隙

        // 显示复选框，用于标记待办事项是否完成
        Checkbox(
            checked = item.isDone,
            modifier = Modifier.align(Alignment.CenterVertically), // 垂直居中
            onCheckedChange = onDoneChanged,
        )

        Spacer(modifier = Modifier.width(8.dp)) // 复选框和文本之间的间隙

        // 显示待办事项的文本
        Text(
            text = AnnotatedString(item.text), // 文本内容
            modifier = Modifier.weight(1F).align(Alignment.CenterVertically), // 填充剩余空间，并垂直居中
            maxLines = 1, // 最多显示一行
            overflow = TextOverflow.Ellipsis // 文本过长时使用省略号
        )

        Spacer(modifier = Modifier.width(8.dp)) // 文本和删除按钮之间的间隙

        // 显示删除按钮
        IconButton(onClick = onDeleteClicked) {
            Icon(
                imageVector = Icons.Default.Delete, // 使用内置的删除图标
                contentDescription = null // 无内容描述
            )
        }

        Spacer(modifier = Modifier.width(MARGIN_SCROLLBAR)) // 右侧的间隙，预留滚动条空间
    }
}


// 使用ExperimentalComposeUiApi注解，因为onKeyUp是实验性API
@OptIn(ExperimentalComposeUiApi::class)
// 定义一个私有的Composable函数，用于渲染输入框和添加按钮
@Composable
private fun Input(
    text: String, // 输入框中的文本
    onTextChanged: (String) -> Unit, // 文本变化的回调函数
    onAddClicked: () -> Unit // 添加按钮点击的回调函数
) {
    // 使用水平布局显示输入框和添加按钮
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        // 显示输入框
        OutlinedTextField(
            value = text,
            modifier = Modifier
                .weight(weight = 1F) // 填充剩余空间
                .onKeyUp(key = Key.Enter, action = onAddClicked), // 按Enter键时触发添加操作
            onValueChange = onTextChanged, // 文本变化时的回调
            label = { Text(text = "Add a todo") } // 输入框的标签
        )

        Spacer(modifier = Modifier.width(8.dp)) // 输入框和添加按钮之间的间隙

        // 显示添加按钮
        IconButton(onClick = onAddClicked) {
            Icon(
                imageVector = Icons.Default.Add, // 使用内置的添加图标
                contentDescription = null // 无内容描述
            )
        }
    }
}

