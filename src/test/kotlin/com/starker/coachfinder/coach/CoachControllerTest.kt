package com.starker.coachfinder.coach

import com.starker.coachfinder.address.PhoneNumber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CoachControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun listCoachesMustReturnCoachesData() {
        val result = testRestTemplate.getForEntity("/coach/", CoachDTO::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    fun createCoachMustReturnObjectIdAfterDataCreation() {
        val request = Coach(email = "fulano@abc.com.br", name = "Fulano Silva", phone = PhoneNumber("+55 51 9988-99999", whatsAppEnabled = false, callEnabled = true))
        val result = testRestTemplate.postForEntity("/coach/", request, Coach::class.java)
        assertNotNull(result.body?.id)
    }
}