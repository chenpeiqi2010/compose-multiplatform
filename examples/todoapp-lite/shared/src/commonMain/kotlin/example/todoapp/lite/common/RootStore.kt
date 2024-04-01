package example.todoapp.lite.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// 定义应用的根存储（RootStore），负责管理整个应用的状态
internal class RootStore {

    // 应用的状态，使用代理属性和 mutableStateOf 来实现可观察和响应式的状态更新
    var state: RootState by mutableStateOf(initialState())
        private set // 外部只能读取状态，不能直接修改，确保状态更新的唯一性和可控性

    // 处理待办事项被点击的事件，设置该待办事项为编辑状态
    fun onItemClicked(id: Long) {
        setState { copy(editingItemId = id) }
    }

    // 处理待办事项完成状态被更改的事件
    fun onItemDoneChanged(id: Long, isDone: Boolean) {
        setState {
            updateItem(id = id) { it.copy(isDone = isDone) }
        }
    }

    // 处理删除待办事项的事件
    fun onItemDeleteClicked(id: Long) {
        setState { copy(items = items.filterNot { it.id == id }) }
    }

    // 处理添加新待办事项的事件
    fun onAddItemClicked() {
        setState {
            val newItem =
                TodoItem(
                    id = items.maxOfOrNull(TodoItem::id)?.plus(1L) ?: 1L, // 为新待办事项生成唯一ID
                    text = inputText, // 使用当前输入框文本作为待办事项内容
                )

            copy(items = items + newItem, inputText = "") // 更新状态，清空输入框
        }
    }

    // 处理输入框文本变化的事件
    fun onInputTextChanged(text: String) {
        setState { copy(inputText = text) }
    }

    // 处理关闭编辑器的事件
    fun onEditorCloseClicked() {
        setState { copy(editingItemId = null) }
    }

    // 处理编辑待办事项文本变化的事件
    fun onEditorTextChanged(text: String) {
        setState {
            updateItem(id = requireNotNull(editingItemId)) { it.copy(text = text) }
        }
    }

    // 处理编辑待办事项完成状态变化的事件
    fun onEditorDoneChanged(isDone: Boolean) {
        setState {
            updateItem(id = requireNotNull(editingItemId)) { it.copy(isDone = isDone) }
        }
    }

    // 更新指定ID的待办事项
    private fun RootState.updateItem(id: Long, transformer: (TodoItem) -> TodoItem): RootState =
        copy(items = items.updateItem(id = id, transformer = transformer))

    // 在待办事项列表中找到指定ID的待办事项并进行更新
    private fun List<TodoItem>.updateItem(id: Long, transformer: (TodoItem) -> TodoItem): List<TodoItem> =
        map { item -> if (item.id == id) transformer(item) else item }

    // 初始化应用状态
    private fun initialState(): RootState =
        RootState(
            items = (1L..5L).map { id ->
                TodoItem(id = id, text = "Some text $id") // 生成初始的待办事项列表
            }
        )

    // 更新状态的通用方法，接受一个状态的变更函数，并应用它来更新状态
    private inline fun setState(update: RootState.() -> RootState) {
        state = state.update()
    }

    // 定义应用状态的数据类
    data class RootState(
        val items: List<TodoItem> = emptyList(), // 待办事项列表
        val inputText: String = "", // 当前输入框文本
        val editingItemId: Long? = null, // 正在编辑的待办事项ID，为null表示没有待办事项在编辑中
    )
}

