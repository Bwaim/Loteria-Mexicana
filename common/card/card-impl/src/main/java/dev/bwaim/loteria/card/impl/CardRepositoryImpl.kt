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

import dev.bwaim.loteria.card.Card
import dev.bwaim.loteria.card.CardRepository
import dev.bwaim.loteria.card.db.CardDao
import dev.bwaim.loteria.card.db.CardEntity
import dev.bwaim.loteria.card.db.asExternalModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CardRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
) : CardRepository {

    override fun observeCards(): Flow<List<Card>> =
        cardDao.observeCards()
            .map { it.map(CardEntity::asExternalModel) }
}
