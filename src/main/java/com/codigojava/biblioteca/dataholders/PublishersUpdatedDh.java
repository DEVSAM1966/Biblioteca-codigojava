package com.codigojava.biblioteca.dataholders;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublishersUpdatedDh extends PublishersBaseDh {

    @NotNull(message = "Publisher ID is mandatory for updates")
    private Integer publisherId;
}