package com.oe.ziel.domain;

import com.oe.ziel.domain.user.User;
import org.joda.time.Instant;

/**
 * Container for the information when a component is invoked.
 */
public class Context {

    protected Instant eventTime;

    protected User user;

}
