package com.example.data

import com.example.data.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageDataSourceImp(
    private val db: CoroutineDatabase
) : MessageDataSource {

    private val messages = db.getCollection<Message>()

    override suspend fun getAllMessages() = messages.find()
        .descendingSort(Message::timestamp)
        .toList()


    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }
}