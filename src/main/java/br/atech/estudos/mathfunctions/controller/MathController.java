package br.atech.estudos.mathfunctions.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.atech.estudos.mathfunctions.service.MathService;

@RestController("/")
public class MathController {

    // @RequestMapping("/calculatePrimeNumbers")
    // public int calculatePrimeNumbers(final @RequestParam("from") long from,
    // final @RequestParam("to") long to) {
    @RequestMapping(method = RequestMethod.POST)
    public int calculatePrimeNumbers() {
        return MathService.calculateLenghtOfPrimeNumbers(1, 100000);
    }

    @RequestMapping("/calculatePrimeNumbers")
    public int calculatePrimeNumbersRequestParam(final @RequestParam("from") long from,
            final @RequestParam("to") long to) {
        return MathService.calculateLenghtOfPrimeNumbers(from, to);
    }

    @RequestMapping("/calculatePrimeNumbers/{from}/{to}")
    public int calculatePrimeNumbers(final @PathVariable(value = "from") Long from,
            final @PathVariable(value = "to") Long to) {
        return MathService.calculateLenghtOfPrimeNumbers(from, to);
    }

    @RequestMapping(value = "/donwload")
    public ResponseEntity<InputStreamResource> download() {
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            File file = loader.getResource("GuideV1.pdf").getFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                    .body(resource);
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

}
