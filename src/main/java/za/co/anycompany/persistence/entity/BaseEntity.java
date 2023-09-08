package za.co.anycompany.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @CreationTimestamp
    @Column(name = "created", nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE_UTC)
    private OffsetDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE_UTC)
    private OffsetDateTime lastModified;
}
