package dev.shermende.reference.db.entity.movement;

import dev.shermende.lib.support.db.entity.TimedEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovementPoint extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

}
