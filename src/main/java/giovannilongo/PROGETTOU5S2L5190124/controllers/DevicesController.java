package giovannilongo.PROGETTOU5S2L5190124.controllers;

import giovannilongo.PROGETTOU5S2L5190124.entities.Device;
import giovannilongo.PROGETTOU5S2L5190124.exceptions.BadRequestException;
import giovannilongo.PROGETTOU5S2L5190124.payloads.NewDeviceDTO;
import giovannilongo.PROGETTOU5S2L5190124.services.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    @Autowired
    DevicesService devicesService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return devicesService.save(body);
    }

    @GetMapping("")
    public List<Device> getDevice(@RequestParam(required = false) Long user_id) {
        if (user_id != null) return devicesService.findByUser(user_id);
        else return devicesService.getDevice();
    }

    @GetMapping("/{deviceId}")
    public Device findById(@PathVariable long deviceId) {
        return devicesService.findById(deviceId);
    }

    @PutMapping("/{deviceId}")
    public Device findAndUpdate(@PathVariable long deviceId, @RequestBody NewDeviceDTO body) {
        return devicesService.findByIdAndUpdate(deviceId, body);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long deviceId) {
        devicesService.findByIdAndDelete(deviceId);
    }

    //    @PostMapping("/{userId}/devices/{deviceId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Device addDeviceToUser(@PathVariable long userId, @PathVariable long deviceId) {
//        Device device = devicesService.findById(deviceId);
//        return usersService.addDeviceToUser(userId, device.getId());
//    }
}
