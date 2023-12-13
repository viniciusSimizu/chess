package com.vini.shared;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

public class Env {
    private static Dotenv dotenv;

    public static String get(String envName) {
        if (Env.dotenv == null) {
            try {
                Env.dotenv = Dotenv
                        .configure()
                        .ignoreIfMalformed()
                        .ignoreIfMissing()
                        .load();

            } catch (DotenvException de) {
                de.printStackTrace();
                return "";
            }
        }

        return Env.dotenv.get(envName);
    }
}
