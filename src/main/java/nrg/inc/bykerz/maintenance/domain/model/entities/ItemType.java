package nrg.inc.bykerz.maintenance.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ItemTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class ItemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private ItemTypes name;

    public ItemType(ItemTypes name){
        this.name=name;
    }
}
