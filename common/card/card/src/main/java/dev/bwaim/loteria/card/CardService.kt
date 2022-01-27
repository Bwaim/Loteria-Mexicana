package dev.bwaim.loteria.card

import dev.bwaim.loteria.coroutines.IODispatcher
import dev.bwaim.loteria.theme.Theme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

public class CardService @Inject internal constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
//    private val cardRepository: CardRepository
) {
    //    public fun observeCard(): Flow<Card> {
//        return cardRepository
//            .observeCard()
//            .flowOn(ioDispatcher)
//    }
    public suspend fun getCards(): List<Int> = withContext(ioDispatcher) {
        Card.cardList
    }
}