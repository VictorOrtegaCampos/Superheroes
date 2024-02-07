package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.model.Hero
import com.example.superheroes.ui.theme.SuperheroesTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import com.example.superheroes.model.SuperheroeRepository


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroeApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroeIcon(
    @DrawableRes superheroeIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(superheroeIcon),
        contentDescription = null
    )
}

@Composable
fun SuperheroeInformation(
    @StringRes superheroeName: Int,
    @StringRes superheroeText: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(superheroeName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(superheroeText),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SuperheroeItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            .padding(bottom = dimensionResource(id = R.dimen.padding_small))

    ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SuperheroeInformation(hero.nameRes, hero.descriptionRes, modifier = Modifier.weight(1f))
                SuperheroeIcon(hero.imageRes)
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroeTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroeApp() {
    Scaffold (topBar = {
        SuperheroeTopAppBar()
    }){ it ->
        LazyColumn(contentPadding = it) {
            items(SuperheroeRepository.heroes) {
                SuperheroeItem(
                    hero = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperheroesTheme (darkTheme = false){
        SuperheroeApp()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingDarkPreview() {
    SuperheroesTheme (darkTheme = true){
        SuperheroeApp()
    }
}