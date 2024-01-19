package giovannilongo.PROGETTOU5S2L5190124.services;

import giovannilongo.PROGETTOU5S2L5190124.entities.User;
import giovannilongo.PROGETTOU5S2L5190124.exceptions.BadRequestException;
import giovannilongo.PROGETTOU5S2L5190124.exceptions.NotFoundException;
import giovannilongo.PROGETTOU5S2L5190124.payloads.NewUserDTO;
import giovannilongo.PROGETTOU5S2L5190124.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;


    public User save(NewUserDTO body) throws IOException {
        usersRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
        });
        User newUser = new User();
        newUser.setUsername(body.username());
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + body.firstName() + "+" + body.lastName());
        newUser.setFirstName(body.firstName());
        newUser.setEmail(body.email());
        newUser.setLastName(body.lastName());

        return usersRepository.save(newUser);
    }

    public Page<User> getUser(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return usersRepository.findAll(pageable);
    }

    public User findById(long id) {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        usersRepository.delete(found);
    }

    public User findByIdAndUpdate(long id, User body) {

        User found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setUsername(body.getUsername());
        found.setFirstName(body.getFirstName());
        found.setLastName(body.getLastName());
        found.setAvatar(body.getAvatar());
        return usersRepository.save(found);
    }
}
