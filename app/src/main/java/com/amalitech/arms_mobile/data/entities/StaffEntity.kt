package com.amalitech.arms_mobile.data.models

import com.amalitech.WhoIsOutQuery
import com.amalitech.arms_mobile.domain.entities.StaffEntity

data class Staff(override val name: String) : StaffEntity(name)


fun WhoIsOutQuery.User.toStaff() : Staff {
    val bio = employee_info!!.employee_bio

    return Staff(name = bio!!.full_name!!)
}

fun WhoIsOutQuery.User1.toStaff() : Staff {
    val bio = employee_info!!.employee_bio

    return Staff(name = bio!!.full_name!!)
}

