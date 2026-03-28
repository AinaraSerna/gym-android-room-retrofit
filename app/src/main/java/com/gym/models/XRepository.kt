package com.gym.models

import com.gym.data.X
import com.gym.data.room.x.XDao
import javax.inject.Inject

class XRepository @Inject constructor(
    private var dao: XDao
) {
    suspend fun get() = dao.getAll()
    suspend fun get(id: Int) = dao.getById(id)
    suspend fun insert(x: X) = dao.insert(x = x.toXEntity())
    suspend fun update(x: X) = dao.update(x = x.toXEntity())
    suspend fun delete(x: X) = dao.delete(x = x.toXEntity())
    suspend fun deleteByIdi(id: Int) = dao.deleteById(id = id)
    suspend fun count() = dao.count()
}