package dev.bwaim.loteria.compose.design.preference.model

public data class Preference<T>(
    val label: String,
    val value: T
)

public data class ListPreferenceValues<T>(
    val title: String = "",
    val entries: Map<String, Preference<T>> = emptyMap()
)
