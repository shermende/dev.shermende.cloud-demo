package dev.shermende.game.db.entity;

import dev.shermende.lib.dal.db.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Game extends BaseEntity<Long> {
    private static final long serialVersionUID = 1425335737055233411L;

    @NotNull
    private Long userId;

    @NotNull
    private Long scenarioId;

    @NotNull
    private Long reasonId;

    @NotNull
    private Long pointId;

}