package com.xk.base.data


import androidx.annotation.Keep

@Keep
data class DictCode(
    val code: Int?,
    val `data`: List<Data?>?,
    val msg: String?
) {
    @Keep
    data class Data(
        val createBy: Any?,
        val createTime: Any?,
        val cssClass: Any?,
        val default: Boolean?,
        val dictCode: Int?,
        val dictLabel: String?,
        val dictSort: Any?,
        val dictType: Any?,
        val dictValue: Any?,
        val isDefault: Any?,
        val listClass: Any?,
        val remark: Any?,
        val status: Any?,
        val updateBy: Any?,
        val updateTime: Any?
    )
}