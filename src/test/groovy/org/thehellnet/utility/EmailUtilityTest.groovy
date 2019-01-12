package org.thehellnet.utility

import spock.lang.Specification

class EmailUtilityTest extends Specification {

    def "Validation"() {
        given:
        def VALUES = [
                ""           : false,
                "admin"      : false,
                "otherstring": false,
                "str@"       : true,
                "@"          : true,
        ]

        expect:
        assert !EmailUtility.validate(null)

        VALUES.each { input, expected ->
            assert EmailUtility.validate(input) == expected
        }
    }

    def "Validation for login purpose"() {
        given:
        def VALUES = [
                ""           : false,
                "admin"      : true,
                "otherstring": false,
                "str@"       : true,
                "@"          : true,
        ]

        expect:
        assert !EmailUtility.validateForLogin(null)

        VALUES.each { input, expected ->
            assert EmailUtility.validateForLogin(input) == expected
        }
    }
}
