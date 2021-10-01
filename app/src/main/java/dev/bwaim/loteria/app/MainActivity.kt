package dev.bwaim.loteria.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.insets.ProvideWindowInsets
import dev.bwaim.loteria.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import dev.bwaim.loteria.compose.theme.LoteriaTheme

@AndroidEntryPoint
public class MainActivity : ComponentActivity() {
    @Inject
    public lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets(consumeWindowInsets = false) {
                LoteriaTheme {
                    NavigationGraph(navigationManager = navigationManager)
                }
            }
        }
    }
}
