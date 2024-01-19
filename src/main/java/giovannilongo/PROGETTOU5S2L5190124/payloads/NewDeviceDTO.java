package giovannilongo.PROGETTOU5S2L5190124.payloads;

import jakarta.validation.constraints.NotNull;

public record NewDeviceDTO(
        @NotNull(message = "L'id dell'user è obbligatorio")
        Long user_Id,
        @NotNull(message = "Il tipo è obbligatorio")
        String type,
        @NotNull(message = "Lo status è obbligatorio")
        String status
) {
}
