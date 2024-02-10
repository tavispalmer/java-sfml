package org.sfml_dev.system;

import java.io.*;

class SharedLibrary {
    private static boolean loaded = false;

    public static void load() {
        if (!loaded) {
            try {
                InputStream inputStream = SharedLibrary.class.getResourceAsStream("/libsfml-system-natives.so");
                File file = File.createTempFile("libsfml-system-natives-", ".so");
                file.deleteOnExit();
                OutputStream outputStream = new FileOutputStream(file);

                outputStream.write(inputStream.readAllBytes());

                outputStream.close();
                inputStream.close();

                System.load(file.getAbsolutePath());
                loaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
