package data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val idCardCompany: Int,
    val fullName: String,
    val businessName: String,
    val email: String,
)
