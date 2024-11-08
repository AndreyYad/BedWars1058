
package com.andrei1058.bedwars.exceptions;

import com.andrei1058.bedwars.server.VersionSupport;

//! из АПИ

public class InvalidMaterialException extends Exception {

    public InvalidMaterialException(String s) {
        super(s + " is not a valid " + VersionSupport.getName() + " material! Using defaults..");
    }
}
