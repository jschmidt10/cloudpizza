package com.github.jschmidt10.cloudpizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * The entrypoint for the MeatRestController.
 */
@SpringBootApplication
@RestController
public class MeatRestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MeatRestController.class);

    private final String hostname;
    private final MeatRepository meatRepo = new MeatRepository();

    MeatRestController() {
        this.hostname = getHostname();
    }

    /**
     * Gets some random meats for a pizza.
     *
     * @param numMeats The number of desired meats
     * @return An HTTP response entity
     */
    @GetMapping
    public ResponseEntity<String> getRandomMeats(@RequestParam(required = false, defaultValue = "1") int numMeats) {
        Set<String> selectedMeats = null;
        try {
            selectedMeats = meatRepo.getRandomMeats(numMeats);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Client made a bad request", e);
            return errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            return successResponse(selectedMeats);
        } catch (Exception e) {
            LOGGER.error("Error creating JSON response", e);
            return errorResponse("Something went wrong. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<String> successResponse(Set<String> meats) {
        MeatResponse response = new MeatResponse(meats, hostname);
        return new ResponseEntity<>(response.asJson(), HttpStatus.OK);
    }

    private ResponseEntity<String> errorResponse(String errorMessage, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse(errorMessage, hostname);
        return new ResponseEntity<>(response.asJson(), httpStatus);
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MeatRestController.class, args);
    }
}
