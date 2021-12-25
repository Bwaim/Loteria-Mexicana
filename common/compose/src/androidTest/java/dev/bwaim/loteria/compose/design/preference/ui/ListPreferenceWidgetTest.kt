package dev.bwaim.loteria.compose.design.preference.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isSelectable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import dev.bwaim.loteria.compose.design.preference.model.ListPreferenceValues
import dev.bwaim.loteria.compose.design.preference.model.Preference
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import org.junit.Rule
import org.junit.Test

internal class ListPreferenceWidgetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val label1 = "label1"
    private val label2 = "label2"
    private val title = "titlePref"
    private val pref1 = Preference(label = label1, value = label1)
    private val pref2 = Preference(label = label2, value = label2)
    private val preferences =
        ListPreferenceValues(title = title, entries = mapOf(label1 to pref1, label2 to pref2))

    @Test
    fun shouldChange() {

        testContent {
            var currentPref by remember {
                mutableStateOf(pref1)
            }
            ListPreferenceWidget(
                preferences = preferences,
                currentValue = currentPref,
                onValueChanged = { currentPref = Preference(label = it.label, value = it.value) as Preference<String> }
            )
        }

        composeTestRule.onNode(hasText(title)).assertExists()
        composeTestRule.onNode(hasText(label1)).assertExists()

        composeTestRule.onNode(hasText(title)).performClick()

        composeTestRule.onNode(hasText(label1) and isSelectable()).assertIsSelected()
        composeTestRule.onNode(hasText(label2)).assertIsNotSelected()

        composeTestRule.onNode(hasText(label2)).performClick()

        composeTestRule.onNode(hasText(label1)).assertDoesNotExist()
        composeTestRule.onNode(hasText(label2)).assertExists()
    }

    private fun testContent(call: @Composable () -> Unit) {
        composeTestRule.setContent {
            LoteriaTheme {
                call()
            }
        }
    }
}
