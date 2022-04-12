package com.epam.esm.controller;


import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/certificateTag")
public class CertificateTagController {
    private final CertificateTagService certificateService;
    private final TagService tagService;

    @Autowired
    public CertificateTagController(CertificateTagService certificateService, TagService tagService) {
        this.certificateService = certificateService;
        this.tagService = tagService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCertificatesByTag(@PathVariable Optional<Integer> id) throws ServiceException {
        return new ResponseEntity<>(certificateService.findCertificatesByTag(id.get()), HttpStatus.OK);
    }

    @RequestMapping(value = "/name/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCertificatesByPartName(@RequestParam String name) throws ServiceException {
       return new ResponseEntity<>(certificateService.findCertificatesByPartName(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCertificatesSorted(@RequestParam(defaultValue ="name") String sortParam, @RequestParam(defaultValue = "asc") String direction) throws ServiceException {
        return new ResponseEntity<>(certificateService.findCertificatesSorted(sortParam, direction), HttpStatus.OK);
    }
}
