package unpas.ac.id.pembeliantiket.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import unpas.ac.id.pembeliantiket.model.PembelianTIket

@Database(entities = [PembelianTIket::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pembelianTiketDao(): PembelianTiketDao
}