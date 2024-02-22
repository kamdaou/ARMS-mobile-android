package com.amalitech.arms_mobile.data.entities

import com.amalitech.WhoIsOutQuery

data class StaffEntity(
    val id: String? = null,
    val name: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val type: String? = null,
    val position: String? = null,
    val image: String? = null,
)

fun WhoIsOutQuery.Today.toStaffEntity(): StaffEntity {
    val bio = user?.employee_info?.employee_bio

    return StaffEntity(
        id = null,
        name = bio?.full_name ?: "JohnSon Doe",
        image = bio?.profile_image,
        position = user?.employee_info?.position?.position_name,
        type = user?.employee_info?.employee_type?.name,
    )
}

fun WhoIsOutQuery.Tomorrow.toStaffEntity(): StaffEntity {
    val bio = user?.employee_info?.employee_bio

    return StaffEntity(
        id = null,
        name = bio?.full_name ?: "JohnSon Doe",
        image = bio?.profile_image,
        position = user?.employee_info?.position?.position_name,
        type = user?.employee_info?.employee_type?.name,
    )
}


