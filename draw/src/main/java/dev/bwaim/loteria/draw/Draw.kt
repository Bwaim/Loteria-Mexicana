package dev.bwaim.loteria.draw

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle

private typealias DrawActioner = (DrawAction) -> Unit

@Composable
public fun Draw(
    viewModel: DrawViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    val viewState2 = viewModel.viewState.collectAsState()
    val value1: DrawState = viewState
    val value2: DrawState = viewState2.value
    Draw(viewState) { action ->
        when (action) {
            NavigateUp -> navigateUp()
        }
    }
}

@Composable
private fun Draw(
    viewState: DrawState,
    actioner: DrawActioner
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { SettingsAppBar(actioner) }
    ) { contentPadding ->
        Text(
            modifier = Modifier.padding(contentPadding),
            text = "DrawScreen"
        )
        CoilImage(viewState.card)
    }
}

@Composable
private fun SettingsAppBar(actioner: DrawActioner) {
    TopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.draw_title)) },
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)),
        navigationIcon = {
            BackButton {
                actioner(NavigateUp)
            }
        }
    )
}

@Composable
private fun CoilImage(drawableRes: Int) {
//    val cardArray = arrayOf(R.drawable.el_gallo,
//                            R.drawable.el_diablito_2,
//                            R.drawable.la_dama_3,
//                            R.drawable.el_catrin_4,
//                            R.drawable.el_paraguas_5,
//                            R.drawable.la_escalera_7)
//    for(card in cardArray) {
//        Image(painter = painterResource(id = card), contentDescription = "carta 1")
//        TimeUnit.SECONDS.sleep(1L)
//
//    }
    val painter = rememberAsyncImagePainter(drawableRes)
    Image(painter = painter, contentDescription = null)
}
