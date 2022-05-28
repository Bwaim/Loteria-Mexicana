package dev.bwaim.loteria.compose

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public fun LazyListState.elevation(): Dp {
    return remember(this) {
        derivedStateOf {
            if (firstVisibleItemIndex == 0) {
                minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
            } else {
                AppBarDefaults.TopAppBarElevation
            }
        }
    }.value
}

@Composable
public fun LazyListState.isScrolled(): Boolean {
    return remember(this) {
        derivedStateOf { firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0 }
    }.value
}

public object ListDefaults {
    public val VerticalPadding: Dp = 8.dp
}
