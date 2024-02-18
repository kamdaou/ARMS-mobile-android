package com.amalitech.arms_mobile.domain.entities

import com.amalitech.WhoIsOutQuery

open class StaffEntity(
    open val name: String
)

fun WhoIsOutQuery.User.toStaffEntity() : StaffEntity {
    return StaffEntity(name = employee_info!!.employee_bio!!.full_name!!)
}

fun WhoIsOutQuery.User1.toStaffEntity() : StaffEntity {
    return StaffEntity(name = employee_info!!.employee_bio!!.full_name!!)
}
