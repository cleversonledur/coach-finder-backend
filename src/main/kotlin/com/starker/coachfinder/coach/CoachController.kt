package com.starker.coachfinder.coach

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
@RequestMapping("coach")
class CoachController(val repository: CoachRepository) {

    @GetMapping
    fun listCoaches(): ResponseEntity<CoachDTO> {
        val coaches = CoachDTO(repository.findAll())
        return ResponseEntity.ok(coaches)
    }

    @PostMapping
    fun createCoach(@RequestBody coach: Coach) = ResponseEntity.ok(repository.save(coach))

    @PutMapping("{id}")
    fun updateCoach(@PathVariable id : String, @RequestBody coach : Coach): ResponseEntity<Coach> {

        val coachFromDB = repository.findById(id).orElseThrow{
            RuntimeException("Coach $coach.$id not found")
        }
        return ResponseEntity.ok(repository.save(coachFromDB.copy(name=coach.name, address = coach.address)))
    }

    @DeleteMapping("{id}")
    fun deleteCoach(@PathVariable id : String): ResponseEntity<String> {
        if(repository.findById(id).isPresent){
            repository.deleteById(id)
            return ResponseEntity.ok().body("Coach with id $id deleted.")
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach with id $id not found in database.")
    }
}