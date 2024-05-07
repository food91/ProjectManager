package com.xk.base.data


import androidx.annotation.Keep

@Keep
data class WageRequest(
    val age: Int?,
    val bankAccountNumber: String?,
    val bankDeposit: String?,
    val cId: String?,
    val checkoutType: Int?,
    val coefficient: Int?,
    val createBy: String?,
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
    val number: Int?,
    val onbordTime: String?,
    val pId: String?,
    val params: Params?,
    val partjob: List<String?>?,
    val password: String?,
    val pieceworkWage: Int?,
    val projectId: Int?,
    val rank: Int?,
    val remark: String?,
    val sex: Int?,
    val updateBy: String?,
    val updateTime: String?,
    val userName: String?,
    val wage: Int?,
    val wageType: Int?,
    val workCondit: String?,
    val workType: String?
) {

    @Keep
    class Params
}