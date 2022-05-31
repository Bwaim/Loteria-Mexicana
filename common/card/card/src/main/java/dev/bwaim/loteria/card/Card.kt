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

package dev.bwaim.loteria.card

public data class Card(
    public val id: Int,
    public val name: String,
    public val imagePath: String
) {

    public companion object {
        public val cardList: List<Int> = listOf(
//            R.drawable.el_gallo,
//            R.drawable.el_diablito_2,
//            R.drawable.la_dama_3,
//            R.drawable.el_catrin_4,
//            R.drawable.el_paraguas_5,
//            R.drawable.la_escalera_7
        )
    }
}
