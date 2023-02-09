package com.example.sumatifroom_tasyazakiarifatunnisa_genap.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(
    entities = [tb_barang::class],
    version = 1
)
abstract class  codepelita: RoomDatabase(){
    abstract fun tbBarang() : barangDAO

    companion object{
        @Volatile private  var instance : codepelita? = null
        private  val LOCK = Any()

        operator fun invoke(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            codepelita::class.java,
            "205447_db"

        ).build()
    }

}
