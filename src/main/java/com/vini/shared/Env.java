package com.vini.shared;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

public class Env {
    private static Dotenv dotenv;

    public static String get(String envName) throws DotenvException {
        if (Env.dotenv == null) {
            Env.dotenv = Dotenv.configure().ignoreIfMalformed().ignoreIfMissing().load();
        }

        return Env.dotenv.get(envName);
    }
}
