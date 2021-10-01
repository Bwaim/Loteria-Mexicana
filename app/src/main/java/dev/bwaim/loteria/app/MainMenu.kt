package dev.bwaim.loteria.app

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import dev.bwaim.loteria.compose.TopAppBarTitle

private typealias MainMenuActioner = (MainMenuAction) -> Unit

@Composable
public fun MainMenu(
    openSettings: () -> Unit,
    startDraw: () -> Unit
) {
    MainMenu { action ->
        when (action) {
            OpenSettings -> openSettings()
            StartDraw -> startDraw()
        }
    }
}

@Composable
private fun MainMenu(
    actioner: MainMenuActioner
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MainMenuAppBar() }
    ) {
        Column {

        }
    }
}

@Composable
private fun MainMenuAppBar() {
    TopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.app_name)) },
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.statusBars,
            applyBottom = false
        )
    )
}