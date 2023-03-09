package com.iessanalberto.dam2.proyecto_tfg.database.modeldb

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Movie: RealmObject{
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var title: String = ""

}