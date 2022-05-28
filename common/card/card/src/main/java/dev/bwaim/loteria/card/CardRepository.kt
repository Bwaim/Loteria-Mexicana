package dev.bwaim.loteria.card

import kotlinx.coroutines.flow.Flow

public interface CardRepository {
    public fun observeCard(): Flow<Card>
}
