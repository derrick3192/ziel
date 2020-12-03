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
     * The ip Address of the user
     */
    protected String ip;

    /**
     * The country the user is in
     */
    protected String country;

    /**
     * The city the user is in
     */
    protected String city;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isMfa() {
        return mfa;
    }

    public void setMfa(boolean mfa) {
        this.mfa = mfa;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
