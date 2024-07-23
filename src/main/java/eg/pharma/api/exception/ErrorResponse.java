package eg.pharma.api.exception;

import java.util.HashMap;

public record ErrorResponse(HashMap<String, String> data, HashMap<String, String> meta) {
}
