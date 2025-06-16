package com.zonadev.myapp.screen.navigation

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zonadev.myapp.viewmodel.MoviewViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: MoviewViewModel) {

    var text by remember { mutableStateOf("") }
    val movieList by viewModel.filteredMovies.collectAsState()

    val movieCategory = listOf(
        "Action",
        "Drama",
        "Comedy",
        "Horror",
        "Romance",
        "Science Fiction",
        "Thriller",
        "Adventure",
        "Fantasy",
        "Mystery",
        "Animation",
        "Documentary",
        "Biography",
        "Musical",
        "Western",
        "Crime"
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .safeDrawingPadding()
    ) {
        item {
            Text(
                text = "Buscar peliculas",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }

        item {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.searchMovies(text)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search, contentDescription = "search"
                        )
                    }
                })
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Text(
                text = "Géneros",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(movieList) { movie ->
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                movie.poster_path?.let { posterPath ->
                    Image(
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w342$posterPath"),
                        contentDescription = movie.title,
                        modifier = Modifier
                            .height(100.dp)
                            .width(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
                Column() {
                    Text(movie.title)
                    Text(movie.release_date)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = movie.vote_average.toString())
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                   // Text(movie.overview)
                }
            }
        }


        /*   LazyVerticalGrid(
               columns = GridCells.Fixed(2),
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.spacedBy(8.dp),
               horizontalArrangement = Arrangement.spacedBy(8.dp),
               contentPadding = PaddingValues(bottom = 32.dp)
           ) {

               items(movieCategory) { category ->
                   Card(
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(50.dp)
                           .clickable {},
                       colors = cardColors(containerColor = Color.DarkGray),
                       elevation = CardDefaults.cardElevation(4.dp)
                   ) {
                       Box(
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(8.dp),
                           contentAlignment = Alignment.Center
                       ) {
                           Text(text = category, color = Color.White)
                       }
                   }
               }
           }*/
    }
}


