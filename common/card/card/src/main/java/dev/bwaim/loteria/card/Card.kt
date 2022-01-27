package dev.bwaim.loteria.card

public data class Card(
    public val id: Int,
    public val name: String,
    public val imagePath: String
) {

    public companion object {
        public val cardList: List<Int> = listOf(
            R.drawable.el_gallo,
            R.drawable.el_diablito_2,
            R.drawable.la_dama_3,
            R.drawable.el_catrin_4,
            R.drawable.el_paraguas_5,
            R.drawable.la_escalera_7
        )
    }
}