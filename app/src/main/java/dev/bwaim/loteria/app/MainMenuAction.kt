package dev.bwaim.loteria.app

internal sealed interface MainMenuAction
internal object OpenSettings : MainMenuAction
internal object OpenDraw : MainMenuAction
