package com.example.scheduler.service;

import java.io.*;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class Command {

    public HashMap<String, String> exec(String cmd) {
        HashMap<String, String> output = new HashMap<String, String>();
        output.put("command", cmd);
        try {

            // Process p = Runtime.getRuntime().exec(command);
            ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));
            Process p = pb.start();

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            BufferedReader stdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String cmdOut = "";
            while ((stdOut.readLine()) != null) {
                cmdOut += stdOut.readLine() + "\n";
            }
            output.put("standardOutput", cmdOut);
            stdOut.close();

            String error = "";
            while ((error = stdError.readLine()) != null) {
                error += stdError.readLine() + "\n";
            }
            output.put("standardError", error);
            stdError.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

//    public static void main(String[] args) {
//        String command = "cmd.exe /c whoami";
//        HashMap<String, String> output = exec(command);
//        System.out.println(output);
//    }

}
