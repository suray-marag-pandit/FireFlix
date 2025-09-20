package com.example.FireFlix.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.FireFlix.R
import com.example.FireFlix.designs.BottomNavigatorDesign
import com.example.FireFlix.designs.LazyRowMoviesDesign
import com.example.FireFlix.viewmodels.TMDBViewModel

@Composable
fun MovieTabScreen(
    navController: NavController,
    TMDBViewModel: TMDBViewModel,
    goToHomeScreen: () -> Unit,
    goToMovieTabScreen: () -> Unit,
    goToMovieDescScreen: (id: Int) -> Unit,
    goToSeriesTabScreen:()->Unit,
    goToSearchScreen:()->Unit,
    goToProfileScreen:()->Unit,
    goToSettingTabScreen:()->Unit
) {
//    viewModel.getPopularMovies() // dont have to call popular movies as it is colled in home screen already
    var timeWindow by remember{
        mutableStateOf("week")
    }
    if(TMDBViewModel.getPopularMovies.collectAsState().value==null) {
        TMDBViewModel.getPopularMovies()
    }

    if(TMDBViewModel.getTopRatedMovies.collectAsState().value==null) {
        TMDBViewModel.getTopRatedMovies()
    }
    if(TMDBViewModel.getNowPlayingMovies.collectAsState().value==null) {
        TMDBViewModel.getNowPlayingMovies()
    }
    if(TMDBViewModel.getUpcomingMovies.collectAsState().value==null) {
        TMDBViewModel.getUpcomingMovies()
    }
    if(TMDBViewModel.getTrendingMovies.collectAsState().value==null){
        TMDBViewModel.getTrendingMovies(timeWindow)
    }
    val popularMovies = TMDBViewModel.getPopularMovies.collectAsState().value
    val topRatedMovies = TMDBViewModel.getTopRatedMovies.collectAsState().value
    val nowPlayingMovies = TMDBViewModel.getNowPlayingMovies.collectAsState().value
    val upcomingMovies = TMDBViewModel.getUpcomingMovies.collectAsState().value
    val trendingMovies = TMDBViewModel.getTrendingMovies.collectAsState().value

    Box( modifier = Modifier
        .fillMaxSize()
        .background(Color.Black) ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1f1f1f))
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "notification",
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(35.dp)
                        .background(Color(0xFF121212), shape = CircleShape)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .clickable(onClick = {
                                goToProfileScreen()
                        })
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.appicon),
                        contentDescription = "appicon",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "FireFlix",
                        fontSize = 13.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.interbold))
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.searchicon),
                    contentDescription = "search",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(35.dp)
                        .background(Color(0xFF121212), shape = CircleShape)
                        .clip(CircleShape)
                        .padding(8.dp)
                        .clickable(
                            onClick = {
                                goToSearchScreen()
                            }
                        )
                )
            }
                // movies list

            if (nowPlayingMovies == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyRowMoviesDesign(nowPlayingMovies, "Now Playing", goToMovieDescScreen)
            }
            if (topRatedMovies == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyRowMoviesDesign(topRatedMovies, "Top Rated Movies", goToMovieDescScreen)
            }

            if (popularMovies == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyRowMoviesDesign(popularMovies, "Popular Movies", goToMovieDescScreen)
            }

            if (upcomingMovies == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyRowMoviesDesign(upcomingMovies, "Upcoming Movies", goToMovieDescScreen)
            }

            if (trendingMovies == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyRowMoviesDesign(trendingMovies, "Trending Movies", goToMovieDescScreen)
                Spacer(modifier = Modifier.padding(bottom = 100.dp))
            }
        }
    }
    BottomNavigatorDesign(navController,goToHomeScreen, goToMovieTabScreen, goToSeriesTabScreen,goToSettingTabScreen)
}