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

package dev.bwaim.loteria.database.dao

import dev.bwaim.loteria.card.db.CardDao
import dev.bwaim.loteria.card.db.CardEntity
import dev.bwaim.loteria.test.android.createDb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CardDaoTest {
    private lateinit var cardDao: CardDao

    @Before
    fun setUp() {
        val db = createDb()
        cardDao = db.cardDao()
    }

    @Test
    fun cardDao_fetches_list_of_cards() = runTest {
        val cardEntities = listOf(
            testCard(1, "El gallo"),
            testCard(2, "El diablito")
        )

        cardDao.upsertCards(cardEntities)

        val savedCardEntities = cardDao.observeCards().first()

        assertEquals(
            listOf(1, 2),
            savedCardEntities.map { it.id }
        )
    }

    private fun testCard(
        id: Int,
        name: String
    ): CardEntity = CardEntity(
        id = id,
        name = name
    )
}
