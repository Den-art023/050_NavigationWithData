package com.example.esjumbo.ui.theme.theme

import androidx.lifecycle.ViewModel
import com.example.esjumbo.data.OrderAndFormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 3000

class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderAndFormUIState())
    val stateUI: StateFlow<OrderAndFormUIState> = _stateUI.asStateFlow()

    fun setPelanggan(list: MutableList<String>) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nama = list[0],
                tlp = list[1],
                alamat = list[2]
            )
        }
    }

    fun setJumlah(jmlEsJumbo: Int) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHarga(jumlah = jmlEsJumbo)
            )
        }
    }

    fun setRasa(rasaPilihan: String) {
        _stateUI.update { steteSaatIni ->
            steteSaatIni.copy(rasa = rasaPilihan)
        }
    }

    fun resetOrder() {
        _stateUI.value = OrderAndFormUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ): String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }
}