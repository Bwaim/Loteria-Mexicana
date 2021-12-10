package dev.bwaim.loteria.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.bwaim.loteria.compose.theme.LoteriaTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
public class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets(consumeWindowInsets = false) {
                LoteriaTheme(useDarkColors = false) {
                    val systemUiController = rememberSystemUiController()
                    val useDarkIcons = MaterialTheme.colors.isLight
                    val statusBarColor = MaterialTheme.colors.primaryVariant

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = statusBarColor,
                            darkIcons = useDarkIcons
                        )
                    }

                    AppNavigation()
                }
            }
        }
    }
}
