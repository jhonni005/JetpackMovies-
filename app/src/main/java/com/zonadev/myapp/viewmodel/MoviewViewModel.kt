package com.zonadev.myapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zonadev.myapp.data.MovieRepository
import com.zonadev.myapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.zonadev.myapp.model.CastMember
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//@HiltViewModel
//val viewModel: MoviewViewModelby viewModels()
class MoviewViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _trendingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val trendingMovies: StateFlow<List<Movie>> = _trendingMovies

    private val _recommendedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val recommendedMovies: StateFlow<List<Movie>> = _recommendedMovies

    private val _movieDetail = MutableStateFlow<Movie?>(null)
    val movieDetail: StateFlow<Movie?> = _movieDetail

    private val _movieCast = MutableStateFlow<List<CastMember>>(emptyList())
    val movieCast:StateFlow<List<CastMember>> = _movieCast

    private val _filteredMovies = MutableStateFlow<List<Movie>>(emptyList())
    val filteredMovies: StateFlow<List<Movie>> = _filteredMovies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _errorMessage = MutableStateFlow<String>("null")
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchPopularMovies()
        fetchTrendingMovies()
    }

    fun fetchMovieCast(id:Int){
        viewModelScope.launch {
            val result = runCatching { repository.getCastMovie(id) }
            result.onSuccess {
                _movieCast.value = it
                Log.d("MovieCast", "Detalles obtenidos: $it")
            }
            result.onFailure { error ->
                Log.d("MovieCast","Error: ${error.message}")
            }
        }
    }

    fun fetchRecommendedMovies(id: Int){
        viewModelScope.launch {
            val result = runCatching { repository.getRecommendedMovies(id) }
            result.onSuccess {
                _recommendedMovies.value = it
            }
            result.onFailure { error->
                Log.d("RecommendedMovies","Error: ${error.message}")
            }
        }
    }
    fun clearMovieCast() {
        _movieCast.value = emptyList()
    }

    fun fetchMovieDetail(id: Int) {
        viewModelScope.launch {
            try {
                val movie = repository.getMovieDetailById(id)
                _movieDetail.value = movie // Actualiza el StateFlow
            } catch (exception: Exception) {
                // Manejo de errores
                Log.e("MovieViewModel", "Error fetching movie details: ${exception.message}")
            }
        }
    }

    fun fetchTrendingMovies() {
        viewModelScope.launch {
            val result = runCatching { repository.getTrendingMovies() }
            result.onSuccess {
                _trendingMovies.value = it
                Log.d("TrendingMovies", "Películas: $it")
            }
        }
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _errorMessage.value = ""
            val result = runCatching { repository.getPopularMovies() }
            result.onSuccess {
                _movies.value = it
                Log.d("MovieResponse", "Películas: $it") // Aquí sí verás la lista
            }.onFailure { _errorMessage.value = it.message.toString() }
        }
    }

    fun searchMovies(query: String){
        viewModelScope.launch {
            _isLoading.value = true
            val result = runCatching { repository.searchMovies(query) }
            result.onSuccess {
                _isLoading.value = false
                _filteredMovies.value = it
                Log.d("searchMovies" ,"$it")
            }.onFailure { _errorMessage.value = it.message.toString() }
        }
    }
}

