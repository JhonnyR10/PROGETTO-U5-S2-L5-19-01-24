package giovannilongo.PROGETTOU5S2L5190124.payloads.errors;

import java.util.Date;
import java.util.List;

public record ErrorsPayloadWithList(
        String message,
        Date timestamp,
        List<String> errorsList
) {
}
