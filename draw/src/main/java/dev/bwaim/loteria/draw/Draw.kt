package dev.bwaim.loteria.draw

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle
import dev.bwaim.loteria.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import java.io.File
import java.util.concurrent.TimeUnit

private typealias DrawActioner = (DrawAction) -> Unit

@Composable
public fun Draw(
    viewModel: DrawViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val viewState2 = viewModel.viewState.collectAsStateWithLifecycle()
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
    ) {
//        Text(text = "DrawScreen")
        CoilImage(viewState.card)
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
