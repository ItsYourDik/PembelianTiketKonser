package unpas.ac.id.pembeliantiket.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch
import unpas.ac.id.pembeliantiket.model.PembelianTIket
import unpas.ac.id.pembeliantiket.persistences.PembelianTiketDao
import unpas.ac.id.pembeliantiket.ui.theme.Purple700
import unpas.ac.id.pembeliantiket.ui.theme.Teal200

@Composable
fun FormPencatatanTiket(pembelianTiketDao: PembelianTiketDao) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val namakonser = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val kata = "Data Pembelian Tiket"

    println(kata)
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "Dik DIk") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama Konser") },
            value = namakonser.value,
            onValueChange = {
                namakonser.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "Kick Fest") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = tanggal.value,
            onValueChange = {
                tanggal.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "DD-MM-YYYY") }
        )
        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "100000") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                if(nama.value.text !== "" && namakonser.value.text !== ""){
                    if (tanggal.value.text !== "" && harga.value.text !== "" ){
                        val id = uuid4().toString()
                        val item = PembelianTIket(id, nama.value.text,
                            namakonser.value.text,tanggal.value.text, harga.value.text)
                        scope.launch {
                            pembelianTiketDao.insertAll(item)
                        }
                        tanggal.value = TextFieldValue("")
                        nama.value = TextFieldValue("")
                        namakonser.value = TextFieldValue("")
                        harga.value = TextFieldValue("")
                    }else{
                        Toast.makeText(context, "Tanggal Dan Harga Harus di Isi",
                            Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context, "Tanggal dan Keterangan Harus di Isi",
                        Toast.LENGTH_LONG).show()
                }



            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tanggal.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                namakonser.value = TextFieldValue("")
                harga.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}