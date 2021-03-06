package dev.shermende.game.controller;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.assembler.GameModelAssembler;
import dev.shermende.game.assembler.RouteModelAssembler;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.model.GameModel;
import dev.shermende.game.model.RouteModel;
import dev.shermende.game.service.GameService;
import dev.shermende.game.service.crud.GameCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;
    private final GameCrudService crudService;
    private final GameModelAssembler assembler;
    private final RouteModelAssembler routeModelAssembler;

    @GetMapping
    public PagedModel<GameModel> findAll(
        Authentication authentication,
        @PageableDefault Pageable pageable,
        @QuerydslPredicate(root = Game.class) Predicate predicate,
        PagedResourcesAssembler<Game> pagedResourcesAssembler
    ) {
        final Page<Game> games = crudService.findAllByAuthentication(authentication, predicate, pageable);
        return pagedResourcesAssembler.toModel(games, assembler);
    }

    @GetMapping("/{id}")
    public GameModel findById(
        @PathVariable Long id
    ) {
        return assembler.toModel(crudService.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @GetMapping("/{id}/get-available-routes")
    public CollectionModel<RouteModel> routes(
        @PathVariable Long id
    ) {
        return routeModelAssembler.toCollectionModel(service.getAvailableRoutes(id));
    }

}