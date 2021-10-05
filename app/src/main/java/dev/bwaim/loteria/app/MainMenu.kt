package dev.bwaim.loteria.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import dev.bwaim.loteria.compose.TopAppBarTitle
import dev.bwaim.loteria.compose.design.LoteriaButton
import dev.bwaim.loteria.compose.design.LoteriaTextButton

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
        topBar = { MainMenuAppBar(actioner) }
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoteriaButton(onClick = { actioner(StartDraw) }) {
                LoteriaTextButton(text = stringResource(id = R.string.start_menu))
            }
        }
    }
}

@Composable
private fun MainMenuAppBar(actioner: MainMenuActioner) {
    TopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.app_name)) },
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.statusBars,
            applyBottom = false,
            additionalEnd = 16.dp
        ),
        actions = {
            IconButton(
                onClick = { actioner(OpenSettings) }
            ) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = stringResource(id = R.string.settings)
                )
            }
        }
    )
}
