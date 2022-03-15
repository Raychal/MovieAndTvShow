package com.raychal.core.utils

import com.raychal.core.data.source.local.entity.GameEntity
import com.raychal.core.data.source.remote.response.DetailGameResponse
import com.raychal.core.data.source.remote.response.GameResponse
import com.raychal.core.domain.model.Game

object DataMapper {
    fun mapGameResponsetoEntities(input: List<GameResponse>) :List<GameEntity>{
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                it.backgroundImage,
                null.toString(),
                it.id,
                it.name,
                it.playtime,
                it.rating,
                it.released,
                it.toString(),
                it.toString(),
                favorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> {
        return input.map {
            Game(
                it.backgroundImage,
                it.id,
                it.name,
                it.playtime,
                it.rating,
                it.released,
                it.backgroundImageAdditional,
                it.description,
                it.updated,
                favorite = false
            )
        }
    }

    fun mapDomainToEntity(input: Game): GameEntity {
        return GameEntity(
            input.backgroundImage,
            input.backgroundImageAdditional,
            input.id,
            input.name,
            input.playtime,
            input.rating,
            input.released,
            input.updated,
            input.description,
            favorite = input.favorite
        )
    }
}