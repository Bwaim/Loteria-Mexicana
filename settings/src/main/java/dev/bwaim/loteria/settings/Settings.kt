package dev.bwaim.loteria.settings

import androidx.annotation.VisibleForTesting
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle
import dev.bwaim.loteria.compose.collectAsStateWithLifecycle
import dev.bwaim.loteria.compose.design.preference.model.ListPreferenceValues
import dev.bwaim.loteria.compose.design.preference.model.Preference
import dev.bwaim.loteria.compose.design.preference.ui.ListPreferenceWidget
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme

private typealias SettingsActioner = (SettingsAction) -> Unit

@Composable
public fun Settings(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    Settings(viewState) { action ->
        when (action) {
            NavigateUp -> navigateUp()
            is OnThemeChanged -> {
                viewModel.setTheme(action.value)
            }
        }
    }
}

@Composable
private fun Settings(
    viewState: SettingsState,
    actioner: SettingsActioner
) {
    val appTheme = viewState.appTheme.toPreference()
    val themesPreferences =
        viewState.themes.toListPreferences(stringResource(id = R.string.settings_app_theme_title))
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { SettingsAppBar(actioner) }
    ) {
        ListPreferenceWidget(
            preferences = themesPreferences,
            currentValue = appTheme,
            onValueChanged = { actioner(OnThemeChanged(it.value as Theme)) }
        )
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

@Composable
@VisibleForTesting
public fun Theme.getLabel(): String =
    when (this) {
        Theme.LIGHT -> stringResource(id = R.string.settings_app_theme_light)
        Theme.DARK -> stringResource(id = R.string.settings_app_theme_dark)
        Theme.SYSTEM -> stringResource(id = R.string.settings_app_theme_system)
        Theme.BATTERY_SAVER -> stringResource(id = R.string.settings_app_theme_battery)
    }

@Composable
private fun Theme.toPreference(): Preference<Theme> = Preference(label = this.getLabel(), value = this)

@Composable
private fun List<Theme>.toListPreferences(title: String): ListPreferenceValues<Theme> =
    ListPreferenceValues(
        title = title,
        entries = this
            .filter { it.isAvailable() }
            .associate {
                it.getLabel() to it.toPreference()
            }
    )

private fun Theme.isAvailable(): Boolean =
    when (this) {
        Theme.LIGHT -> true
        Theme.DARK -> true
        Theme.SYSTEM -> BuildWrapper.isAtLeastQ
        Theme.BATTERY_SAVER -> !BuildWrapper.isAtLeastQ
    }
