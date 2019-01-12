package org.thehellnet.utility

import spock.lang.Specification

class PasswordUtilityTest extends Specification {

    void "Password hash"() {
        given:
        String password = "admin"

        expect:
        String hash = PasswordUtility.hash(password)
        assert PasswordUtility.verify(hash, password)
    }
}

