package com.xk.base.data


import androidx.annotation.Keep

@Keep
data class ResponseExamResponse(
    val code: Int?,
    val msg: String?,
    val rows: List<Row?>?,
    val total: Int?,
    val totalPage: Int?
) {
    @Keep
    data class Row(
        val creatId: String?,
        val creatTime: Any?,
        val createBy: Any?,
        val createTime: Any?,
        val endTime: Any?,
        val examName: String?,
        val examType: Any?,
        val id: Int?,
        val initTime: Any?,
        val pId: Any?,
        val priorityType: Any?,
        val remark: Any?,
        val sectionType: Any?,
        val status: Int?,
        val total: Any?,
        val upType: Any?,
        val updateBy: Any?,
        val updateId: Any?,
        val updateTime: Any?
    )
}