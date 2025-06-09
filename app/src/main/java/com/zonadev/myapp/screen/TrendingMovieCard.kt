package com.zonadev.myapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zonadev.myapp.model.Movie
import com.zonadev.myapp.utils.Constants

@Composable
fun TrendingMovieCard(movie: Movie,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Box {
            movie.backdrop_path?.let { backdropPath ->
                Image(
                    painter = rememberAsyncImagePainter(Constants.BASE_BACKDROP_URL + backdropPath),
                    contentDescription = "Imagen de la película en tendencia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
