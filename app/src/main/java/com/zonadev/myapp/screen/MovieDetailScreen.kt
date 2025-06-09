package com.zonadev.myapp.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.zonadev.myapp.viewmodel.MoviewViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailScreen(
    id: Int,
    viewModel: MoviewViewModel,
) {
    val movieDetail by viewModel.movieDetail.collectAsState()
    var currentId by remember { mutableIntStateOf(id) }
    val listState = rememberLazyListState()

    // Ejecuta una vez cuando se entra a la pantalla (o cambia el id)
    LaunchedEffect(currentId) {
        viewModel.fetchMovieDetail(currentId)
        viewModel.fetchMovieCast(currentId)
        listState.animateScrollToItem(0)
    }
    DisposableEffect(Unit) {
        onDispose {
            // Al salir, limpia
            viewModel.clearMovieCast()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // 👈 Fondo negro SIEMPRE visible
    ) {

        if (movieDetail != null) {
            val movie = movieDetail!!
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Black)

            ) {
                item {
                    // Póster de la película
                    movie.poster_path?.let { posterPath ->
                        Image(
                            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w342$posterPath"),
                            contentDescription = movie.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
                //Spacer(modifier = Modifier.height(16.dp))

                item {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                // Spacer(modifier = Modifier.height(8.dp))

                item {
                    Text(
                        text = "Sinopsis: ${movie.overview}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                //   Spacer(modifier = Modifier.height(8.dp))

                item {
                    Text(
                        text = "Release Date: ${movie.release_date}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Spacer(modifier = Modifier.height(8.dp))


                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Rating: ${movie.vote_average}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // Spacer(modifier = Modifier.height(8.dp))

                item {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        movie.genres.forEach { genre ->
                            Text(
                                text = genre.name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                item {
                    MoviesRecommended(
                        id = currentId,
                        viewModel = viewModel,
                        onMovieClick = { clickedId ->
                            currentId = clickedId
                        }
                    )
                }
                item {
                    MovieCast(viewModel)
                }

            }
        } else {
            // Estado de carga
            AnimatedVisibility(visible = movieDetail == null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
    }
}




