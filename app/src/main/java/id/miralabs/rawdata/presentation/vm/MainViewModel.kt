package id.miralabs.rawdata.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.miralabs.rawdata.data.dto.Type
import id.miralabs.rawdata.domain.LoadTypeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadTypeListUseCase: LoadTypeListUseCase
) : ViewModel() {

    private val _typeListUiState = MutableStateFlow<MainUiState>(MainUiState.Loading())
    val typeListUiState: StateFlow<MainUiState> = _typeListUiState

    fun loadTypeList() = viewModelScope.launch {
        _typeListUiState.value = MainUiState.Loading()
        loadTypeListUseCase().collect {
            _typeListUiState.value = MainUiState.Loaded(it)
        }
    }

    sealed class MainUiState {
        data class Loading(val typeList: List<Type>? = null) : MainUiState()
        data class Loaded(val typeList: List<Type>) : MainUiState()
    }
}