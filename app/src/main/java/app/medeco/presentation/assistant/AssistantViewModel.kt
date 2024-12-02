package app.medeco.presentation.assistant

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.domain.model.assistant.AiMessage
import app.medeco.domain.model.assistant.AiMessageType
import app.medeco.domain.model.assistant.NetworkResult
import app.medeco.domain.use_case.assistant.SendAiMessageUseCase
import app.medeco.domain.use_case.assistant.SendAssistantQueryUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
@KoinViewModel
class AssistantViewModel(
    private val sendAiMessage: SendAiMessageUseCase,
    private val sendQuery: SendAssistantQueryUseCase
) : ViewModel() {

    val messages = mutableStateListOf<AiMessage>()
    private val _uiState = MutableStateFlow(AssistantUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AssistantEvent) {
        when (event) {
            is AssistantEvent.SendMessage -> viewModelScope.launch {
                val message = event.message
                messages.add(0, message)

                _uiState.update {
                  it.copy(
                      messages = messages,
                      loading = true,
                      error = null
                  )
                }
                when (val result = sendQuery(event.message.content)) {
                    is NetworkResult.Success -> {
                        messages.add(0, AiMessage(
                            content = result.data,
                            type = AiMessageType.MODEL
                        ))

                        _uiState.update {
                            it.copy(messages = messages, loading = false)
                        }
                    }

                    is NetworkResult.Failure -> {
                        messages.removeAt(0)
                        delay(300)

                        _uiState.update {
                            it.copy(
                                messages = messages,
                                loading = false,
                                error = result
                            )
                        }
                    }
                }
            }

        }
    }
}