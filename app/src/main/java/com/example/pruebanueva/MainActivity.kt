package com.example.pruebanueva

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicacionTwitter()
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AplicacionTwitter() {
    val controladorNav = rememberNavController()
    Scaffold(
        topBar = { BarraSuperiorTwitter() },
        bottomBar = { BarraNavegacionInferiorTwitter(controladorNav) }
    ) {
        NavHost(

            navController = controladorNav,
            startDestination = "inicio",
            Modifier.padding(top = 56.dp, bottom = 64.dp)

        ) {
            composable("inicio") { PantallaInicio() }
            composable("perfil") { PantallaPerfil() }
            composable("nuevoTweet") { PantallaNuevoTweet() }
        }
    }
}

@Composable
fun BarraSuperiorTwitter() {
    TopAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.wallstreet),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.icono),
            contentDescription = "Logo de Twitter",
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Buscar",
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun BarraNavegacionInferiorTwitter(controladorNav: NavController) {
    val rutaActual = controladorNav.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = rutaActual == "inicio",
            onClick = {
                controladorNav.navigate("inicio") {
                    popUpTo(controladorNav.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Add, contentDescription = "Tweet") },
            label = { Text("Tweet") },
            selected = rutaActual == "nuevoTweet",
            onClick = {
                controladorNav.navigate("nuevoTweet") {
                    popUpTo(controladorNav.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = rutaActual == "perfil",
            onClick = {
                controladorNav.navigate("perfil") {
                    popUpTo(controladorNav.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

@Composable
fun PantallaInicio() {

    val imgs = listOf(
        R.drawable.cat,
        R.drawable.dog,
        R.drawable.elephant,
        R.drawable.giraffe,
        R.drawable.lion,
        R.drawable.penguin,
        R.drawable.tiger,
        R.drawable.wallstreet,
        R.drawable.zebra,
        R.drawable.nosfe
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(10) { indice ->
            ElementoTweet(
                nombreUsuario = "Usuario $indice",
                textoTweet = "Este es un tweet de ejemplo $indice",
                imagenId = imgs[indice % imgs.size]
            )
        }
    }
}

@Composable
fun  ElementoTweet(nombreUsuario: String, textoTweet: String, imagenId : Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(nombreUsuario, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(textoTweet, fontSize = 16.sp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("❤️ 10", fontSize = 14.sp)
                Text("🔁 5", fontSize = 14.sp)
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }
}

@Composable
fun PantallaPerfil() {
    val imgs = listOf(
        R.drawable.cat,
        R.drawable.dog,
        R.drawable.elephant,
        R.drawable.giraffe,
        R.drawable.lion,

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.wallstreet),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Usuario", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("@usuario123", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Bio del usuario...", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tweets", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(5) { indice ->
                ElementoTweet(
                    nombreUsuario = "Usuario $indice",
                    textoTweet = "Tweet del usuario en perfil $indice",
                    imagenId = imgs[indice % imgs.size]
                )
            }
        }
    }
}

@Composable
fun PantallaNuevoTweet() {

    val imgs = listOf(
        R.drawable.cat,
        R.drawable.dog,
        R.drawable.elephant,
        R.drawable.giraffe,
        R.drawable.lion,
        R.drawable.penguin,
        R.drawable.tiger,
        R.drawable.wallstreet,
        R.drawable.nosfe

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(id = R.drawable.wallstreet),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text("EjemploUsuario", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("@ejemplo_usuario", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Este es un tweet de ejemplo que muestra cómo se vería el detalle de un tweet al hacer clic.", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("❤️ 20", fontSize = 14.sp)
            Text("🔁 10", fontSize = 14.sp)
            Text("💬 5", fontSize = 14.sp)
        }

        Divider(color = Color.Gray, thickness = 0.5.dp)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Comentarios", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))



            Spacer(modifier = Modifier.width(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(10) { indice ->

                    ElementoDetalleComentario(
                        textoComentario = "Usuario Comentario $indice",
                        descripcion = "Este es un comentario del usuario $indice",
                        imgs = imgs[indice % imgs.size]
                    )
                 }
}}}


@Composable
fun ElementoDetalleComentario (textoComentario: String, descripcion: String , imgs : Int){



    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = imgs),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = textoComentario, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(
                text = descripcion,
                fontSize = 14.sp
            )
        }
}}




