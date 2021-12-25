package dev.bwaim.loteria.settings

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle
import dev.bwaim.loteria.compose.design.preference.ui.PreviewListPreference

private typealias SettingsActioner = (SettingsAction) -> Unit

@Composable
public fun Settings(
    navigateUp: () -> Unit
) {
    Settings { action ->
        when (action) {
            NavigateUp -> navigateUp()
        }
    }
}

@Composable
private fun Settings(
    actioner: SettingsActioner
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { SettingsAppBar(actioner) }
    ) {
        PreviewListPreference()
    }
}

@Composable
private fun SettingsAppBar(actioner: SettingsActioner) {
    TopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.settings_title)) },
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.statusBars,
            applyBottom = false,
            additionalEnd = 16.dp
        ),
        navigationIcon = {
            BackButton {
                actioner(NavigateUp)
            }
        }
    )
}
