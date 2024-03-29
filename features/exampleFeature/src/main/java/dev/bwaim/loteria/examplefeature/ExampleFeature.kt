/*
 * Copyright 2022 Dev Bwaim team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.bwaim.loteria.examplefeature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.bwaim.loteria.compose.theme.LoteriaTheme

@Composable
public fun ExampleFeature(
    viewModel: ExampleFeatureViewModel,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    ExampleFeature(viewState = state)
}

@Composable
public fun ExampleFeature(
    viewState: ExampleFeatureState,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Greeting(viewState.name)
    }
}

@Composable
private fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    LoteriaTheme {
        Greeting("Android")
    }
}
