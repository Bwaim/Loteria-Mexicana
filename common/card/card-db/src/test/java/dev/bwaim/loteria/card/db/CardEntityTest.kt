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

package dev.bwaim.loteria.card.db

import dev.bwaim.loteria.localization.CardName
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CardEntityTest {

    @Test
    fun card_entity_can_be_mapped_to_card() {
        val cardEntity = CardEntity(
            id = 0,
            name = "CARD_1",
        )

        val card = cardEntity.asExternalModel()

        assertEquals(0, card.id)
        assertEquals(CardName.CARD_1.stringId, card.nameId)
    }
}
