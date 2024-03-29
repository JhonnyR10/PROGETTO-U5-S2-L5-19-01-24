package giovannilongo.PROGETTOU5S2L5190124.controllers;

import giovannilongo.PROGETTOU5S2L5190124.entities.User;
import giovannilongo.PROGETTOU5S2L5190124.exceptions.BadRequestException;
import giovannilongo.PROGETTOU5S2L5190124.payloads.NewUserDTO;
import giovannilongo.PROGETTOU5S2L5190124.payloads.NewUserResponseDTO;
import giovannilongo.PROGETTOU5S2L5190124.services.DevicesService;
import giovannilongo.PROGETTOU5S2L5190124.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    DevicesService devicesService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        User newUser = usersService.save(body);
        return new NewUserResponseDTO(newUser.getId());
    }

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return usersService.getUser(page, size, sortBy);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) {
        return usersService.findById(userId);
    }

    @PutMapping("/{userId}")
    public User findAndUpdate(@PathVariable long userId, @RequestBody User body) {
        return usersService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long userId) {
        usersService.findByIdAndDelete(userId);
    }

    @PatchMapping("/{userId}/avatar")
    public User uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable long userId) {
        try {
            return usersService.uploadAvatar(userId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
