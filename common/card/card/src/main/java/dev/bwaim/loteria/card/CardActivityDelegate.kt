package dev.bwaim.loteria.card

import kotlinx.coroutines.flow.StateFlow

public interface CardActivityDelegate {
    public val card: StateFlow<Card>
}