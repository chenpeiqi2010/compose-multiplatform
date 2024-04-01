package example.todoapp.lite.common

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import example.todoapp.lite.common.RootStore.RootState

// 定义一个可组合（Composable）函数作为根内容，用于整个待办事项应用的入口
@Composable
fun RootContent(modifier: Modifier = Modifier) {
    // 使用remember构建并记住RootStore的实例，这样可以在重组时保持状态不变
    val model = remember { RootStore() }
    // 从模型中获取当前的应用状态
    val state = model.state

    // 调用MainContent函数显示应用的主界面，传递来自模型的数据和事件处理函数
    MainContent(
        modifier = modifier.background(MaterialTheme.colors.background), // 设置背景色
        items = state.items, // 待办事项列表
        inputText = state.inputText, // 输入框文本
        onItemClicked = model::onItemClicked, // 点击待办事项的处理函数
        onItemDoneChanged = model::onItemDoneChanged, // 更改待办事项完成状态的处理函数
        onItemDeleteClicked = model::onItemDeleteClicked, // 删除待办事项的处理函数
        onAddItemClicked = model::onAddItemClicked, // 添加新待办事项的处理函数
        onInputTextChanged = model::onInputTextChanged, // 输入框文本变化的处理函数
    )

    // 如果存在正在编辑的待办事项，则显示编辑对话框
    state.editingItem?.also { item ->
        EditDialog(
            item = item, // 正在编辑的待办事项
            onCloseClicked = model::onEditorCloseClicked, // 关闭编辑对话框的处理函数
            onTextChanged = model::onEditorTextChanged, // 编辑文本变化的处理函数
            onDoneChanged = model::onEditorDoneChanged, // 更改待办事项完成状态的处理函数
        )
    }
}

// 扩展函数，用于从RootState中获取当前正在编辑的待办事项
private val RootState.editingItem: TodoItem?
    get() = editingItemId?.let(items::firstById) // 根据编辑中的待办事项ID从列表中查找对应的待办事项

// 扩展函数，从待办事项列表中根据ID查找第一个匹配的待办事项
private fun List<TodoItem>.firstById(id: Long): TodoItem? =
    firstOrNull { it.id == id }
