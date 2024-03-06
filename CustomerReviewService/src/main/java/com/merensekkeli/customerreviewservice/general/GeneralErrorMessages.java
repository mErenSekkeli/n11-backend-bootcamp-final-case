package com.merensekkeli.customerreviewservice.general;

import java.time.LocalDateTime;

public record GeneralErrorMessages(LocalDateTime date, String message, String description) {
}
