package com.epam.esm.controller;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCertificate(@RequestBody @Validated Certificate certificate) throws ServiceException {
        certificateService.createCertificate(certificate);
        return new ResponseEntity<>("Certificate created.", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificates() throws ServiceException {
        return new ResponseEntity<>(certificateService.findCertificates(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificate(@PathVariable Optional<Integer> id) throws ServiceException {
        return new ResponseEntity<>(certificateService.findCertificate(id.get()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCertificate(@PathVariable Optional<Integer> id) throws ServiceException {
        certificateService.deleteCertificate(id.get());
        return new ResponseEntity<>(String.format("Certificate with id: %d deleted.", id.get()), HttpStatus.OK);
    }

    @RequestMapping(value = {"/", "/{id}"}, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCertificate(@PathVariable Optional<Integer> id, @RequestBody @Validated Certificate certificate) throws ServiceException {
        certificateService.updateCertificate(id.get(), certificate);
        return new ResponseEntity<>(String.format("Certificate with id: %d updated.", id.get()), HttpStatus.OK);
    }
}
