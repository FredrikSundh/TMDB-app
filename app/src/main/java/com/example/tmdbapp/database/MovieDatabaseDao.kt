package com.example.tmdbapp.database

import androidx.room.*
import com.example.tmdbapp.model.CachedType
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.model.favouriteMovie

@Dao
interface MovieDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)
    @Insert
    suspend fun insertFavourite(favouriteMovie: favouriteMovie)

    @Delete
    suspend fun deleteFavourite(favouriteMovie: favouriteMovie)
    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    @Query("SELECT * from favouriteMovies")
    suspend fun getFavourites() : List<favouriteMovie>

    @Query("SELECT EXISTS(SELEcT * from favouriteMovies WHERE id = :id)")
    suspend fun isFavorite(id: Long): Boolean

    @Insert
    suspend fun insertCachedType(cachedType: CachedType)

    @Delete
    suspend fun removeCachedType(cachedType: CachedType)

    @Query("DELETE FROM cachedType")
    suspend fun deleteAllCachedTypes()

    @Query("SELECT EXISTS(SELECT 1 FROM cachedType WHERE type = :cachedTypeId LIMIT 1)")
    suspend fun isCachedTypeExists(cachedTypeId: String): Boolean

    @Query("SELECT type FROM cachedType LIMIT 1")
    suspend fun getFirstCachedType(): String?
}