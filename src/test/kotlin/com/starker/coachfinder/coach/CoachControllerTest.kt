package com.starker.coachfinder.coach

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.number.OrderingComparison.greaterThan
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.internal.matchers.GreaterThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner

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
        val request = Coach()
        var result = testRestTemplate.postForEntity("/coach/", request, Coach::class.java)
        assertNotNull(result.body?.id)
    }
}