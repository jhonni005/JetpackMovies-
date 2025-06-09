package com.zonadev.myapp.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zonadev.myapp.utils.Constants.IMAGE_BASE_URL
import com.zonadev.myapp.viewmodel.MoviewViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.zonadev.myapp.R
import kotlinx.coroutines.delay

@Composable
fun MoviesRecommended(
    id: Int,
    viewModel: MoviewViewModel,
    onMovieClick: (Int) -> Unit
) {

    // Esto debería estar en un LaunchedEffect para no dispararse cada recomposición
    LaunchedEffect(id) {
        viewModel.fetchRecommendedMovies(id)
    }

    val recommendedMovies by viewModel.recommendedMovies.collectAsState()


    
    Text("Relacionados", modifier = Modifier.padding(6.dp), color = Color.White)
    if (recommendedMovies.isNotEmpty()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp) // Un poquito más alto para margen
                .padding(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp), // Mejor separación al inicio y fin
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre items
        ) {

            items(recommendedMovies) { movie ->

                Column(
                    modifier = Modifier
                        .width(140.dp) // Menor ancho para que entren más en pantalla
                        .height(200.dp)
                        .clickable {
                            onMovieClick(movie.id)
                        },
                ) {

                    val imageUrl = IMAGE_BASE_URL + movie.poster_path
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Poster de la película",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 3f) // Relación típica de poster 2:3
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.title,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}