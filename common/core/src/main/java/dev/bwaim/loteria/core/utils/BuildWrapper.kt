package dev.bwaim.loteria.core.utils

import android.os.Build

public object BuildWrapper {
    public val SDK_INT: Int
        get() = Build.VERSION.SDK_INT

    public val isAtLeastQ: Boolean get() = SDK_INT >= 29
}
