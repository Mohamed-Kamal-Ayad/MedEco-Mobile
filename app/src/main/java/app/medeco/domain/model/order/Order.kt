package app.medeco.domain.model.order

import app.medeco.domain.model.drug.Drug
import app.medeco.domain.model.pharmacy.PharmacyBranch
import app.medeco.domain.model.user.User

data class Order(
    val id: Int,
    val qty: Int,
    val user: User,
    val drug: Drug,
    val pharmacyBranch: PharmacyBranch,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)