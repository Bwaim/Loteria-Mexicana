package dev.bwaim.loteria.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.bwaim.loteria.compose.extensions.shouldUseDarkColors
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import dev.bwaim.loteria.theme.ThemeActivityDelegate
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
public class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var themeActivityDelegate: ThemeActivityDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            LoteriaTheme(useDarkColors = themeActivityDelegate.shouldUseDarkColors()) {
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
