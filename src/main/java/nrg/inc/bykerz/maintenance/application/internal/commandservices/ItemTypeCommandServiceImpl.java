package nrg.inc.bykerz.maintenance.application.internal.commandservices;


import nrg.inc.bykerz.maintenance.domain.model.commands.SeedItemTypesCommand;
import nrg.inc.bykerz.maintenance.domain.model.entities.ItemType;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ItemTypes;
import nrg.inc.bykerz.maintenance.domain.services.ItemTypeCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ItemTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class ItemTypeCommandServiceImpl implements ItemTypeCommandService {

    private final ItemTypeRepository itemTypeRepository;

    public ItemTypeCommandServiceImpl(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    @Override
    public void handle(SeedItemTypesCommand command) {
        Arrays.stream(ItemTypes.values()).forEach(itemType -> {
            if (!itemTypeRepository.existsByName(itemType)) {
                itemTypeRepository.save(new ItemType(ItemTypes.valueOf(itemType.name())));
            }
        });
    }
}
