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

package dev.bwaim.loteria.localization

import androidx.annotation.StringRes
import dev.bwaim.loteria.localization.R.string

public enum class CardName(@StringRes public val stringId: Int) {
    CARD_1(string.card_1),
    CARD_2(string.card_2),
    CARD_3(string.card_3),
    CARD_4(string.card_4),
    CARD_5(string.card_5),
    CARD_6(string.card_6),
    CARD_7(string.card_7),
    CARD_8(string.card_8),
    CARD_9(string.card_9),
    CARD_10(string.card_10),
    CARD_11(string.card_11),
    CARD_12(string.card_12),
    CARD_13(string.card_13),
    CARD_14(string.card_14),
    CARD_15(string.card_15),
    CARD_16(string.card_16),
    CARD_17(string.card_17),
    CARD_18(string.card_18),
    CARD_19(string.card_19),
    CARD_20(string.card_20),
    CARD_21(string.card_21),
    CARD_22(string.card_22),
    CARD_23(string.card_23),
    CARD_24(string.card_24),
    CARD_25(string.card_25),
    CARD_26(string.card_26),
    CARD_27(string.card_27),
    CARD_28(string.card_28),
    CARD_29(string.card_29),
    CARD_30(string.card_30),
    CARD_31(string.card_31),
    CARD_32(string.card_32),
    CARD_33(string.card_33),
    CARD_34(string.card_34),
    CARD_35(string.card_35),
    CARD_36(string.card_36),
    CARD_37(string.card_37),
    CARD_38(string.card_38),
    CARD_39(string.card_39),
    CARD_40(string.card_40),
    CARD_41(string.card_41),
    CARD_42(string.card_42),
    CARD_43(string.card_43),
    CARD_44(string.card_44),
    CARD_45(string.card_45),
    CARD_46(string.card_46),
    CARD_47(string.card_47),
    CARD_48(string.card_48),
    CARD_49(string.card_49),
    CARD_50(string.card_50),
    CARD_51(string.card_51),
    CARD_52(string.card_52),
    CARD_53(string.card_53),
    CARD_54(string.card_54)
    ;

    public companion object {
        public fun valueOf(name: String): CardName =
            CardName.values().first { card -> card.name == name }
    }
}
