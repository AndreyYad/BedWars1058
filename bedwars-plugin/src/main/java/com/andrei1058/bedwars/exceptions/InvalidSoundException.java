
package com.andrei1058.bedwars.exceptions;

import com.andrei1058.bedwars.server.VersionSupport;

//! из АПИ

public class InvalidSoundException extends Throwable {


    public InvalidSoundException(String s) {
        super(s + " is not a valid " + VersionSupport.getName() + " sound! Using defaults..");
    }
}
