package com.zonadev.myapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.zonadev.myapp.data.MovieRepositoryImpl
import com.zonadev.myapp.network.RetrofitInstance
import com.zonadev.myapp.screen.navigation.AppNavigation
import com.zonadev.myapp.ui.theme.MoviesAppTheme
import com.zonadev.myapp.viewmodel.MoviesViewModelFactory
import com.zonadev.myapp.viewmodel.MoviewViewModel

// Esta es la actividad principal. Se separa la configuración del tema, la navegación y el viewModel.
class MainActivity : ComponentActivity() {
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
    val viewModelProvider = ViewModelProvider(context as ComponentActivity,factory)
    val viewModel = viewModelProvider.get(MoviewViewModel::class.java)

    AppNavigation(viewModel)
}




