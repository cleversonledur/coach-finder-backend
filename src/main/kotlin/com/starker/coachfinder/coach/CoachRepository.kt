package com.starker.coachfinder.coach

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CoachRepository : MongoRepository<Coach, String> {
}