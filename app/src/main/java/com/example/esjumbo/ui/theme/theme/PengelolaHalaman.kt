package com.example.esjumbo.ui.theme.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.esjumbo.R

enum class PengelolaHalaman {
    Home,
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
    TopAppBar(title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = { navigasiUp }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun EsJumboApp(
    
){}