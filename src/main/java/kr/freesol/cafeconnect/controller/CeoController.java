package kr.freesol.cafeconnect.controller;

import jakarta.validation.Valid;
import kr.freesol.cafeconnect.domain.Ceo;
import kr.freesol.cafeconnect.dto.CeoCreateRequest;
import kr.freesol.cafeconnect.dto.CeoCreateResponse;
import kr.freesol.cafeconnect.service.CeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ceo")
public class CeoController {
    private final CeoService ceoService;

    @PostMapping
    public ResponseEntity<CeoCreateResponse> createCeo(@RequestBody @Valid CeoCreateRequest ceoCreateRequest) {
        Ceo ceo = ceoCreateRequest.toEntity();
        Long id = ceoService.createCeo(ceo);

        CeoCreateResponse ceoCreateResponse = CeoCreateResponse.builder()
                .id(id)
                .build();

        return ResponseEntity.created(URI.create("/api/ceo/" + id)).body(ceoCreateResponse);
    }

}
