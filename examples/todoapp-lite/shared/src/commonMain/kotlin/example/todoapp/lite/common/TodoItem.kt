package example.todoapp.lite.common

// 定义待办事项的数据类
internal data class TodoItem(
    val id: Long = 0L, // 待办事项的唯一标识ID
    val text: String = "", // 待办事项的文本内容
    val isDone: Boolean = false // 待办事项是否已完成
)