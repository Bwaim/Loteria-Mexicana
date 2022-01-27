package dev.bwaim.loteria.draw

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.bwaim.loteria.card.Card
import dev.bwaim.loteria.card.CardService
import dev.bwaim.loteria.theme.ThemeService
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class DrawViewModel @Inject constructor(
    private val cardService: CardService
) : ViewModel() {

//    val cardChannel = Channel<Card>(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val _viewState: MutableStateFlow<DrawState> = MutableStateFlow(DrawState())
    public val viewState: StateFlow<DrawState> = _viewState

    init {
        viewModelScope.launch {
            val cards = cardService.getCards()
            cards.forEach {
                _viewState.value = DrawState(it)
                delay(5000)
            }
        }
    }
}
