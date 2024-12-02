package app.medeco.presentation.pharmacy.branch

import app.medeco.data.remote.pharmacy.model.CreatePharmacyBranchBody

sealed class ManageBranchesEvent {
    data class AddBranch(val branch: CreatePharmacyBranchBody): ManageBranchesEvent()
}