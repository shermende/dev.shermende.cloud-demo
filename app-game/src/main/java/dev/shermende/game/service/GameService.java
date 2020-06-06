package dev.shermende.game.service;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.resource.GameMoveResource;
import dev.shermende.lib.support.service.CrudService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface GameService extends CrudService<Game, Long> {

    @NotNull
    Page<Game> findAll(
        @NotNull Authentication authentication,
        @Nullable Predicate predicate,
        @NotNull Pageable pageable
    );

    @NotNull
    Game create(
        @NotNull Authentication authentication,
        @NotNull GameCreateResource resource
    );

    @NotNull
    Game move(
        @NotNull Authentication authentication,
        @NotNull Long gameId,
        @NotNull GameMoveResource resource
    );

}
