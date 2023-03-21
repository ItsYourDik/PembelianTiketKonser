package unpas.ac.id.pembeliantiket.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import unpas.ac.id.pembeliantiket.model.PembelianTIket

@Dao
interface PembelianTiketDao {
    @Query("SELECT * FROM PembelianTiket")
    fun loadAll(): LiveData<List<PembelianTIket>>

    @Query("SELECT * FROM PembelianTiket WHERE id = :id")
    fun find(id: String): PembelianTIket?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: PembelianTIket)

    @Delete
    fun delete(item: PembelianTIket)
}