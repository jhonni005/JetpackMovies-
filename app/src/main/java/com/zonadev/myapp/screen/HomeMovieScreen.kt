package com.zonadev.myapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zonadev.myapp.viewmodel.MoviewViewModel
import kotlinx.coroutines.delay

// Pantalla principal de películas, mostrando un slider de las tendencias y una grilla de películas populares.
@Composable
fun MovieScreen(
    viewModel: MoviewViewModel,
    navController: NavController,
) {

    val movies by viewModel.movies.collectAsState()
    val trendingMovies by viewModel.trendingMovies.collectAsState()
    val error by viewModel.errorMessage.collectAsState()


    // Estado del pager, se basa en el número de películas trending.
    val pagerState = rememberPagerState(initialPage = 0) {
        trendingMovies.size

    }

    LaunchedEffect(key1 = pagerState) {
        while (true) {
            delay(4000L)
            if (!pagerState.isScrollInProgress && pagerState.pageCount > 0) {
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    if (!error.isNullOrEmpty()) {
        Text("Error: $error")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            if (trendingMovies.isNotEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) { // <-- aquí el cambio importante
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text("Tendencias")
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxWidth(),
                            pageSpacing = 4.dp
                        ) { page ->
                            TrendingMovieCard(
                                movie = trendingMovies[page],
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        NavigationIndicator(pagerState = pagerState)
                    }
                }
            }


            // Segundo bloque: Grilla de películas populares.
            items(movies) { movie ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Se pasa el navController a la tarjeta para poder navegar al detalle.
                    MovieCard(movie = movie, navController = navController)
                }
            }
        }//LazyVerticalGrid
    }
}
