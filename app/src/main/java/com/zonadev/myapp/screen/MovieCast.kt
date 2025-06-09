package com.zonadev.myapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.zonadev.myapp.utils.Constants
import com.zonadev.myapp.viewmodel.MoviewViewModel


@Composable
fun MovieCast(viewModel: MoviewViewModel) {
    val movieCast by viewModel.movieCast.collectAsState()

    if (movieCast.size >= 6) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Reparto",
                modifier = Modifier.padding(6.dp),
                color = Color.White
            )

            // Primera fila
            Row(modifier = Modifier.fillMaxWidth()) {
                for (i in 0 until 3) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .background(Color.Black)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(Constants.IMAGE_BASE_URL + movieCast[i].profile_path),
                            contentDescription = "Poster de la película",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = movieCast[i].name,
                            color = Color.White,
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .background(Color(0xAA000000)) // Fondo negro semi-transparente
                                .fillMaxWidth()
                                .padding(1.dp)
                        )
                    }
                }
            }

            // Segunda fila
            Row(modifier = Modifier.fillMaxWidth()) {
                for (i in 3 until 6) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .background(Color.Black)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(Constants.IMAGE_BASE_URL + movieCast[i].profile_path),
                            contentDescription = "Poster de la película",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = movieCast[i].name,
                            color = Color.White,
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .background(Color(0xAA000000))
                                .fillMaxWidth()
                                .padding(1.dp)
                        )
                    }
                }
            }
        }
    } else {
        // Mostrar un mensaje si no hay suficientes elementos
        Text(text = "No hay suficientes actores", color = Color.Red)
    }
}
      /*  Column {
            Text("Reparto", color = Color.White)
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // 3 columnas
                modifier = Modifier.fillMaxWidth()
            ) {
                items(movieCast) {   // Mostrar solo 6 actores
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = movieCast[0].name, color = Color.White)
                    }
                }
            }
        }*/


        /*  Column {
              Text("Reparto", modifier = Modifier.padding(6.dp), color = Color.White)
              LazyRow(
                  modifier = Modifier
                      .height(210.dp)
                      .fillMaxWidth()
                      .padding(2.dp),
              ) {
                  items(movieCast.chunked()) { cast ->
                      Row (
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(6.dp)
                              .background(Color.Magenta)
                      ){
                          Column(
                              modifier = Modifier
                                  .height(210.dp)
                                  .width(180.dp)
                          ) {
                              Image(
                                  painter = rememberAsyncImagePainter(Constants.IMAGE_BASE_URL + cast.profile_path),
                                  contentDescription = "Poster de la película",
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(6.dp)
                                      .height(200.dp),
                                  contentScale = ContentScale.Crop
                              )
                              Text(cast.name)
                          }
                      }
                  }
              }*/

