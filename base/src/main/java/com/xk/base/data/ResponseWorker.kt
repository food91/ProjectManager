package com.xk.base.data


import androidx.annotation.Keep

@Keep
data class ResponseWorker(
    val code: Int?,
    val `data`: Data?,
    val msg: String?
) {
    @Keep
    data class Data(
        val contract: List<Any?>?,
        val project: List<Project?>?
    ) {
        @Keep
        data class Project(
            val age: Int?,
            val bankAccountNumber: String?,
            val bankDeposit: String?,
            val cId: String?,
            val checkoutType: Int?,
            val coefficient: Any?,
            val createBy: Any?,
            val createTime: Any?,
            val delete: Any?,
            val ebJob: String?,
            val employId: String?,
            val familyName: String?,
            val founder: String?,
            val groupId: Any?,
            val id: Int?,
            val idNumber: Int?,
            val img: String?,
            val name: String?,
            val number: Any?,
            val onbordTime: String?,
            val pId: String?,
            val partjob: Any?,
            val password: String?,
            val pieceworkWage: Int?,
            val projectId: Any?,
            val rank: Int?,
            val remark: Any?,
            val sex: Int?,
            val updateBy: Any?,
            val updateTime: Any?,
            val userName: String?,
            val wage: Int?,
            val wageType: Int?,
            val workCondit: Any?,
            val workType: String?
        )
    }
}