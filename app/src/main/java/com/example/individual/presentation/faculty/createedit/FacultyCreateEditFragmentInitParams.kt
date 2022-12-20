package com.example.individual.presentation.faculty.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

/**
 * Параметры, передаваемые во фрагмент при навигации
 */

@Parcelize
data class FacultyCreateEditFragmentInitParams(val id: Long?) : InitParams