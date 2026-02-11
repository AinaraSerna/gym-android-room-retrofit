package com.base.models

import com.base.data.X
import com.base.data.room.x.XEntity

fun X.toXEntity() = XEntity(
    id = this.id
)

fun XEntity.toX() = X(
    id = this.id
)