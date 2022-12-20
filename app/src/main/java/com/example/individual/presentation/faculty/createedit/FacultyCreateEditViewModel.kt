package com.example.individual.presentation.faculty.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.FacultyRepository
import com.example.individual.model.Faculty
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

// https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/527-urok-4-viewmodel.html
class FacultyCreateEditViewModel : ViewModel() {

    // https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/525-urok-2-livedata.html
    val facultyLiveData = MutableLiveData<Faculty?>()
    private val facultyRepository = FacultyRepository.getInstance()

    fun getFaculty(id: Long) {
        // Запуск асинхронного потока
        // viewModelScope - https://developer.android.com/topic/libraries/architecture/coroutines#viewmodelscope
        // launch https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html
        // В общем про корутины https://kotlinlang.org/docs/coroutines-basics.html#your-first-coroutine
        // defaultErrorHandler - перехватчик ошибок. Ошибки могут быть, когда какие-то косяки с базой или нет интернета.
        // Сообщения пишутся в логи. В приложении просто не будут отображаться данные
        viewModelScope.launch(defaultErrorHandler) {
            facultyLiveData.postValue(facultyRepository.getFacultyById(id))
        }
    }

    fun saveFaculty(name: String) {
        viewModelScope.launch(defaultErrorHandler) {
            // Т.к. у нас сохранение и изменение на одном экране, то проверяем, есть ли что-то в live data
            // если там не null, значит это было изменение и нужно
            // Аналогично происходит со студентами и группами
            facultyLiveData.value?.let {
                facultyRepository.update(
                    Faculty(
                        id = it.id,
                        name = it.name
                    )
                )
            }
                ?: facultyRepository.add(Faculty(id = 0, name = name))
        }
    }

    fun deleteFaculty() {
        facultyLiveData.value?.let { faculty ->
            viewModelScope.launch(defaultErrorHandler) {
                facultyRepository.delete(faculty)
            }
        }
    }
}