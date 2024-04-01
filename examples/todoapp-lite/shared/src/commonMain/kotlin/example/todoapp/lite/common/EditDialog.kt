package example.todoapp.lite.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
internal fun EditDialog(
    item: TodoItem,  // 待编辑的待办事项
    onCloseClicked: () -> Unit,  // 关闭对话框时的回调函数
    onTextChanged: (String) -> Unit,  // 待办事项文本变化时的回调函数
    onDoneChanged: (Boolean) -> Unit,  // 待办事项完成状态变化时的回调函数
) {
    EditDialog(  // 调用私有的EditDialog函数来创建对话框的外层结构
        onCloseRequest = onCloseClicked,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {  // 纵向排列UI组件
            TextField(  // 文本输入框，用于编辑待办事项的文本
                value = item.text,
                modifier = Modifier.weight(1F).fillMaxWidth().sizeIn(minHeight = 192.dp),
                label = { Text("Todo text") },
                onValueChange = onTextChanged,
            )

            Spacer(modifier = Modifier.height(8.dp))  // 用于添加间隔

            Row {  // 水平排列UI组件
                Text(text = "Completed", Modifier.padding(15.dp))

                Checkbox(  // 复选框，用于标记待办事项是否完成
                    checked = item.isDone,
                    onCheckedChange = onDoneChanged,
                )
            }
        }
    }
}

@Composable
private fun EditDialog(
    onCloseRequest: () -> Unit,  // 对话框关闭请求的回调
    content: @Composable () -> Unit  // 对话框内容，作为一个Composable函数传入
) {
    Dialog(  // 创建一个对话框
        onDismissRequest = onCloseRequest,  // 设置对话框关闭时的处理
    ) {
        Card(elevation = 8.dp) {  // 使用卡片作为对话框的容器，以便于在视觉上突出显示
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .height(IntrinsicSize.Min)  // 设置最小高度
            ) {
                ProvideTextStyle(MaterialTheme.typography.subtitle1) {
                    Text(text = "Edit todo")  // 对话框的标题
                }

                Spacer(modifier = Modifier.height(8.dp))  // 间隔

                Box(modifier = Modifier.weight(1F)) {
                    content()  // 插入传入的对话框内容
                }

                Spacer(modifier = Modifier.height(8.dp))  // 间隔

                Button(  // 定义一个按钮，用于关闭对话框
                    onClick = onCloseRequest,
                    modifier = Modifier.align(Alignment.End)  // 按钮对齐到右边
                ) {
                    Text(text = "Done")  // 按钮文本
                }
            }
        }
    }
}
