package com.kraqqen.util.file_system;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtil
{
    public static String readFromFile(String name)
    {
        StringBuilder source = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(name));

            String line;
            while ((line = reader.readLine()) != null)
            {
                source.append(line).append("\n");
            }

            reader.close();
        }
        catch (Exception e)
        {
            System.err.println("Error loading source code: " + name);
            e.printStackTrace();
        }

        return source.toString();
    }

    public static String[] readAllLines(String name)
    {
        return readFromFile(name).split("\n");
    }
}