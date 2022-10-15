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

package dev.bwaim.loteria.card.impl

import dev.bwaim.loteria.card.db.CardDao
import dev.bwaim.loteria.card.db.CardEntity
import dev.bwaim.loteria.card.db.asExternalModel
import dev.bwaim.loteria.card.impl.testdoubles.TestCardDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CardRepositoryImplTest {

    private lateinit var subject: CardRepositoryImpl

    private lateinit var cardDao: CardDao

    @Before
    fun setup() {
        cardDao = TestCardDao()

        subject = CardRepositoryImpl(cardDao = cardDao)
    }

    @Test
    fun observe_cards_is_backed_by_card_dao() = runTest {
        assertEquals(
            cardDao.observeCards()
                .first()
                .map(CardEntity::asExternalModel),
            subject.observeCards()
                .first()
        )
    }
}
