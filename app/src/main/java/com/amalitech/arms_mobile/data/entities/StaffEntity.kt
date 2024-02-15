package com.amalitech.arms_mobile.data.entities

import com.amalitech.WhoIsOutQuery

data class StaffEntity(
    val name: String
)

fun WhoIsOutQuery.Today.toStaffEntity() : StaffEntity {
    val bio = user!!.employee_info!!.employee_bio

    return StaffEntity(name = bio!!.full_name!!)
}

fun WhoIsOutQuery.Tomorrow.toStaffEntity() : StaffEntity {
    val bio = user!!.employee_info!!.employee_bio

    return StaffEntity(name = bio!!.full_name!!)
}


