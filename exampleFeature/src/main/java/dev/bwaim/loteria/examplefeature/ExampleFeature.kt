package dev.bwaim.loteria.examplefeature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.bwaim.loteria.compose.theme.LoteriaTheme

@Composable
public fun ExampleFeature(
    viewModel: ExampleFeatureViewModel
) {
    val state by viewModel.viewState.collectAsState()
    ExampleFeature(viewState = state)
}

@Composable
public fun ExampleFeature(
    viewState: ExampleFeatureState
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
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
