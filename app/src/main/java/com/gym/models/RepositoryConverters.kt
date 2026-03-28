package com.gym.models

import com.gym.data.X
import com.gym.data.room.x.XEntity

fun X.toXEntity() = XEntity(
    id = this.id
)

fun XEntity.toX() = X(
    id = this.id
)