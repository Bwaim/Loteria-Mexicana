package dev.bwaim.loteria.compose.design.preference.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.bwaim.loteria.compose.design.preference.model.ListPreferenceValues
import dev.bwaim.loteria.compose.design.preference.model.Preference
import dev.bwaim.loteria.compose.theme.LoteriaTheme

internal sealed interface ListPreferenceAction
internal data class OnValueChanged<T>(val value: Preference<T>) : ListPreferenceAction

private typealias ListPreferenceActioner = (ListPreferenceAction) -> Unit

@Composable
public fun <T> ListPreferenceWidget(
    modifier: Modifier = Modifier,
    preferences: ListPreferenceValues<T>,
    currentValue: Preference<T>,
    onValueChanged: (value: Preference<out Any?>) -> Unit
) {
    ListPreferenceUi(
        modifier = modifier,
        preferences = preferences,
        currentValue = currentValue
    ) { action ->
        when (action) {
            is OnValueChanged<*> -> {
                onValueChanged(action.value)
            }
        }
    }
}

@Composable
private fun <T> ListPreferenceUi(
    modifier: Modifier = Modifier,
    preferences: ListPreferenceValues<T>,
    currentValue: Preference<T>,
    actioner: ListPreferenceActioner
) {
    var isDialogShown by remember {
        mutableStateOf(false)
    }
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clickable { isDialogShown = true }
    ) {
        Text(
            text = preferences.title,
            style = LoteriaTheme.typography.bodyLink
        )
        Text(
            text = currentValue.label,
            style = LoteriaTheme.typography.mention
        )
    }

    if (isDialogShown) {
        ListPreferenceDialog(
            preferences = preferences,
            currentValue = currentValue,
            onDismiss = { isDialogShown = false },
            onSelected = { preference ->
                actioner(OnValueChanged(preference))
                isDialogShown = false
            }
        )
    }
}

@Composable
private fun <T> ListPreferenceDialog(
    preferences: ListPreferenceValues<T>,
    currentValue: Preference<T>,
    onDismiss: () -> Unit,
    onSelected: (Preference<T>) -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = preferences.title,
                style = LoteriaTheme.typography.bodyLink,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                preferences.entries.forEach { preference ->
                    val isSelected = preference.key == currentValue.label
                    val onSelectedAction = {
                        if (!isSelected) {
                            onSelected(preference.value)
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = { onSelectedAction() }
                            )
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { onSelectedAction() }
                        )
                        Text(
                            text = preference.value.label,
                            style = LoteriaTheme.typography.mention,
                            modifier = Modifier.align(CenterVertically)
                        )
                    }
                }
            }
        },
        buttons = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .align(CenterEnd)
                ) {
                    Text(
                        text = stringResource(android.R.string.cancel),
                        style = LoteriaTheme.typography.mentionLink
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewListPreference() {
    val currentValue = Preference(label = "pref2", value = "pref2Value")
    val preferences = mapOf(
        "pref1" to Preference(label = "pref1", value = "pref1Value"),
        "pref2" to currentValue,
        "pref3" to Preference(label = "pref3", value = "pref3Value")
    )
    val listPreference = ListPreferenceValues(
        title = "MyListPref",
        entries = preferences
    )
    LoteriaTheme {
        ListPreferenceWidget(
            preferences = listPreference,
            currentValue = currentValue,
            onValueChanged = { }
        )
    }
}

@Preview(group = "active")
@Composable
private fun PreviewAlertListPref() {
    val currentValue = Preference(label = "pref2", value = "pref2Value")
    val preferences = mapOf(
        "pref1" to Preference(label = "pref1", value = "pref1Value"),
        "pref2" to currentValue,
        "pref3" to Preference(label = "pref3", value = "pref3Value")
    )
    val listPreference = ListPreferenceValues(
        title = "MyListPref",
        entries = preferences
    )
    LoteriaTheme {
        ListPreferenceDialog(
            preferences = listPreference,
            currentValue = currentValue,
            onDismiss = { },
            onSelected = { }
        )
    }
}
