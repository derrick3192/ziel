package com.oe.ziel.domain.user;

import java.util.List;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.Years;

public class User {

    /**
     * The username or the user
     */
    protected String username;

    /**
     * The display name of the user which is shown in the UI
     */
    protected String displayName;

    /**
     * The email of the user.
     */
    protected String email;

    /**
     * What department the user belongs to.
     */
    protected String department;

    /**
     * What groups the user belongs to.
     */
    protected List<String> groups;

    /**
     * When the user was created.
     */
    protected Instant createdAt;

    /**
     * When the user was last updated
     */
    protected Instant updatedAt;

    /**
     * The user has authenticated using MFA
     */
    protected boolean mfa;

    /**
     * When the user was last logged in
     */
    protected Instant lastLogin;

    /**
     * What the gender of the user is
     */
    protected Gender gender;

    /**
     * The title of the user.
     */
    protected String title;

    /**
     * The address the user lives in
     */
    protected String livingAddress;

    /**
     * The mailing address of the user
     */
    protected String mailAddress;

    /**
     * The mobile phone number of th user
     */
    protected String mobile;

    /**
     * The DOB of the user
     */
    protected LocalDate birthDate;

    /**
     * Calculate the age of the user as of now.
     * @return the age in years
     */
    public Years age() {
        return age(Instant.now());
    }

    /**
     * Calculate the age of a user given a time
     * @param now the time to calculate the age
     * @return the age in years
     */
    public Years age(Instant now) {
        if (now.isBefore(birthDate.toDateTimeAtStartOfDay())) {
            throw new RuntimeException("Users birthday=" + birthDate + " is after: " + now);
        }
        return Years.yearsBetween(birthDate.toDateTimeAtStartOfDay().toInstant(), now);
    }

}
