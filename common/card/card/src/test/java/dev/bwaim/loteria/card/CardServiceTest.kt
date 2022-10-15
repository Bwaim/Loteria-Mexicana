/*
 * Copyright 2022 Dev Bwaim team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.bwaim.loteria.card

import dev.bwaim.loteria.card.testdoubles.TestCardRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class CardServiceTest {
    private lateinit var subject: CardService

    private lateinit var cardRepository: TestCardRepository
    @Before
    fun setUp() {
        val ioDispatcher = UnconfinedTestDispatcher()
        cardRepository = TestCardRepository()
        subject = CardService(ioDispatcher, cardRepository)
    }
    @Test
    fun cardService_cards_stream_is_backed_by_card_repository() = runTest {
        cardRepository.sendCards(
            listOf(
                Card(0, "card1"),
                Card(1, "card2")
            )
        )
        Assert.assertEquals(
            cardRepository.observeCards().first(),
            subject.observeCards().first()
        )
    }
}
