
package com.andrei1058.bedwars.exceptions;


import com.andrei1058.bedwars.server.VersionSupport;

/// из АПИ

public class InvalidEffectException extends Throwable {

    public InvalidEffectException(String message) {
        super(message + " is not a valid " + VersionSupport.getName() + " effect! Using defaults..");
    }
}
