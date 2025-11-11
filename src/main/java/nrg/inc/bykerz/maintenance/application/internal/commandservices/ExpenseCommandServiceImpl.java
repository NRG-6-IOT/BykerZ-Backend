package nrg.inc.bykerz.maintenance.application.internal.commandservices;

import nrg.inc.bykerz.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.domain.model.commands.CreateExpenseByOwnerIdCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.CreateExpenseCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteExpenseCommand;
import nrg.inc.bykerz.maintenance.domain.model.entities.ExpenseType;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ExpenseTypes;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import nrg.inc.bykerz.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final OwnerRepository ownerRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository, ProfileRepository profileRepository, OwnerRepository ownerRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional
    public Optional<Expense> handle(CreateExpenseCommand command) {

        var user = userRepository.findById(command.userId());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with id " + command.userId() + " not found");
        }

        var expense = new Expense(
                command.name(),
                command.finalPrice(),
                command.userId(),
                new ExpenseType(ExpenseTypes.valueOf(command.expenseType()))
        );

        try {
            expenseRepository.save(expense);
            return Optional.of(expense);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating expense: " + e.getMessage());
        }
    }

    @Override
    public Optional<Expense> handle(CreateExpenseByOwnerIdCommand command) {

        var owner = ownerRepository.findById(command.ownerId());

        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner with id " + command.ownerId() + " not found");
        }

        var profile = profileRepository.findById(owner.get().getProfile().getId());

        if (profile.isEmpty()) {
            throw new IllegalArgumentException("Profile for owner with id " + command.ownerId() + " not found");
        }

        var user = userRepository.findById(profile.get().getUserId());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User for profile with id " + profile.get().getId() + " not found");
        }

        var expense = new Expense(
                command.name(),
                command.finalPrice(),
                user.get().getId(),
                new ExpenseType(ExpenseTypes.valueOf(command.expenseType()))
        );

        try {
            expenseRepository.save(expense);
            return Optional.of(expense);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating expense: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void handle(DeleteExpenseCommand command) {
        var expense = expenseRepository.findById(command.expenseId());

        if (expense.isEmpty()) {
            throw new IllegalArgumentException("Expense with id " + command.expenseId() + " not found");
        }

        try {
            expenseRepository.delete(expense.get());
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting expense: " + e.getMessage());
        }
    }
}
