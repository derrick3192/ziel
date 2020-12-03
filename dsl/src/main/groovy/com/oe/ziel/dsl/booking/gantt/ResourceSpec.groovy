package com.oe.ziel.dsl.booking.gantt

import com.oe.ziel.dsl.booking.gantt.constraint.LicenseConstraintSpec
import groovy.transform.CompileStatic

/**
 * Spec for resources
 */
@CompileStatic
class ResourceSpec implements Spec {

    boolean same

    // the selected license for the resource
    License givenLicense

    int defaultScore

    // given resource
    Object givenResource

    boolean license(String licenseName){
        return license(licenseName, 0, 0 , 0)
    }

    boolean license(String licenseName, int major){
        return license(licenseName, major, 0, 0)
    }

    boolean license(String licenseName, int major, int minor){
        return license(licenseName, major, minor, 0)
    }

    boolean license(String licenseName, int major, int minor, int patch){
        return givenLicense.major <=> major || givenLicense.minor <=> minor || givenLicense.patch <=> patch
    }

    void constraint(int score, Closure<Boolean> cl){
        Tuple2<Integer, Closure<Boolean>> tuple = new Tuple2(score, cl)
    }

    void constraint(Closure<Boolean> cl){
        constraint(defaultScore, cl)
    }


}
