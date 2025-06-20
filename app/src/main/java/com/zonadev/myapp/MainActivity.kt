package com.zonadev.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zonadev.myapp.data.MovieRepositoryImpl
import com.zonadev.myapp.data.entity.MovieEntity
import com.zonadev.myapp.model.Movie
import com.zonadev.myapp.network.RetrofitInstance
import com.zonadev.myapp.screen.navigation.AppNavigation
import com.zonadev.myapp.ui.theme.MoviesAppTheme
import com.zonadev.myapp.viewmodel.MoviesViewModelFactory
import com.zonadev.myapp.viewmodel.MoviewViewModel
import kotlinx.coroutines.launch

// Esta es la actividad principal. Se separa la configuración del tema, la navegación y el viewModel.
class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Función propia para habilitar el modo edge-to-edge
        setContent {
            MoviesAppTheme {
                // Usamos Surface para aplicar el color de fondo del theme.
                Surface(
                    //    modifier = Modifier.fillMaxSize(),
                    //  color = MaterialTheme.colorScheme.background
                ) {

                    val context = LocalContext.current
                    val app = context.applicationContext as MovieApp


                   lifecycleScope.launch {
                        app.room.movieDao().insertMovie(MovieEntity(id = 10, "Spiderman", "vacio"))
                       app.room.movieDao().insertMovie(MovieEntity(id = 20, "Robin hood", "vacio"))
                       app.room.movieDao().insertMovie(MovieEntity(id = 30, "El hombre de Hielo", "vacio"))
                        app.room.movieDao().getAllMovies().collect { movies ->
                            movies.forEach { movie ->
                                Log.d("moviedata", "pelicula recuperada: ${movie.title } con id: ${movie.id}")
                            }

                        }
                    }

                    MoviesNavigation()
                }
            }
        }
    }
}


// Composable que se encarga de crear el viewModel, definir el repositorio y configurar la navegación de la app.
@Composable
fun MoviesNavigation() {
    // Instancias necesarias para el Repository y el ViewModel.
    val context = LocalContext.current
    val apiService = RetrofitInstance.api
    val movieRepository = MovieRepositoryImpl(apiService)
    val factory = MoviesViewModelFactory(movieRepository)
    val viewModelProvider = ViewModelProvider(context as ComponentActivity, factory)
    val viewModel = viewModelProvider.get(MoviewViewModel::class.java)

    AppNavigation(viewModel)
}




