package com.zonadev.myapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Indicador de navegación que muestra pequeños puntos para representar las páginas del pager.
@Composable
fun NavigationIndicator(pagerState: PagerState) {
    // Se calcula la lista de índices a mostrar (máximo 5 indicadores).
    val visibleIndicators: List<Int> = if (pagerState.pageCount <= 5) {
        (0 until pagerState.pageCount).toList()
    } else {
        val current = pagerState.currentPage
        when {
            current < 2 -> List(5) { it }
            current > pagerState.pageCount - 3 -> List(5) { pagerState.pageCount - 5 + it }
            else -> List(5) { current - 2 + it }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        visibleIndicators.forEach { index ->
            val isSelected = index == pagerState.currentPage
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (isSelected) 10.dp else 8.dp)
                    .background(
                        color = if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )
        }
    }
}
