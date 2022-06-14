package com.example.KeyRemover.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RemoveKeyFromJsonPayload {

    private static final String KEY = "d";

    private static final String SUB_KEY = "results";

    private static final List<String> keysToRemove = Arrays.asList("_metadata");

    private static final String inputFile = "payload.json";

    ObjectMapper objectMapper = new ObjectMapper();

    public void removeKey() throws IOException {
        Map<String, Map> jsonPayload = objectMapper.readValue(new String(Files.readAllBytes(new ClassPathResource(inputFile).getFile().toPath())), Map.class);

        List<Map> results = ((ArrayList<Object>) jsonPayload.get(KEY).get(SUB_KEY)).stream().map(result -> {
            Map map = objectMapper.convertValue(result, Map.class);
            keysToRemove.forEach(map::remove);
            return map;
        }).collect(Collectors.toList());

        try (PrintWriter out = new PrintWriter("formatted_" + inputFile)) {
            out.println(objectMapper.writeValueAsString(results));
        }

    }

}
