package com.example.individual.data.network

import com.example.individual.model.PlaneFull
import com.example.individual.utils.fromServerTimestamp
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PlaneJsonDeserializer : JsonDeserializer<PlaneFull> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PlaneFull {
        val jsonObject = json.asJsonObject
        return PlaneFull(
            id = jsonObject["id"].asString,
            airlineId = jsonObject["airlineId"].asString,
            onboardNumber = jsonObject["onboardNumber"].asString,
            flightNumber = jsonObject["flightNumber"].asString,
            flightFrom = jsonObject["flightFrom"].asString,
            flightTo = jsonObject["flightTo"].asString,
            boardingTime = jsonObject["boardingTime"].asDouble.fromServerTimestamp(),
            gate = jsonObject["gate"].asString,
            firstPilotName = jsonObject["gate"].asString,
            secondPilotName = jsonObject["pilotName"].asString,
            seatsCount = jsonObject["seatsCount"].asDouble,
            maxSpeedKmh = jsonObject["maxSpeedKmh"].asDouble
        )
    }
}