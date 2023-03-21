package unpas.ac.id.pembeliantiket.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import unpas.ac.id.pembeliantiket.model.PembelianTIket
import unpas.ac.id.pembeliantiket.persistences.AppDatabase

@Composable
fun PengelolaanTiket() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pembelian-tiket"
    ).build()
    val pembelianTiketDao = db.pembelianTiketDao()

    val list : LiveData<List<PembelianTIket>> = pembelianTiketDao.loadAll()
    val items: List<PembelianTIket> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanTiket(pembelianTiketDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(
                            text = item.nama, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Konser", fontSize = 14.sp)
                        Text(
                            text = item.namakonser, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "tanggal", fontSize = 14.sp)
                        Text(
                            text = item.tanggal, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Harga", fontSize = 14.sp)
                        Text(
                            text = "Rp. ${item.harga}", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}