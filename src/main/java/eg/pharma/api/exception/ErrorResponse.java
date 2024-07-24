package eg.pharma.api.exception;

import java.util.HashMap;

public record ErrorResponse(HashMap<?, ?> data, HashMap<?, ?> meta) {
}
