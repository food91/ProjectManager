package com.xk.base.data


import androidx.annotation.Keep

@Keep
data class ResponseFindProjectList(
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
            val coefficient: Double?,
            val createBy: Any?,
            val createTime: String?,
            val ebJob: String?,
            val employId: String?,
            val familyName: String?,
            val founder: String?,
            val groupId: String?,
            val id: Int?,
            val idNumber: Int?,
            val img: String?,
            val isremove: Int?,
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
            val updateTime: String?,
            val userName: String?,
            val wage: Int?,
            val wageType: Int?,
            val workCondit: String?,
            val workType: String?
        )
    }
}