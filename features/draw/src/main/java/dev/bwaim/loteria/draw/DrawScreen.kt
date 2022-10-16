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

@file:OptIn(ExperimentalLifecycleComposeApi::class)

package dev.bwaim.loteria.draw

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle

@Composable
public fun DrawRoute(
    viewModel: DrawViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    Draw(
        viewState = viewState,
        onBackClick = onBackClick
    )
}

@Composable
private fun Draw(
    viewState: DrawState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = { SettingsAppBar(onBackClick) }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
        ) {

            viewState.cards.forEachIndexed { index, card ->
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "$index - ${stringResource(id = card.nameId)}"
                )
            }
        }

//        CoilImage(viewState.card)
    }
}

@Composable
private fun SettingsAppBar(
    onBackClick: () -> Unit
) {
    SmallTopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.draw_title)) },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal + WindowInsetsSides.Top
            )
        ),
        navigationIcon = {
            BackButton { onBackClick() }
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
