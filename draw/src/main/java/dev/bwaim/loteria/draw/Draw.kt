package dev.bwaim.loteria.draw

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle

private typealias DrawActioner = (DrawAction) -> Unit

@Composable
public fun Draw(
    // viewModel: ViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    Draw() { action ->
        when (action) {
            NavigateUp -> navigateUp()
        }
    }
}

@Composable
private fun Draw(
    // viewState: SettingsState,
    actioner: DrawActioner
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { SettingsAppBar(actioner) }
    ) {
        Text(text = "DrawScreen")
    }
}

@Composable
private fun SettingsAppBar(actioner: DrawActioner) {
    TopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.draw_title)) },
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
