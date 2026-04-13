package com.gym.ui.features.historial.formRegistrosPorFecha

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.theme.Cereza
import com.gym.ui.theme.CerezaDeshabilitado
import com.gym.ui.theme.CerezaOscuro
import com.gym.ui.theme.RosaPalo
import com.gym.ui.theme.RosaRojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FormRegistrosPorFecha(
    onIrAtras: () -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    listaEjercicios: List<EjercicioUiState>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val pager = rememberPagerState(
            pageCount = { listaEjercicios.size },
            initialPage = 0,
            initialPageOffsetFraction = 0f
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalPager(state = pager) { indice ->
                val ejercicio = listaEjercicios[indice]
                ElevatedCard(
                    modifier = Modifier
                        .aspectRatio(0.85f)
                        .padding(all = 16.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = RosaPalo
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.18f),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text(
                                text = ejercicio.nombre.uppercase(),
                                fontWeight = FontWeight.Black,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    shadow = Shadow(
                                        color = Color.White.copy(alpha = 0.15f),
                                        offset = Offset(x = 4f, y = 4f),
                                        blurRadius = 3f
                                    )
                                ),
                                color = CerezaOscuro,
                                textAlign = TextAlign.Center,
                                letterSpacing = 1.sp
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.82f)
                                .clip(shape = MaterialTheme.shapes.large)
                                .border(
                                    width = 1.dp,
                                    color = Cereza.copy(alpha = 0.2f),
                                    shape = MaterialTheme.shapes.large
                                )
                                .background(Color.White)
                                .padding(vertical = 12.dp, horizontal = 4.dp)
                        ) {
                            Box(modifier = Modifier.weight(0.1f))
                            Text(
                                modifier = Modifier.weight(0.45f),
                                text = if (ejercicio.serie == 3) "PESO" else "NIVEL",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Cereza,
                                    letterSpacing = 0.5.sp
                                )
                            )
                            Text(
                                modifier = Modifier.weight(0.45f),
                                text = if (ejercicio.serie == 3) "REPS." else "TIEMPO",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Cereza,
                                    letterSpacing = 0.5.sp
                                )
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 8.dp
                            ),
                            thickness = 0.5.dp,
                            color = RosaRojo.copy(alpha = 0.2f)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(count = ejercicio.serie) { serieIndex ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(all = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Box(
                                        modifier = Modifier.weight(0.1f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(29.dp)
                                                .clip(CircleShape)
                                                .background(color = Cereza)
                                                .border(
                                                    width = 2.dp,
                                                    color = Cereza.copy(alpha = 0.3f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "${serieIndex + 1}", color = Color.White,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier.weight(0.45f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .width(100.dp)
                                                .padding(bottom = 5.dp),
                                            value = "",
                                            onValueChange = {},
                                            keyboardOptions = KeyboardOptions.Default.copy(
                                                keyboardType = KeyboardType.Number
                                            ),
                                            maxLines = 1,
                                            label = { Text(text = if (ejercicio.serie == 3) "Peso" else "Nivel") },
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedBorderColor = Cereza,
                                                unfocusedBorderColor = CerezaDeshabilitado,
                                                unfocusedContainerColor = Color.White,
                                                focusedContainerColor = Color.White,
                                                cursorColor = Cereza,
                                                focusedLabelColor = Cereza,
                                                unfocusedLabelColor = Cereza
                                            )
                                        )
                                    }
                                    Box(
                                        modifier = Modifier.weight(0.45f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .width(100.dp)
                                                .padding(bottom = 5.dp),
                                            value = "",
                                            onValueChange = {},
                                            keyboardOptions = KeyboardOptions.Default.copy(
                                                keyboardType = KeyboardType.Number
                                            ),
                                            maxLines = 1,
                                            label = { Text(text = if (ejercicio.serie == 3) "Reps." else "Tiempo") },
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedBorderColor = Cereza,
                                                unfocusedBorderColor = CerezaDeshabilitado,
                                                unfocusedContainerColor = Color.White,
                                                focusedContainerColor = Color.White,
                                                cursorColor = Cereza,
                                                focusedLabelColor = Cereza,
                                                unfocusedLabelColor = Cereza
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            scope.launch { pager.animateScrollToPage(pager.currentPage - 1) }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White,
                            containerColor = Cereza
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(times = pager.pageCount) { iteration ->
                            val color =
                                if (pager.currentPage == iteration) Cereza else CerezaDeshabilitado
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(color)
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            scope.launch { pager.animateScrollToPage(pager.currentPage + 1) }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White,
                            containerColor = Cereza
                        )
                    ) {
                        Icon(
                            modifier = Modifier.padding(start = 2.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier
                            .size(width = 120.dp, height = 50.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Cereza,
                            contentColor = Color.White,
                            disabledContainerColor = CerezaDeshabilitado,
                            disabledContentColor = Color.White.copy(alpha = 0.6f)
                        ),
                        enabled = false
                    ){
                        Text(
                            text = "Guardar",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}