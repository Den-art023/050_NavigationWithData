@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.esjumbo.ui.theme.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.esjumbo.R
import com.example.esjumbo.data.SumberData.flavors

enum class PengelolaHalaman {
    Home,
    Form,
    Rasa,
    Summary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsJumboAppbar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = { navigasiUp }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            R.string.back_button
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun EsJumboApp(
    ViewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = { EsJumboAppbar(bisaNavigasiBack = false, navigasiUp = { /*TODO*/ }) }
    ) { innerPadding ->
        val uiState by ViewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PengelolaHalaman.Home.name) {
                HalamanHome(
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Form.name) }
                )
            }

            composable(route = PengelolaHalaman.Form.name) {
                HalamanSatu(onNextButtonClicked = {
                    ViewModel.setPelanggan(it)
                    navController.navigate(PengelolaHalaman.Rasa.name)
                }) {

                }
            }

            composable(route = PengelolaHalaman.Rasa.name) {
                val context = LocalContext.current
                HalamanDua(
                    pilihanRasa = flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = { ViewModel.setRasa(it) },
                    onConfirmButtonClicked = { ViewModel.setJumlah(it) },
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Summary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToHome(
                            ViewModel,
                            navController
                        )
                    }
                )
            }

            composable(route = PengelolaHalaman.Summary.name) {
                HalamanTiga(
                    orderAndFormUIState = uiState,
                    onCancelButtonClicked = { cancelOrderAndNavigateToRasa(navController) }
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToRasa(navController: NavHostController) {
    navController.popBackStack(PengelolaHalaman.Rasa.name, inclusive = false)
}

private fun cancelOrderAndNavigateToHome(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
}

@Preview
@Composable
fun PreviewPengelola(){
    EsJumboApp()
}