package dev.shermende.reference.controller;

import dev.shermende.lib.model.reference.MovementRouteModel;
import dev.shermende.reference.assembler.MovementRouteModelAssembler;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.service.MovementRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movementRoutes")
public class MovementRouteController {

    private final MovementRouteService service;
    private final MovementRouteModelAssembler assembler;

    @GetMapping("/custom/findAllBySourcePointId")
    public PagedModel<MovementRouteModel> findAllBySourcePointId(
        @PageableDefault Pageable pageable,
        @RequestParam("sourcePointId") Long sourcePointId,
        PagedResourcesAssembler<MovementRoute> pagedResourcesAssembler
    ) {
        return pagedResourcesAssembler.toModel(
            service.findAllBySourcePointId(pageable, sourcePointId), assembler);
    }

    @GetMapping("/custom/findOneBySourcePointIdAndReasonId")
    public MovementRouteModel findOneBySourcePointIdAndReasonId(
        @PageableDefault Pageable pageable,
        @RequestParam("sourcePointId") Long sourcePointId,
        @RequestParam("reasonId") Long reasonId
    ) {
        return assembler.toModel(service
            .findOneBySourcePointIdAndReasonId(sourcePointId, reasonId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}