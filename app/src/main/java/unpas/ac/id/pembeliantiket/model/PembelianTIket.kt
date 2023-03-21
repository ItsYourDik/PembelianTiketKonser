package unpas.ac.id.pembeliantiket.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PembelianTIket(
    @PrimaryKey val id: String,
    val nama: String,
    val namakonser: String,
    val tanggal: String,
    val harga: String
)
