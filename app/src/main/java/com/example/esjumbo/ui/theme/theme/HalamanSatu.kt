package com.example.esjumbo.ui.theme.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esjumbo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanSatu(
    onNextButtonClicked: (MutableList<String>) -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    var namaTxt by remember {
        mutableStateOf("")
    }
    var tlpText by remember {
        mutableStateOf("")
    }
    var almtTxt by remember {
        mutableStateOf("")
    }

    var lisData: MutableList<String> = mutableListOf(namaTxt, tlpText, almtTxt)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Data Pelanggan",
            modifier = Modifier.padding(20.dp),
            fontSize = 20.sp)

        OutlinedTextField(
            value = namaTxt,
            onValueChange = { namaTxt = it },
            label = { Text(text = stringResource(id = R.string.name)) }
        )
        OutlinedTextField(
            value = tlpText,
            onValueChange = { tlpText = it },
            label = { Text(text = stringResource(id = R.string.noTlp)) }
        )
        OutlinedTextField(
            value = almtTxt,
            onValueChange = { namaTxt = it },
            label = { Text(text = stringResource(id = R.string.alamat)) }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            Button(onClick = onCancelButtonClicked) {
                Text(text = stringResource(id = R.string.cancel))
            }
            
            Spacer(modifier = Modifier.width(60.dp))

            Button(onClick = { onNextButtonClicked(lisData) }) {
                Text(text = stringResource(id = R.string.submit))
            }            
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSatu(){
    HalamanSatu(onNextButtonClicked = {},
        onCancelButtonClicked = {})
}
