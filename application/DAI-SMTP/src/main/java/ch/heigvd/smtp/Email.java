package ch.heigvd.smtp;

import java.util.List;

/**
 * This record represents an email.
 * An email contains a sender and a list of receptionists, it also contains a subject and a message (the body).
 * We created a record since it is just here to pass around data that is immutable. Each field is private final, and
 * we need a getter for each. This way we also get a public constructor with a corresponding argument for each field.
 *
 * @param from    sender
 * @param to      receptionists
 * @param message body
 * @param subject subject
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public record Email(String from, List<String> to, String message, String subject) {}
