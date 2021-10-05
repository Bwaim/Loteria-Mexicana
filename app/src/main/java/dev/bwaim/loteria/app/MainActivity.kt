package dev.bwaim.loteria.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import dev.bwaim.loteria.navigation.NavigationManager
import javax.inject.Inject

@AndroidEntryPoint
public class MainActivity : ComponentActivity() {
    @Inject
    public lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets(consumeWindowInsets = false) {
                LoteriaTheme(useDarkColors = false) {
                    NavigationGraph(navigationManager = navigationManager)
                }
            }
        }
    }
}
